package CalculadoraDeEmprestimos.Calculadora.de.Emprestimos.controller;

import CalculadoraDeEmprestimos.Calculadora.de.Emprestimos.model.CalcularEmprestimoRequest;
import CalculadoraDeEmprestimos.Calculadora.de.Emprestimos.model.CalcularEmprestimoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import CalculadoraDeEmprestimos.Calculadora.de.Emprestimos.service.CalculadoraDeEmprestimosService;

@RestController
@RequestMapping("/api/emprestimo")
public class CalculadoraDeEmprestimosController {

    @Autowired
    private CalculadoraDeEmprestimosService calculadoraDeEmprestimosService;

    @PostMapping("/calcular")
    public CalcularEmprestimoResponse calculateLoan(@RequestBody CalcularEmprestimoRequest request) {
        return calculadoraDeEmprestimosService.calcularEmprestimo(request);
    }

}