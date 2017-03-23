package com.example.univasf.keepwalking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView textKeepWalking;
    TextView textCaminhada;

    TextView textPassos;
    TextView textTempo;
    TextView textDistancia;
    TextView textVelocidade;
    TextView textCalorias;

    Button btIniciar;
    Button btZerar;

    char btFlag;
    DbHelper dbHelper;

    String currentDateTimeString;

    Caminhada caminhada= new Caminhada();

    // Metodo para mudar a fonte
    private void changeFont(TextView tv, String fonte) {
        Typeface type = Typeface.createFromAsset(getAssets(), fonte);
        tv.setTypeface(type);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btFlag = 'I';

        /* altera fontes */
        // Tittle
        textKeepWalking = (TextView) findViewById(R.id.textKeepWalking);
        textCaminhada = (TextView) findViewById(R.id.textCaminhada);
        changeFont(textKeepWalking, "fonts/stone.TTF");
        changeFont(textCaminhada, "fonts/stone.TTF");

        // Circle Texts
        textPassos = (TextView) findViewById(R.id.textPassos);
        textTempo = (TextView) findViewById(R.id.textTempo);
        textDistancia = (TextView) findViewById(R.id.textDistancia);
        textVelocidade = (TextView) findViewById(R.id.textVelocidade);
        textCalorias = (TextView) findViewById(R.id.textCalorias);
        changeFont(textPassos, "fonts/stone.TTF");
        changeFont(textTempo, "fonts/stone.TTF");
        changeFont(textDistancia, "fonts/stone.TTF");
        changeFont(textVelocidade, "fonts/stone.TTF");
        changeFont(textCalorias, "fonts/stone.TTF");

        // Buttons
        btIniciar = (Button) findViewById(R.id.btIniciar);
        btZerar = (Button) findViewById(R.id.btZerar);
        changeFont(btIniciar, "fonts/annabelle.ttf");
        changeFont(btZerar, "fonts/annabelle.ttf");

        //Values
        caminhada.setCh((Chronometer) findViewById(R.id.chronometer));

        //TESTE DB

        dbHelper = new DbHelper(this);
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
                        Intent about = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(about);
                        return true;}
                    case R.id.action_help:{
                        Intent help = new Intent(MainActivity.this, HelpActivity.class);
                        startActivity(help);
                        return true;}
                    case R.id.action_history:{
                        Intent history = new Intent(MainActivity.this, HistoryActivity.class);
                        startActivity(history);
                        return true;}
                    default:
                        return false;
                }
            }

        });
    }


    public void clickIniciar(View v){

        switch (btFlag){
            case 'I':
            case 'C':{
                btFlag = 'P';
                btIniciar.setText(R.string.pausar);

                currentDateTimeString = DateFormat.getDateInstance().format(new Date());
                caminhada.setData(currentDateTimeString);

                //Chamar funcoes
                caminhada.startChronometer (btFlag);
                caminhada.startPassos(this);
                //funcTempo();
                //funcDistancia();
                //funcVelocidade();
                //funcCalorias();
                return;
            }
            case 'P':{
                btFlag = 'C';
                btIniciar.setText(R.string.cont);

                caminhada.pauseChronometer();
                caminhada.pausePassos();

                return;
            }
            default:
                return;
        }
    }

    public void clickZerar(View v){

        btIniciar.setText(R.string.inic);
        caminhada.clearChronometer(btFlag);

        /*/ Caixa de diálogo de TESTES
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.Theme_AppCompat_Dialog_Alert);
        builder.setMessage("passos: " + caminhada.getContador());
        builder.setPositiveButton("OK", null);
        builder.show();
        ////////////////////////////*/

        if(btFlag != 'I') dbHelper.insertCaminhada(caminhada);

        caminhada.clearPassos();
        btFlag = 'I';

    }
}