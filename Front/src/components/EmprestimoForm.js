import React, { useState } from 'react';
import './style.css';

const LoanForm = ({ onCalculate }) => {
  const [formData, setFormData] = useState({
    dataInicial: '',
    dataFinal: '',
    dataPrimeiroPagamento: '',
    valorEmprestimo: '',
    taxaJuros: '',
    dayBase: 360,
    qtdParcelas: ''
  });

  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const validate = () => {
    const newErrors = {};

    if (new Date(formData.dataFinal) <= new Date(formData.dataInicial)) {
      newErrors.dataFinal = 'A data final deve ser maior que a data inicial.';
    }

    if (
      new Date(formData.dataPrimeiroPagamento) <= new Date(formData.dataInicial) ||
      new Date(formData.dataPrimeiroPagamento) >= new Date(formData.dataFinal)
    ) {
      newErrors.dataPrimeiroPagamento =
        'A data do primeiro pagamento deve ser maior que a data inicial e menor que a data final.';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validate()) {
      onCalculate(formData);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Data Inicial:
        <input
          type="date"
          name="dataInicial"
          value={formData.dataInicial}
          onChange={handleChange}
          required
        />
      </label>
      <label>
        Data Final:
        <input
          type="date"
          name="dataFinal"
          value={formData.dataFinal}
          onChange={handleChange}
          required
        />
        {errors.dataFinal && <p className="error">{errors.dataFinal}</p>}
      </label>
      <label>
        Primeiro Pagamento:
        <input
          type="date"
          name="dataPrimeiroPagamento"
          value={formData.dataPrimeiroPagamento}
          onChange={handleChange}
          required
        />
        {errors.dataPrimeiroPagamento && <p className="error">{errors.dataPrimeiroPagamento}</p>}
      </label>
      <label>
        Valor de Empréstimo:
        <input
          type="number"
          name="valorEmprestimo"
          value={formData.valorEmprestimo}
          onChange={handleChange}
          required
        />
      </label>
      <label>
        Taxa de Juros (%):
        <input
          type="number"
          step="0.01"
          name="taxaJuros"
          value={formData.taxaJuros}
          onChange={handleChange}
          required
        />
      </label>
      <label>
        Base de Dias:
        <input
          type="number"
          name="dayBase"
          value={formData.dayBase}
          readOnly
        />
      </label>
      <label>
        Quantidade de Parcelas:
        <input
          type="number"
          name="qtdParcelas"
          value={formData.qtdParcelas}
          onChange={handleChange}
          required
        />
      </label>
      <button type="submit">Calcular</button>
    </form>
  );
};

export default LoanForm;
