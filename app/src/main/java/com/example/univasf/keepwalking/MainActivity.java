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

    // Metodo para mudar a fonte
    private void changeFont(TextView tv, String fonte) {
        Typeface type = Typeface.createFromAsset(getAssets(), fonte);
        tv.setTypeface(type);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* altera fontes */
        // Tittle
        TextView textKeepWalking = (TextView) findViewById(R.id.textKeepWalking);
        TextView textCaminhada = (TextView) findViewById(R.id.textCaminhada);
        changeFont(textKeepWalking, "fonts/stone.TTF");
        changeFont(textCaminhada, "fonts/stone.TTF");

        // Circle Texts
        TextView textPassos = (TextView) findViewById(R.id.textPassos);
        TextView textTempo = (TextView) findViewById(R.id.textTempo);
        TextView textDistancia = (TextView) findViewById(R.id.textDistancia);
        TextView textVelocidade = (TextView) findViewById(R.id.textVelocidade);
        TextView textCalorias = (TextView) findViewById(R.id.textCalorias);
        changeFont(textPassos, "fonts/stone.TTF");
        changeFont(textTempo, "fonts/stone.TTF");
        changeFont(textDistancia, "fonts/stone.TTF");
        changeFont(textVelocidade, "fonts/stone.TTF");
        changeFont(textCalorias, "fonts/stone.TTF");

        // Buttons
        Button btIniciar = (Button) findViewById(R.id.btIniciar);
        Button btZerar = (Button) findViewById(R.id.btZerar);
        changeFont(btIniciar, "fonts/annabelle.ttf");
        changeFont(btZerar, "fonts/annabelle.ttf");

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
                    case R.id.action_history:
                        return true;
                    default:
                        return false;
                }
            }

        });
    }


}