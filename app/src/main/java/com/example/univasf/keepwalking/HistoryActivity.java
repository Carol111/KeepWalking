package com.example.univasf.keepwalking;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    Button btTotal;
    Button btLimpar;

    DbHelper dbHelper;
    List<Caminhada> listaCaminhada;

    private ListView listCaminhada;

    // Metodo para mudar a fonte
    private void changeFont(TextView tv, String fonte) {
        Typeface type = Typeface.createFromAsset(getAssets(), fonte);
        tv.setTypeface(type);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        /* altera fontes */
        TextView textHistory = (TextView) findViewById(R.id.textHistory);
        changeFont(textHistory, "fonts/stone.TTF");

        // Tittle
        TextView textKeepWalking = (TextView) findViewById(R.id.textKeepWalking);
        TextView textCaminhada = (TextView) findViewById(R.id.textCaminhada);
        changeFont(textKeepWalking, "fonts/stone.TTF");
        changeFont(textCaminhada, "fonts/stone.TTF");

        // Buttons
        Button btVoltar = (Button) findViewById(R.id.btVoltar);
        btTotal = (Button) findViewById(R.id.btTotal);
        btLimpar = (Button) findViewById(R.id.btLimpar);
        changeFont(btVoltar, "fonts/annabelle.ttf");
        changeFont(btTotal, "fonts/annabelle.ttf");
        changeFont(btLimpar, "fonts/annabelle.ttf");

        // Volta para a MainActivity
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //TESTE DB

        dbHelper = new DbHelper(this);

        listCaminhada = (ListView) findViewById(R.id.listCaminhada);
        listaCaminhada = dbHelper.selectTodasAsCaminhadas();

        ArrayAdapter<Caminhada> adp = new ArrayAdapter<Caminhada>(this, android.R.layout.simple_list_item_1, listaCaminhada);

        listCaminhada.setAdapter(adp);

    }

    // Menu Popup
    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.inflate(R.menu.menu_main);
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            // Acoes do Menu Popup
            @Override
            public boolean onMenuItemClick (MenuItem item){
                switch (item.getItemId()){
                    case R.id.action_about:{
                        Intent about = new Intent(HistoryActivity.this, AboutActivity.class);
                        startActivity(about);
                        finish();
                        return true;}
                    case R.id.action_help:{
                        Intent help = new Intent(HistoryActivity.this, HelpActivity.class);
                        startActivity(help);
                        finish();
                        return true;}
                    case R.id.action_history:
                        return true;
                    default:
                        return false;
                }
            }

        });
    }

    public void limparDb(View v){
        dbHelper.limpar();
        listaCaminhada.removeAll(listaCaminhada);
        listaCaminhada.clear();
        listaCaminhada = dbHelper.selectTodasAsCaminhadas();

        ArrayAdapter<Caminhada> adp = new ArrayAdapter<Caminhada>(this, android.R.layout.simple_list_item_1, listaCaminhada);

        listCaminhada.setAdapter(adp);
    }

    public void totalDb(View v) {

        int passos = 0;
        //inserir tempo
        int distancia = 0;
        int velocidade = 0;
        int calorias = 0;
        int n = 0;

        if (listaCaminhada.isEmpty()) {

            // Caixa de diálogo
            AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this, R.style.Theme_AppCompat_Dialog_Alert);

            //inserir tempo
            builder.setMessage("Nenhum dado de caminhada encontrado");
            builder.setTitle("Total");
            builder.setPositiveButton("OK", null);
            builder.show();

        } else {

            for (Iterator iterator = listaCaminhada.iterator(); iterator.hasNext(); ) {
                Caminhada caminhada = (Caminhada) iterator.next();

                passos = passos + caminhada.getPassos();
                //inserir tempo
                distancia = distancia + caminhada.getDistancia();
                velocidade = velocidade + caminhada.getVelocidade();
                calorias = calorias + caminhada.getCalorias();
                n++;
            }
            velocidade = velocidade / n;

            // Caixa de diálogo
            AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this, R.style.Theme_AppCompat_Dialog_Alert);

            //inserir tempo
            builder.setMessage("Passos: " + passos + "\nDistância: " + distancia + " m \nVelocidade média: " + velocidade + "m/s \nCalorias: " + calorias + " kcal");
            builder.setTitle("Total");
            builder.setPositiveButton("OK", null);
            builder.show();
        }
    }

}

