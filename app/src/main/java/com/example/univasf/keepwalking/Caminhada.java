package com.example.univasf.keepwalking;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Caminhada{

    //Variáveis armazenadas no BANCO
    private String data;
    private int passos;
    private long tempo;
    private float distancia;
    private double velocidade;
    private double calorias;

    //Auxiliares de PASSOS
    private Sensor mySensor;
    private TextView vPassos;
    private SensorManager sManager;
    private SensorEventListener sListener;
    private int direcao;

    //Auxiliares do TEMPO
    private Chronometer ch;
    private long milliseconds;
    static final int hora = 3600000;
    static final int min = 60000;
    static final int sec = 1000;

    //Auxiliares da DISTÂNCIA
    private TextView vDistancia;

    //Auxiliares da VELOCIDADE
    private TextView vVelociade;

    //Auxiliares da CALORIAS
    private TextView vCaloria;

    //////////////////////////////////////////////////////
    //Construtores

    public Caminhada(){
        milliseconds = 0;
        tempo = 0;
        passos = 0;
        direcao = 1;
        distancia = 0;
    }

    public Caminhada(String data, int passos, long tempo, int distancia, int velocidade, int calorias){
        this.data = data;
        this.passos = passos;
        this.tempo = tempo;
        this.distancia = distancia;
        this.velocidade = velocidade;
        this.calorias = calorias;
    }

    //////////////////////////////////////////////////////
    //Get e Set
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(float calorias) {
        this.calorias = calorias;
    }

    public int getPassos() {
        return passos;
    }

    public void setPassos(int passos) {
        this.passos = passos;
    }

    //////////////////////////////////////////////////////
    //PASSOS

    public void startPassos (final Context context){

        vPassos = (TextView) ((Activity)context).findViewById(R.id.valuePassos);
        vDistancia = (TextView) ((Activity)context).findViewById(R.id.valueDistancia);
        vVelociade = (TextView) ((Activity)context).findViewById(R.id.valueVelocidade);
        vCaloria = (TextView) ((Activity)context).findViewById(R.id.valueCalorias);


        sManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        mySensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[1] > 0) {
                    if (sensorEvent.values[2] > 2.6 && direcao == 1) {
                        direcao = -1;
                        passos++;
                    }
                    if (sensorEvent.values[2] < 1.5 && direcao == -1)
                        if(sensorEvent.values[2] < -0.6 && direcao == -1){
                            direcao = 1;
                            passos++;
                        }
                }
                else{
                    if (sensorEvent.values[2] > 2.6 && direcao == 1) {
                        direcao = -1;
                        passos++;
                    }
                    if (sensorEvent.values[2] < 1.5 && direcao == -1)
                        if(sensorEvent.values[2] < -0.6 && direcao == -1){
                            direcao = 1;
                            passos++;
                        }
                }
                vPassos.setText("" + passos);

                //////////////////////////////////////////////////////
                //DISTANCIA

                distancia = (float) (passos*0.8);
                vDistancia.setText("" + distancia);


                //////////////////////////////////////////////////////
                //VELOCIDADE

                //define nº de casas decimais
                DecimalFormat df = new DecimalFormat("0.00");
                // velocidade em m/s
                velocidade = (1000 * distancia / (SystemClock.elapsedRealtime() - ch.getBase()))/3.6;
                vVelociade.setText("" + df.format(velocidade));

                //////////////////////////////////////////////////////
                //CALORIAS
                calorias = (velocidade/3.6) * 70 * 0.0175 * (SystemClock.elapsedRealtime() - ch.getBase())/60000;
                vCaloria.setText("" + df.format(calorias));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sManager.registerListener(sListener, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void pausePassos (){
        sManager.unregisterListener(sListener);
    }

    public void clearPassos() {
        passos = 0;
        distancia = 0;
        velocidade = 0;
        calorias = 0;
        vPassos.setText("" + passos);
        vDistancia.setText("" + distancia);
        vVelociade.setText("" + velocidade);
        vCaloria.setText("" + calorias);
    }

    //////////////////////////////////////////////////////
    //TEMPO

    public void startChronometer (){
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

    //////////////////////////////////////////////////////

    //Formatação de saída
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
