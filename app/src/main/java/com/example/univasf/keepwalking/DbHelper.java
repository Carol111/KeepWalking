package com.example.univasf.keepwalking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

// AUXILIADOR DO BANCO DE DADOS

public class DbHelper extends SQLiteOpenHelper {

    private static final String NOME_BASE = "MinhasCaminhadas";
    private static final int VERSAO_BASE = 1;

    public DbHelper(Context context) {
        super(context, NOME_BASE, null, VERSAO_BASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTabelaCaminhada = "CREATE TABLE caminhada("
                + "data TEXT PRIMARY KEY,"
                + "passos INTEGER,"
                + "tempo REAL,"
                + "distancia INTEGER,"
                + "velocidade INTEGER,"
                + "calorias INTEGER"
                + ")";
        db.execSQL(sqlCreateTabelaCaminhada);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlCreateTabelaCaminhada = "DROP TABLE caminhada";
        db.execSQL(sqlCreateTabelaCaminhada);

        onCreate(db);
    }

    public void limpar (){

        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "DELETE FROM caminhada";
        db.execSQL(sqlDelete);
    }

    public void insertCaminhada(Caminhada caminhada){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        String sqlSearchData = "SELECT data FROM caminhada WHERE data = '" + caminhada.getData() + "'";
        Cursor c = db.rawQuery(sqlSearchData, null);

       if(c.moveToFirst()){
            Log.i("Test Banco", "data encontrada");
            cv.put("data", caminhada.getData());
            cv.put("passos", caminhada.getPassos());
            cv.put("tempo", caminhada.getbMilliseconds());
            cv.put("distancia", caminhada.getDistancia());
            cv.put("velocidade", caminhada.getVelocidade());
            cv.put("calorias", caminhada.getCalorias());

        }else {
            Log.i("Test Banco", "data NAO encontrada");
            cv.put("data", caminhada.getData());
            cv.put("passos", caminhada.getPassos());
            cv.put("tempo", caminhada.getbMilliseconds());
            cv.put("distancia", caminhada.getDistancia());
            cv.put("velocidade", caminhada.getVelocidade());
            cv.put("calorias", caminhada.getCalorias());
        }

        db.insert("caminhada", null, cv);

        db.close();
    }

    public List<Caminhada> selectTodasAsCaminhadas(){

        List<Caminhada> listCaminhadas = new ArrayList<Caminhada>();

        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectTodasCaminhadas = "SELECT * FROM caminhada";

        Cursor c = db.rawQuery(sqlSelectTodasCaminhadas, null);

        if(c.moveToFirst()){
            do{
                Caminhada caminhada = new Caminhada();
                caminhada.setData(c.getString(0));
                caminhada.setPassos(c.getInt(1));
                caminhada.setbMilliseconds(c.getLong(2));
                caminhada.setDistancia(c.getInt(3));
                caminhada.setVelocidade(c.getInt(4));
                caminhada.setCalorias(c.getInt(5));

                listCaminhadas.add(caminhada);
            }while(c.moveToNext());
        }

        db.close();
        return listCaminhadas;
    }
}
