import React, { useState } from 'react';
import LoanForm from './components/EmprestimoForm';
import ResultsGrid from './components/ResultsGrid';

const App = () => {
  const [results, setResults] = useState(null);

  const handleCalculate = async (formData) => {
    try {
      const response = await fetch('http://localhost:8080/api/emprestimo/calcular', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      const data = await response.json();
      setResults(data);
    } catch (error) {
      console.error('Erro ao calcular:', error);
    }
  };

  return (
    <div>
      <LoanForm onCalculate={handleCalculate} />
      <ResultsGrid results={results} />
    </div>
  );
};

export default App;
