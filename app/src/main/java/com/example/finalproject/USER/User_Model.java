package com.example.finalproject.USER;

public class User_Model {

    public String id, nome, password, email, number, nif/*, tipo*/;

    public User_Model(String id, String nome, String password, String email, String number, String nif/*, String tipo*/)
    {
        this.id = id;
        this.nome = nome;
        this.password = password;
        this.email = email;
        this.number = number;
        this.nif = nif;
        //this.tipo = tipo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {this.id = id;}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {this.number = number;}

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {this.nif = nif;}

  /*  public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {this.tipo = tipo;}
*/
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {this.nome = nome;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {this.password = password;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {this.email = email;}


}
