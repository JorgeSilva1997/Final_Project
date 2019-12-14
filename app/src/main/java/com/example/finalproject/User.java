package com.example.finalproject;

public class User {

    private int id, number, nif, tipo;
    private String nome, password, email;

    public User(int id, int number, int nif, int tipo, String nome, String password, String email)
    {
        this.id = id;
        this.number = number;
        this.nif = nif;
        this.tipo = tipo;
        this.nome = nome;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public int getNif() {
        return nif;
    }

    public int getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


}
