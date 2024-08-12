import React from 'react';
import './style.css';

const ResultsGrid = ({ results }) => {
  if (!results || results.length === 0) {
    return <p>Insira os dados e clique em calcular para ver os resultados.</p>;
  }

  return (
    <table>
      <thead>
      <tr>
      <th colspan="3">Empréstimo</th>
      <th colspan="2">Parcela</th>
      <th colspan="2">Principal</th>
      <th colspan="3">Juros</th>
    </tr>
        <tr>
          <th>Data</th>
          <th>Valor Empréstimo</th>
          <th>Saldo Devedor</th>
          <th>Consolidada</th>
          <th>Total</th>
          <th>Amortização</th>
          <th>Saldo</th>
          <th>Provisão</th>
          <th>Acumulado</th>
          <th>Pago</th>
        </tr>
      </thead>
      <tbody>
        {results.parcelas.map((item, index) => (
          <tr key={index}>
            <td>{item.data}</td>
            <td>{item.valorEmprestimo.toFixed(2)}</td>
            <td>{item.saldoDevedor.toFixed(2)}</td>
            <td>{item.consolidada}</td>
            <td>{item.total.toFixed(2)}</td>
            <td>{item.amortizacao.toFixed(2)}</td>
            <td>{item.saldo.toFixed(2)}</td>
            <td>{item.provisao.toFixed(2)}</td>
            <td>{item.acumulado.toFixed(2)}</td>
            <td>{item.pago.toFixed(2)}</td>            
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default ResultsGrid;
