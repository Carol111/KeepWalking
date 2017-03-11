package com.example.univasf.keepwalking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

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

        //TextView textSobre = (TextView) findViewById(R.id.textSobre);
        //changeFont(textSobre, "fonts/stone.TTF");

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

        //Button btVoltar = (Button) findViewById(R.id.btVoltar);
        //changeFont(btVoltar, "fonts/annabelle.ttf");

    }

    // Menu Popup
    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.show();
    }

    // Acoes do Menu Popup
    public boolean onMenuItemClick (MenuItem item){
        switch (item.getItemId()){
            case R.id.action_about:
                return true;
            case R.id.action_help:
                return true;
            case R.id.action_contact:
                return true;
            default:
                return false;
        }
    }
}