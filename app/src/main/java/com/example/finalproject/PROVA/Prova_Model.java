package com.example.finalproject.PROVA;

public class Prova_Model {

    public String ID;
    public String NOME;

    public Prova_Model(String ID, String NOME)   {

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
