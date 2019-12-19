package com.example.finalproject.ESCALOES;

public class Escalao_Model {
    public String ID;
    public String NOME;

    public Escalao_Model(String ID, String NOME)   {

        this.ID = ID;
        this.NOME = NOME;

    }

    public String getID()
    {
        return ID;
    }

    public void setID(String ID) {this.ID = ID;}

    public String getNOME()
    {
        return NOME;
    }

    public void setNOME(String NOME) {this.NOME = NOME;}
}

