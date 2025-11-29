package com.matheushstrindade.banking;

import com.matheushstrindade.banking.model.Cliente;
import com.matheushstrindade.banking.model.Conta;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.math.RoundingMode;

public class App {
    public static void main(String[] args) {

        // === Criação do Cliente ===
        Cliente matheus = new Cliente(
                "Matheus H. S. Trindade",
                "529.982.247-25",
                LocalDate.of(1995, 10, 5)
        );

        System.out.println("Cliente criado: " + matheus.getNome());
        System.out.println("CPF: " + matheus.getCpf() + "\n");

        // === Criação da primeira conta (automática associação) ===
        System.out.println("Criando conta corrente com saldo inicial...");
        Conta contaCorrente = new Conta(matheus, new BigDecimal("1500.00"));
        System.out.println("Conta criada → " + contaCorrente);
        System.out.println("Titular da conta: " + contaCorrente.getTitular().getNome());
        System.out.println("Total de contas do cliente: " + matheus.getContas().size());
        System.out.println();

        // === Testes de depósito ===
        System.out.println("Testando depósito com valor negativo...");
        try {
            contaCorrente.depositar(new BigDecimal("-5000"));
        } catch (IllegalArgumentException e) {
            System.out.println("Depósito recusado → " + e.getMessage());
        }

        System.out.println("\nTestando depósito válido...");
        contaCorrente.depositar(new BigDecimal("750.50"));
        System.out.println("Depósito aceito → " + contaCorrente);
        System.out.println();

        // === Teste de saque com saldo insuficiente ===
        System.out.println("Testando saque acima do saldo...");
        try {
            contaCorrente.sacar(new BigDecimal("10000"));
        } catch (IllegalArgumentException e) {
            System.out.println("Saque bloqueado → " + e.getMessage());
        }

        // === Teste de saque válido ===
        System.out.println("\nTestando saque válido...");
        contaCorrente.sacar(new BigDecimal("400.75"));
        System.out.println("Saque aceito → " + contaCorrente);
        System.out.println();

        // === Criando uma segunda conta (poupança) para o mesmo cliente ===
        System.out.println("Criando conta poupança para o mesmo cliente...");
        Conta contaPoupanca = new Conta(matheus, new BigDecimal("5000.00"));
        System.out.println("Conta poupança criada → " + contaPoupanca);
        System.out.println("Total de contas do cliente " + matheus.getNome() + ": " + matheus.getContas().size());
        System.out.println();

        // === Resumo final do cliente ===
        System.out.println("=== RESUMO DO CLIENTE ===");
        System.out.println("Nome: " + matheus.getNome());
        System.out.println("CPF: " + matheus.getCpf());
        System.out.println("Total de contas: " + matheus.getContas().size());
        matheus.getContas().forEach(conta ->
                System.out.println("  • Conta ID " + conta.getId() + " → Saldo: R$ " +
                        conta.getSaldo().setScale(2, RoundingMode.HALF_EVEN))
        );
    }
}
