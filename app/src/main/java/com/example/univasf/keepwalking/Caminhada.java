package com.example.univasf.keepwalking;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.widget.Chronometer;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

import java.text.DecimalFormat;

import android.location.LocationListener;
import android.location.LocationManager;
import android.content.Context;


public class Caminhada {

    //Variáveis armazenadas no BANCO
    private String data;
    private int passos;
    private long tempo;
    private float distancia;
    private float dist2=0;
    private double velocidade;
    private double calorias;

    //Auxiliares de PASSOS
    private Sensor mySensor;
    private TextView vPassos;
    private SensorManager sManager;
    private SensorEventListener sListener;
    private int direcao;
    private LocationManager vlocationManager;
    private LocationListener vlocationListener;


    private long lastUpdate = 0;
    private float last_z;
    private static final int SHAKE_THRESHOLD = 310;

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

    DecimalFormat df;

    //////////////////////////////////////////////////////
    //Construtores

    public Caminhada() {
        milliseconds = 0;
        tempo = 0;
        passos = 0;
        direcao = 1;
        distancia = 0;
        df = new DecimalFormat("0.00");
    }

    public Caminhada(String data, int passos, long tempo, int distancia, int velocidade, int calorias) {
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

    public void setTempo(long bMilliseconds) {
        this.tempo = bMilliseconds;
    }

    public long getTempo() {
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

    public void startPassos(final Context context) {

        vPassos = (TextView) ((Activity) context).findViewById(R.id.valuePassos);
        vDistancia = (TextView) ((Activity) context).findViewById(R.id.valueDistancia);
        vVelociade = (TextView) ((Activity) context).findViewById(R.id.valueVelocidade);
        vCaloria = (TextView) ((Activity) context).findViewById(R.id.valueCalorias);


        sManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        mySensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        vlocationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);

/*
        vlocationListener = new LocationListener() {
            float count=0, dist=0, dist2=0;
            boolean flag=false;
            double lat1, lat2, log1, log2;

            @Override
            public void onLocationChanged(Location location) {
                Location location1= new Location("Location1");
                Location location2= new Location("Location2");

                if(flag==true){
                    location2.setLatitude(location.getLatitude());
                    location2.setLongitude(location.getLongitude());
                    lat2 = 60*location.getLatitude()*1852;
                    log2 = 60*location.getLongitude()*1852;

                    dist2 += location1.distanceTo(location2);
                    vCaloria.setText("" + df.format(dist2));
                    count--;

                }
                else {count++;}

                if(count==1){
                    location1.setLatitude(location.getLatitude());
                    location1.setLongitude(location.getLongitude());
                    lat1 = 60*location.getLatitude()*1852;
                    log1 = 60*location.getLongitude()*1852;


                    flag = true;
                }
                else {flag= false;}

                    dist += Math.sqrt(((lat1-lat2)*(lat1-lat2)) + (log2 - log1)*(log2 - log1));
                    vVelociade.setText("" + df.format(dist));


            }


            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }

        };
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        vlocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 0, vlocationListener);
*/

        sListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                    float z = sensorEvent.values[2];

                    long curTime = System.currentTimeMillis();

                    if ((curTime - lastUpdate) > 100) {
                        long diffTime = (curTime - lastUpdate);
                        lastUpdate = curTime;

                        float speed = Math.abs(z - last_z)/ diffTime * 10000;

                        if (speed > SHAKE_THRESHOLD) {
                            passos++;
                        }
                        last_z = z;
                    }
                }

                vPassos.setText("" + passos);

                //////////////////////////////////////////////////////
                //DISTANCIA

                distancia = (float) (passos*0.8);
                vDistancia.setText("" + df.format(distancia));


                //////////////////////////////////////////////////////
                //VELOCIDADE
                // velocidade em m/s
                velocidade = (1000 * distancia / (SystemClock.elapsedRealtime() - ch.getBase()))*3.6;
                vVelociade.setText("" + df.format(velocidade));

                //////////////////////////////////////////////////////
                //CALORIAS
                calorias = (velocidade*3.6) * 70 * 0.0175 * (SystemClock.elapsedRealtime() - ch.getBase())/60000;
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
                + df.format(distancia) + " m \n"
                + df.format(velocidade) + " km/h | "
                + df.format(calorias) + " cal";
    }

}
