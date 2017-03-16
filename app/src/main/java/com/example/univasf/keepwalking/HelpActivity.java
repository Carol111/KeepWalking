package com.example.univasf.keepwalking;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    // Metodo para mudar a fonte
    private void changeFont(TextView tv, String fonte) {
        Typeface type = Typeface.createFromAsset(getAssets(), fonte);
        tv.setTypeface(type);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // Tittle
        TextView textKeepWalking = (TextView) findViewById(R.id.textKeepWalking);
        TextView textCaminhada = (TextView) findViewById(R.id.textCaminhada);
        changeFont(textKeepWalking, "fonts/stone.TTF");
        changeFont(textCaminhada, "fonts/stone.TTF");

        /* altera fontes */
        TextView textAjuda = (TextView) findViewById(R.id.textAjuda);
        changeFont(textAjuda, "fonts/stone.TTF");

        // Buttons
        Button btVoltar = (Button) findViewById(R.id.btVoltar);
        changeFont(btVoltar, "fonts/annabelle.ttf");

        // Volta para a MainActivity
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(HelpActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        });

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
                        Intent about = new Intent(HelpActivity.this, AboutActivity.class);
                        startActivity(about);
                        finish();
                        return true;}
                    case R.id.action_help:
                        return true;
                    case R.id.action_history:{
                        Intent history = new Intent(HelpActivity.this, HistoryActivity.class);
                        startActivity(history);
                        finish();
                        return true;}
                    default:
                        return false;
                }
            }

        });
    }

}
