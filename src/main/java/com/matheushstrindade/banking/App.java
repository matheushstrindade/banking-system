package com.matheushstrindade.banking;

import com.matheushstrindade.banking.model.Conta;
import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        Conta c1 = new Conta(new BigDecimal("1000.00"));
        System.out.println("Conta criada: " + c1 + "\n");

        // Testes de depósito
        System.out.println("Testando depósito com valor negativo...");
        try {
            c1.depositar(new BigDecimal("-5000"));
        } catch (IllegalArgumentException e) {
            System.out.println("Depósito recusado → " + e.getMessage());
        }

        System.out.println("\nTestando depósito válido...");
        c1.depositar(new BigDecimal("500.00"));
        System.out.println("Depósito aceito → " + c1);

        // Teste de saque com saldo insuficiente
        System.out.println("\nTestando saque com saldo insuficiente...");
        try {
            c1.sacar(new BigDecimal("5000"));
        } catch (IllegalArgumentException e) {
            System.out.println("Saque bloqueado: " + e.getMessage());
        }

        // Teste de saque válido
        System.out.println("\nTestando saque válido...");
        c1.sacar(new BigDecimal("300"));
        System.out.println("Saque aceito → " + c1);
    }
}
