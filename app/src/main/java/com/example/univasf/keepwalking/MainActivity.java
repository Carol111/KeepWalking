package com.example.univasf.keepwalking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

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

        //TESTE DB

        DbHelper dbHelper = new DbHelper(this);

        Caminhada c1 = new Caminhada("Mar 16, 2017",200,10,1,100);
        Caminhada c2 = new Caminhada("Mar 17, 2017",200,10,1,100);
        Caminhada c3 = new Caminhada("Mar 18, 2017",200,10,1,100);
        Caminhada c4 = new Caminhada("Mar 19, 2017",200,10,1,100);
        Caminhada c5 = new Caminhada("Mar 20, 2017",200,10,1,100);
        Caminhada c6 = new Caminhada("Mar 21, 2017",200,10,1,100);
        Caminhada c7 = new Caminhada("Mar 22, 2017",200,10,1,100);
        Caminhada c8 = new Caminhada("Mar 23, 2017",200,10,1,100);

        dbHelper.insertCaminhada(c1);
        dbHelper.insertCaminhada(c2);
        dbHelper.insertCaminhada(c3);
        dbHelper.insertCaminhada(c4);
        dbHelper.insertCaminhada(c5);
        dbHelper.insertCaminhada(c6);
        dbHelper.insertCaminhada(c7);
        dbHelper.insertCaminhada(c8);

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
                        finish();
                        return true;}
                    case R.id.action_help:{
                        Intent help = new Intent(MainActivity.this, HelpActivity.class);
                        startActivity(help);
                        finish();
                        return true;}
                    case R.id.action_history:{
                        Intent history = new Intent(MainActivity.this, HistoryActivity.class);
                        startActivity(history);
                        finish();
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

                //Chamar funcoes

                //funcPassos();
                //funcTempo();
                //funcDistancia();
                //funcVelocidade();
                //funcCalorias();
                return;
            }
            case 'P':{
                btFlag = 'C';
                btIniciar.setText(R.string.cont);
                return;
            }
            default:
                return;
        }
    }

    public void clickZerar(View v){
        btFlag = 'I';
        btIniciar.setText(R.string.inic);
        // inserir dados em um objeto da classe caminhada e inserir o objeto na lista
    }
}