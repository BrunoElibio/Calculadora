package CalculadoraDeEmprestimos.Calculadora.de.Emprestimos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CalcularEmprestimoRequest {
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private LocalDate dataPrimeiroPagamento;
    private Double valorEmprestimo;
    private Double taxaJuros;

    private Integer qtdParcelas;

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public LocalDate getDataPrimeiroPagamento() {
        return dataPrimeiroPagamento;
    }

    public void setDataPrimeiroPagamento(LocalDate dataPrimeiroPagamento) {
        this.dataPrimeiroPagamento = dataPrimeiroPagamento;
    }

    public Double getValorEmprestimo() {
        return valorEmprestimo;
    }

    public void setValorEmprestimo(Double valorEmprestimo) {
        this.valorEmprestimo = valorEmprestimo;
    }

    public Double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(Double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public Integer getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(Integer qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }
}