package com.example.finalproject.CONVOCATORIA;

public class Convocatoria_Model {

    public String id_convocatoria;
    public String datahora;
    public String prova_id;
    public String prova_nome;
    public String escalao_id;
    public String escalao_nome;
    public String equipa_visitada_id;
    public String equipa_visitada_nome;
    public String equipa_visitante_id;
    public String equipa_visitante_nome;
    public String pavilhao_id;
    public String pavilhao_nome;
    public String user_id;
    public String user_nome;

    public Convocatoria_Model(String id_convocatoria, String datahora, String prova_id, String prova_nome,
                              String escalao_id, String escalao_nome, String equipa_visitada_id,
                              String equipa_visitada_nome, String equipa_visitante_id, String equipa_visitante_nome,
                              String pavilhao_id, String pavilhao_nome, String user_id, String user_nome)   {

        this.id_convocatoria = id_convocatoria;
        this.datahora = datahora;
        this.prova_id= prova_id;
        this.prova_nome= prova_nome;
        this.escalao_id= escalao_id;
        this.escalao_nome=escalao_nome;
        this.equipa_visitada_id=equipa_visitada_id;
        this.equipa_visitada_nome=equipa_visitada_nome;
        this.equipa_visitante_id=equipa_visitante_id;
        this.equipa_visitante_nome=equipa_visitante_nome;
        this.pavilhao_id=pavilhao_id;
        this.pavilhao_nome=pavilhao_nome;
        this.user_id=user_id;
        this.user_nome=user_nome;
    }

    public String getId_convocatoria() {
        return id_convocatoria;
    }

    public void setId_convocatoria(String id_convocatoria) {
        this.id_convocatoria = id_convocatoria;
    }

    public String getDatahora() {
        return datahora;
    }

    public void setDatahora(String datahora) {
        this.datahora = datahora;
    }

    public String getProva_id() {
        return prova_id;
    }

    public void setProva_id(String prova_id) {
        this.prova_id = prova_id;
    }

    public String getProva_nome() {
        return prova_nome;
    }

    public void setProva_nome(String prova_nome) {
        this.prova_nome = prova_nome;
    }

    public String getEscalao_id() {
        return escalao_id;
    }

    public void setEscalao_id(String escalao_id) {
        this.escalao_id = escalao_id;
    }

    public String getEscalao_nome() {
        return escalao_nome;
    }

    public void setEscalao_nome(String escalao_nome) {
        this.escalao_nome = escalao_nome;
    }

    public String getEquipa_visitada_id() {
        return equipa_visitada_id;
    }

    public void setEquipa_visitada_id(String equipa_visitada_id) {
        this.equipa_visitada_id = equipa_visitada_id;
    }

    public String getEquipa_visitada_nome() {
        return equipa_visitada_nome;
    }

    public void setEquipa_visitada_nome(String equipa_visitada_nome) {
        this.equipa_visitada_nome = equipa_visitada_nome;
    }

    public String getEquipa_visitante_id() {
        return equipa_visitante_id;
    }

    public void setEquipa_visitante_id(String equipa_visitante_id) {
        this.equipa_visitante_id = equipa_visitante_id;
    }

    public String getEquipa_visitante_nome() {
        return equipa_visitante_nome;
    }

    public void setEquipa_visitante_nome(String equipa_visitante_nome) {
        this.equipa_visitante_nome = equipa_visitante_nome;
    }

    public String getPavilhao_id() {
        return pavilhao_id;
    }

    public void setPavilhao_id(String pavilhao_id) {
        this.pavilhao_id = pavilhao_id;
    }

    public String getPavilhao_nome() {
        return pavilhao_nome;
    }

    public void setPavilhao_nome(String pavilhao_nome) {
        this.pavilhao_nome = pavilhao_nome;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_nome() {
        return user_nome;
    }

    public void setUser_nome(String user_nome) {
        this.user_nome = user_nome;
    }
}
