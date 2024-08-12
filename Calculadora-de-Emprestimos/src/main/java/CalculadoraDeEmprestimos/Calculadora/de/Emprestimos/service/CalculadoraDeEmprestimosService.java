package CalculadoraDeEmprestimos.Calculadora.de.Emprestimos.service;

import CalculadoraDeEmprestimos.Calculadora.de.Emprestimos.model.CalcularEmprestimoResponse;
import org.springframework.stereotype.Service;
import CalculadoraDeEmprestimos.Calculadora.de.Emprestimos.model.CalcularEmprestimoRequest;

import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Service
public class CalculadoraDeEmprestimosService {
    private static final Double DIAS_ANO = 360.0;

    public CalcularEmprestimoResponse calcularEmprestimo(CalcularEmprestimoRequest request) {
        List<CalcularEmprestimoResponse.Parcela> parcelas = new ArrayList<>();

        LocalDate dataCompetencia = request.getDataInicial();
        String consolidada  = "";
        Double valorEmprestimo    = request.getValorEmprestimo();
        Double saldoDevedor       = request.getValorEmprestimo();
        Double amortizacao = 0.00, pago = 0.00, provisao = 0.00, acumulado = 0.00;
        Double saldo = request.getValorEmprestimo();

        if(dataCompetencia.equals(request.getDataPrimeiroPagamento())) {
            consolidada = formatarConsolidada(1, request.getQtdParcelas());
            amortizacao= calcularAmortizacao(request);
        }

        Double total = amortizacao + pago;

        //primeira informação
        parcelas.add(new CalcularEmprestimoResponse.Parcela(dataCompetencia, valorEmprestimo, saldoDevedor, consolidada, total, amortizacao, saldo, provisao, acumulado, pago));

        //fim do mes solicitado
        LocalDate novaData = dataCompetencia.with(TemporalAdjusters.lastDayOfMonth());
        provisao           = calcularProvisao(request, novaData, dataCompetencia, request.getValorEmprestimo());
        valorEmprestimo   += provisao;

        parcelas.add(new CalcularEmprestimoResponse.Parcela(novaData, 0.00, valorEmprestimo, consolidada, 0.00, 0.00, request.getValorEmprestimo(), provisao, provisao, 0.00));

        //Verifica numero de meses ate primeiro pagamento
        Integer mesesDiferentes = 0;
        LocalDate ultimoCalc = novaData;
        while(novaData.getMonth()!=request.getDataPrimeiroPagamento().getMonth()){
            mesesDiferentes++;
            novaData = novaData.plusMonths(1);
        }

        //Até o mes do pagamento
        while(mesesDiferentes>1){
            ultimoCalc = ultimoCalc.plusDays(1);
            provisao   = calcularProvisao(request, ultimoCalc, ultimoCalc.withDayOfMonth(ultimoCalc.lengthOfMonth()), valorEmprestimo);
            valorEmprestimo += provisao;
            ultimoCalc = ultimoCalc.withDayOfMonth(ultimoCalc.lengthOfMonth());
            parcelas.add(new CalcularEmprestimoResponse.Parcela(ultimoCalc, 0.00, valorEmprestimo, consolidada, 0.00, 0.00, valorEmprestimo, provisao, provisao, 0.00));
            mesesDiferentes--;
        }

        //iniciando pagamento
        int nParcelas = 0;
        LocalDate primeiro = request.getDataPrimeiroPagamento();

        while(nParcelas<request.getQtdParcelas()){
            //parcela
            CalcularEmprestimoResponse.Parcela ultimaParcela = parcelas.get(parcelas.size()-1);
            consolidada  = formatarConsolidada(nParcelas+1, request.getQtdParcelas());
            amortizacao  = calcularAmortizacao(request);
            provisao     = calcularProvisao(request, primeiro, ultimaParcela.getData(), ultimaParcela.getSaldo());
            saldo        = ultimaParcela.getSaldo() - amortizacao;
            pago         = ultimaParcela.getAcumulado()+provisao;
            acumulado    = ultimaParcela.getAcumulado() + provisao - pago;
            saldoDevedor = saldo + acumulado;
            total        = amortizacao + pago;

            parcelas.add(new CalcularEmprestimoResponse.Parcela(primeiro, 0.00, saldoDevedor, consolidada, total, amortizacao, saldo, provisao , acumulado, pago));

            //juros
            ultimaParcela = parcelas.get(parcelas.size()-1);
            pago = amortizacao = total = 0.0;
            provisao    = calcularProvisao(request, primeiro.withDayOfMonth(primeiro.lengthOfMonth()), ultimaParcela.getData(), ultimaParcela.getSaldo());
            acumulado   = ultimaParcela.getAcumulado() + provisao - pago;
            saldoDevedor = saldo + acumulado;

            //caso seja ultima parcela, nao gera mais juros
            if(nParcelas+1>=request.getQtdParcelas()) break;
            parcelas.add(new CalcularEmprestimoResponse.Parcela(primeiro.withDayOfMonth(primeiro.lengthOfMonth()), 0.00, saldoDevedor, "", total, amortizacao, saldo, provisao , acumulado, pago));

            primeiro = primeiro.plusMonths(1);
            nParcelas++;
        }

        return new CalcularEmprestimoResponse(parcelas);
    }

    private String formatarConsolidada(int parcelaAtual, int totalParcelas) {
        return parcelaAtual + "/" + totalParcelas;
    }

    private Double calcularAmortizacao(CalcularEmprestimoRequest request) {
        return request.getValorEmprestimo() / request.getQtdParcelas();
    }

    private Double calcularProvisao(CalcularEmprestimoRequest request, LocalDate atual, LocalDate ultimaParcela, Double mult){
        Double provisao    = request.getTaxaJuros()/100 + 1;
        provisao    = Math.pow(provisao, ((atual.getDayOfYear() - ultimaParcela.getDayOfYear())/DIAS_ANO));
        provisao    = provisao - 1;
        provisao   *= mult;
        return provisao;
    }
}