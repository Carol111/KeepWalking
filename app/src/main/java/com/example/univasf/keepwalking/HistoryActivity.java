package com.example.univasf.keepwalking;

import android.content.DialogInterface;
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

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    Button btTotal;
    Button btLimpar;

    DbHelper dbHelper;
    List<Caminhada> listaCaminhada;

    private ListView listCaminhada;

    static final int hora = 3600000;
    static final int min = 60000;
    static final int sec = 1000;

    DecimalFormat df;

    // Metodo para mudar a fonte
    private void changeFont(TextView tv, String fonte) {
        Typeface type = Typeface.createFromAsset(getAssets(), fonte);
        tv.setTypeface(type);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        df = new DecimalFormat("0.00");

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

        //Exibir o que já existe no banco de dados
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

    //Limpar o HISTÓRICO
    public void limparDb(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this, R.style.Theme_AppCompat_Dialog_Alert);

        builder.setMessage("Tem certeza que deseja limpar todo o histórico?");
        builder.setTitle("Limpar");
        builder.setNegativeButton("NÃO", null);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbHelper.limpar();
                listaCaminhada.removeAll(listaCaminhada);
                listaCaminhada.clear();
                listaCaminhada = dbHelper.selectTodasAsCaminhadas();
                ArrayAdapter<Caminhada> adp = new ArrayAdapter<Caminhada>(HistoryActivity.this, android.R.layout.simple_list_item_1, listaCaminhada);

                listCaminhada.setAdapter(adp);
            }

        });
        builder.show();
    }

    //Exibir TOTAL geral
    public void totalDb(View v) {

        int passos = 0;
        long tempo = 0;
        float distancia = 0;
        float velocidade = 0;
        float  calorias = 0;
        int n = 0;

        if (listaCaminhada.isEmpty()) {
            // Se não existir nenhum histórico de caminhadas

            // Caixa de diálogo
            AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this, R.style.Theme_AppCompat_Dialog_Alert);

            builder.setMessage("Nenhum histórico encontrado");
            builder.setTitle("Total");
            builder.setPositiveButton("OK", null);
            builder.show();

        } else {

            // Se existir histórico

            for (Iterator iterator = listaCaminhada.iterator(); iterator.hasNext(); ) {
                Caminhada caminhada = (Caminhada) iterator.next();

                passos += caminhada.getPassos();
                tempo += caminhada.getTempo();
                distancia += caminhada.getDistancia();
                velocidade += caminhada.getVelocidade();
                calorias += caminhada.getCalorias();
                n++;
            }
            velocidade = velocidade / n;

            // Caixa de diálogo com o TOTAL geral
            AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this, R.style.Theme_AppCompat_Dialog_Alert);

            builder.setMessage("Passos: " + passos
                    + "\nTempo: " + tempo/hora + "h "
                    + (tempo%hora)/min + "m "
                    + ((tempo%hora)%min)/sec + "s"
                    + "\nDistância: " + df.format(distancia)
                    + " m \nVelocidade média: " + df.format(velocidade)
                    + " km/h \nCalorias: " + df.format(calorias) + " cal");
            builder.setTitle("Total");
            builder.setPositiveButton("OK", null);
            builder.show();
        }
    }

}

