package com.example.univasf.keepwalking;

import android.os.SystemClock;
import android.widget.Chronometer;

public class Caminhada {
    private String data;
    private int passos;
    private int distancia;
    private int velocidade;
    private int calorias;

    private Chronometer ch;
    private long milliseconds=0;
    private long bMilliseconds;

    public Caminhada(){
    }

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

    public void setCh(Chronometer ch) {
        this.ch = ch;
    }

    public void setbMilliseconds(long bMilliseconds){
        this.bMilliseconds = bMilliseconds;
    }
    public long getbMilliseconds(){
        return bMilliseconds;
    }

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

    public void startChronometer (){
        ch.setBase(SystemClock.elapsedRealtime() - milliseconds);
        ch.start();
    }

    public void pauseChronometer(){
        milliseconds = SystemClock.elapsedRealtime() - ch.getBase();
        ch.stop();
    }

    public void clearChronometer (){
        bMilliseconds = SystemClock.elapsedRealtime() - ch.getBase();
        milliseconds = 0;
        ch.setBase(SystemClock.elapsedRealtime());
        ch.start();
        ch.stop();
    }

    @Override
    public String toString() {
        //inserir tempo
        return data + "\n" + passos + " passos | " + distancia + " m | " + velocidade + " m/s | " + calorias + " kcal";
    }


}
