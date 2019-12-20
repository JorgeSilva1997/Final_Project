package com.example.finalproject.PAVILHAO;

public class Pavilhao_Model {

    public String ID;
    public String NOME;
    public String RUA;
    public String LOCALIDADE;
    public String DISTANCIA;

    public Pavilhao_Model(String ID, String NOME, String RUA, String LOCALIDADE, String DISTANCIA)   {

        this.ID = ID;
        this.NOME = NOME;
        this.RUA = RUA;
        this.LOCALIDADE = LOCALIDADE;
        this.DISTANCIA = DISTANCIA;

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

    public String getRUA()
    {
        return RUA;
    }

    public void setRUA(String RUA) {this.RUA = RUA;}

    public String getLOCALIDADE()
    {
        return LOCALIDADE;
    }

    public void setLOCALIDADE(String LOCALIDADE) {this.LOCALIDADE = LOCALIDADE;}

    public String getDISTANCIA()
    {
        return DISTANCIA;
    }

    public void setDISTANCIA(String DISTANCIA) {this.DISTANCIA = DISTANCIA;}
}
