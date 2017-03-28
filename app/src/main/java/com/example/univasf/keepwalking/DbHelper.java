package com.example.univasf.keepwalking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

// AUXILIADOR DO BANCO DE DADOS

public class DbHelper extends SQLiteOpenHelper {

    private static final String NOME_BASE = "MinhasCaminhadas";
    private static final int VERSAO_BASE = 1;

    public DbHelper(Context context) {
        super(context, NOME_BASE, null, VERSAO_BASE);
    }

    //Criar tabela
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTabelaCaminhada = "CREATE TABLE caminhada("
                + "data TEXT PRIMARY KEY,"
                + "passos INTEGER,"
                + "tempo REAL,"
                + "distancia REAL,"
                + "velocidade REAL,"
                + "calorias REAL"
                + ")";
        db.execSQL(sqlCreateTabelaCaminhada);

    }

    //Upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlCreateTabelaCaminhada = "DROP TABLE caminhada";
        db.execSQL(sqlCreateTabelaCaminhada);

        onCreate(db);
    }

    //Limpar banco de dados
    public void limpar (){

        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "DELETE FROM caminhada";
        db.execSQL(sqlDelete);
    }

    //Inserir nova linha na tabela
    public void insertCaminhada(Caminhada caminhada){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        //verificar se já existe uma linha na tabela para a data atual
        String sqlSearchData = "SELECT * FROM caminhada WHERE data = '" + caminhada.getData() + "'";
        Cursor c = db.rawQuery(sqlSearchData, null);

       if(c.moveToFirst()){

           //Se já existir ATUALIZAR

            cv.put("passos", c.getLong(1) + caminhada.getPassos());
            cv.put("tempo", c.getLong(2) + caminhada.getTempo());
            cv.put("distancia", c.getFloat(3) + caminhada.getDistancia());
            cv.put("velocidade", caminhada.getVelocidade());
            cv.put("calorias", caminhada.getCalorias());

            db.update("caminhada", cv, "data= '" + caminhada.getData() + "'", null);

        }else {

           //Se não existir INSERIR

            cv.put("data", caminhada.getData());
            cv.put("passos", caminhada.getPassos());
            cv.put("tempo", caminhada.getTempo());
            cv.put("distancia", caminhada.getDistancia());
            cv.put("velocidade", caminhada.getVelocidade());
            cv.put("calorias", caminhada.getCalorias());

           db.insert("caminhada", null, cv);
        }

        cv.clear();
        db.close();
    }

    //Inserir linhas da tabela na lista a ser mostrada no HISTÓRICO
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
                caminhada.setTempo(c.getLong(2));
                caminhada.setDistancia(c.getFloat(3));
                caminhada.setVelocidade(c.getFloat(4));
                caminhada.setCalorias(c.getFloat(5));

                listCaminhadas.add(caminhada);
            }while(c.moveToNext());
        }

        db.close();
        return listCaminhadas;
    }
}
