package org.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Animal {
    private int id;
    private int id_usuario;
    private String nome;
    private String especie;
    private int idade;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Animal(){}

    public Animal(int id, int id_usuario, String nome, String especie, int idade) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", id_usuario=" + id_usuario +
                ", nome='" + nome + '\'' +
                ", especie='" + especie + '\'' +
                ", idade=" + idade +
                '}';
    }
}
