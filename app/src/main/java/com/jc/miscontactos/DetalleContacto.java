package com.jc.miscontactos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleContacto extends AppCompatActivity {

    private static final String KEY_EXTRA_URL = "url";
    private static final String KEY_EXTRA_LIKES = "like";


    private ImageView imgFotoDetalle;
    private TextView tvLikesDetalles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle parametros = getIntent().getExtras();

        String urlFoto = parametros.getString("KEY_EXTRA_URL");
        int likes = parametros.getInt("KEY_EXTRA_LIKES");

        tvLikesDetalles = (TextView) findViewById(R.id.tvLikesDetalle);
        tvLikesDetalles.setText(String.valueOf(likes));

    }

    // crear un menu de contexto al dejar presionado un view un largo tiempo
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu_contexto, menu);

    }
    // para saber que seleccione en el menu de contexto
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mEdit:
                Toast.makeText(this, "soy contexto editar", Toast.LENGTH_SHORT).show();
                // getResoruces().getString(R.string.msg)
                break;
            case R.id.mDelete:
                Toast.makeText(this, "soy contexto Eliminar", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }





    // el menu popup es mostrado por un metodo click sobre el elemento
    public void levantarMenuPopUp(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

        // para saber que esta seleccionando
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mView:
                        Toast.makeText(getBaseContext(), "soy view en popup", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mViewDetail:
                        Toast.makeText(getBaseContext(), "soy detalle en popup", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        popupMenu.show();
    }
}
