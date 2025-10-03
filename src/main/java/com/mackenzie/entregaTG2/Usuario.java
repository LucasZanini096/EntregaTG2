/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor..
 */
//testando com a turma 6N
//testando com a turma 6P antes de sextar
package com.mackenzie.entregaTG2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

// Classe Usuário

public class Usuario {
    private String nome;
    private String email;
    private String senhaHash;
    private Map<String, Object> preferenciasAlimentares;
    private String receita;

    // Construtor vazio
    public Usuario() {
        this.preferenciasAlimentares = new HashMap<>();
    }

    // Construtor completo (sem senha em texto plano)
    public Usuario(String nome, String email, String senhaHash, Map<String, Object> preferenciasAlimentares, String receita) {
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.preferenciasAlimentares = preferenciasAlimentares != null ? preferenciasAlimentares : new HashMap<>();
        this.receita = receita;
    }

    // Método para cadastrar usuário
    public void cadastrar(String nome, String email, String senha, Map<String, Object> preferenciasAlimentares, String receita) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        if (senha == null || senha.length() < 6) {
            throw new IllegalArgumentException("Senha deve ter no mínimo 6 caracteres");
        }

        this.nome = nome;
        this.email = email;
        this.senhaHash = gerarHash(senha);
        this.preferenciasAlimentares = preferenciasAlimentares != null ? preferenciasAlimentares : new HashMap<>();
        this.receita = receita;
    }

    // Método para autenticar usuário
    public boolean autenticar(String email, String senha) {
        if (email == null || senha == null) {
            return false;
        }

        String hashSenhaFornecida = gerarHash(senha);
        return this.email.equals(email) && this.senhaHash.equals(hashSenhaFornecida);
    }

    // Método auxiliar para gerar hash SHA-256
    private String gerarHash(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(senha.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public Map<String, Object> getPreferenciasAlimentares() {
        return preferenciasAlimentares;
    }

    public void setPreferenciasAlimentares(Map<String, Object> preferenciasAlimentares) {
        this.preferenciasAlimentares = preferenciasAlimentares;
    }

    public String getReceita() {
        return receita;
    }

    public void setReceita(String receita) {
        this.receita = receita;
    }
}




