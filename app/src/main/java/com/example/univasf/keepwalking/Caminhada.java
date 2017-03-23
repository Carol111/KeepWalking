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
    private long milliseconds;
    private long tempo;

    static final int hora = 3600000;
    static final int min = 60000;
    static final int sec = 1000;

    public Caminhada(){
    }

    public Caminhada(String data, int passos, long tempo, int distancia, int velocidade, int calorias){
        this.data = data;
        this.passos = passos;
        this.tempo = tempo;
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

    public void setTempo(long bMilliseconds){
        this.tempo = bMilliseconds;
    }
    public long getTempo(){
        return tempo;
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

    public void startChronometer (char flag){
        if(flag == 'I'){
            milliseconds=0;
            tempo=0;
        }
        ch.setBase(SystemClock.elapsedRealtime() - milliseconds);
        ch.start();
    }

    public void pauseChronometer(){
        milliseconds = SystemClock.elapsedRealtime() - ch.getBase();
        ch.stop();
    }

    public void clearChronometer (char flag){

        if(flag == 'C'){
            tempo = milliseconds;
        }else tempo = SystemClock.elapsedRealtime() - ch.getBase();

        milliseconds = 0;
        ch.setBase(SystemClock.elapsedRealtime());
        ch.start();
        ch.stop();
    }

    @Override
    public String toString() {
        return data + "\n"
                + passos + " passos | "
                + tempo/hora + "h "
                + (tempo%hora)/min + "m "
                + ((tempo%hora)%min)/sec + "s | "
                + distancia + " m | "
                + velocidade + " m/s | "
                + calorias + " kcal";
    }


}
