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

public class AboutActivity extends AppCompatActivity {

    // Metodo para mudar a fonte
    private void changeFont(TextView tv, String fonte) {
        Typeface type = Typeface.createFromAsset(getAssets(), fonte);
        tv.setTypeface(type);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Tittle
        TextView textKeepWalking = (TextView) findViewById(R.id.textKeepWalking);
        TextView textCaminhada = (TextView) findViewById(R.id.textCaminhada);
        changeFont(textKeepWalking, "fonts/stone.TTF");
        changeFont(textCaminhada, "fonts/stone.TTF");

        /* altera fontes */
        TextView textSobre = (TextView) findViewById(R.id.textSobre);
        changeFont(textSobre, "fonts/stone.TTF");
        TextView textIntegrantesA = (TextView) findViewById(R.id.textIntegrantesA);
        changeFont(textIntegrantesA, "fonts/stone.TTF");
        TextView textContatoA = (TextView) findViewById(R.id.textContatoA);
        changeFont(textContatoA, "fonts/stone.TTF");

        // Buttons
        Button btVoltar = (Button) findViewById(R.id.btVoltar);
        changeFont(btVoltar, "fonts/annabelle.ttf");

        // Volta para a MainActivity
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    case R.id.action_about:
                        return true;
                    case R.id.action_help:{
                        Intent help = new Intent(AboutActivity.this, HelpActivity.class);
                        startActivity(help);
                        finish();
                        return true;}
                    case R.id.action_history:{
                        Intent history = new Intent(AboutActivity.this, HistoryActivity.class);
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
