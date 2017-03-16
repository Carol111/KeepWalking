package com.example.univasf.keepwalking;

public class Caminhada {
    private String data;
    private int passos;
    private int distancia;
    private int velocidade;
    private int calorias;

    public Caminhada(){}

    public Caminhada(String data, int passos, int distancia, int velocidade, int calorias){
        this.data = data;
        this.passos = passos;
        //inserir tempo
        this.distancia = distancia;
        this.velocidade = velocidade;
        this.calorias = calorias;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getPassos() {
        return passos;
    }

    public void setPassos(int passos) {
        this.passos = passos;
    }

    //inserir tempo

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public void add (int newPassos, int newDistancia, int newVelocidade, int newCalorias){
        setPassos(getPassos() + newPassos);
        //inserir tempo
        setDistancia(getDistancia() + newDistancia);
        setVelocidade((getVelocidade() + newVelocidade)/2);
        setCalorias(getCalorias() + newCalorias);
    }

    @Override
    public String toString() {
        //inserir tempo
        return data + "\n" + passos + " passos | " + distancia + " m | " + velocidade + " m/s | " + calorias + " kcal";
    }

}
