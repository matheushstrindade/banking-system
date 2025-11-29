package com.matheushstrindade.banking;

import com.matheushstrindade.banking.model.Conta;
import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        Conta c1 = new Conta(new BigDecimal("1000.00"));
        System.out.println("Conta criada: " + c1 + "\n");

        // Teste de valor negativo
        try {
            c1.depositar(new BigDecimal("-5000"));
        } catch (IllegalArgumentException e) {
            System.out.println("Negativo bloqueado: " + e.getMessage());
        }

        // Teste de valor válido
        c1.depositar(new BigDecimal("500.75"));
        System.out.println("\nDepósito válido → " + c1);

        // Teste de saque insuficiente
        try {
            c1.sacar(new BigDecimal("5000"));
        } catch (IllegalArgumentException e) {
            System.out.println("Saque bloqueado: " + e.getMessage());
        }

        // Teste de saque válido
        c1.sacar(new BigDecimal("300"));
        System.out.println("Saque válido → " + c1);
    }
}
