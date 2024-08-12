package CalculadoraDeEmprestimos.Calculadora.de.Emprestimos.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CalcularEmprestimoResponse {
    private List<Parcela> parcelas;

    public CalcularEmprestimoResponse(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }


    public static class Parcela {
        private LocalDate data;
        private Double amortizacao;
        private Double saldo;

        private String consolidada;

        private Double total;

        private Double provisao;

        private Double acumulado;

        private Double pago;

        private Double valorEmprestimo;

        private Double saldoDevedor;

        public Parcela(LocalDate dataCompetencia, Double valorEmprestimo, Double saldoDevedor, String consolidada, Double total, Double amortizacao, Double saldo, Double provisao, Double acumulado, Double pago) {
            setData(dataCompetencia);
            setValorEmprestimo(valorEmprestimo);
            setConsolidada(consolidada);
            setAmortizacao(amortizacao);
            setTotal(total);
            setSaldo(saldo);
            setProvisao(provisao);
            setSaldoDevedor(saldoDevedor);
            setAcumulado(acumulado);
            setPago(pago);
        }

        public LocalDate getData() {
            return data;
        }

        public void setData(LocalDate data) {
            this.data = data;
        }

        public Double getAmortizacao() {
            return amortizacao;
        }

        public void setAmortizacao(Double amortizacao) {
            this.amortizacao = amortizacao;
        }

        public Double getSaldo() {
            return saldo;
        }

        public void setSaldo(Double saldo) {
            this.saldo = saldo;
        }

        public String getConsolidada() {
            return consolidada;
        }

        public void setConsolidada(String consolidada) {
            this.consolidada = consolidada;
        }

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public Double getProvisao() {
            return provisao;
        }

        public void setProvisao(Double provisao) {
            this.provisao = provisao;
        }

        public Double getAcumulado() {
            return acumulado;
        }

        public void setAcumulado(Double acumulado) {
            this.acumulado = acumulado;
        }

        public Double getPago() {
            return pago;
        }

        public void setPago(Double pago) {
            this.pago = pago;
        }

        public Double getValorEmprestimo() {
            return valorEmprestimo;
        }

        public void setValorEmprestimo(Double valorEmprestimo) {
            this.valorEmprestimo = valorEmprestimo;
        }

        public Double getSaldoDevedor() {
            return saldoDevedor;
        }

        public void setSaldoDevedor(Double saldoDevedor) {
            this.saldoDevedor = saldoDevedor;
        }
    }
}
