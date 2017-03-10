package com.example.univasf.keepwalking;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// Testando Commit
public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    // Muda a fonte
    private void mudarFonte(TextView tv, String fonte) {
        Typeface type = Typeface.createFromAsset(getAssets(), fonte);
        tv.setTypeface(type);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // altera fonte do texto superior
        TextView textKeepWalking = (TextView) findViewById(R.id.textKeepWalking);
        TextView textCaminhada = (TextView) findViewById(R.id.textCaminhada);
        mudarFonte(textKeepWalking, "fonts/stone.TTF");
        mudarFonte(textCaminhada, "fonts/stone.TTF");
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
            case R.id.action_contacts:
                return true;
            default:
                return false;
        }
    }
}