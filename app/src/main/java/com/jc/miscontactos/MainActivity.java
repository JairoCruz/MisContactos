package com.jc.miscontactos;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jc.miscontactos.Adapter.ContactoAdaptador;
import com.jc.miscontactos.Adapter.PageAdapter;
import com.jc.miscontactos.fragment.PerfilFragment;
import com.jc.miscontactos.fragment.RecyclerViewFragment;
import com.jc.miscontactos.pojo.Contacto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   // https://api.instagram.com/oauth/authorize/?client_id=d457e5e2ca054aadadd4a43296cd083e&redirect_uri=https://twitter.com/JAIRONMAINDEN&response_type=code&scope=basic+comments+follower_list+likes+relationships+public_content
   // code=9a9eb6dc40e6451c8fd8e6144848adcc

    /*curl -F 'client_id=d457e5e2ca054aadadd4a43296cd083e' \
            -F 'client_secret=c98b995c655347989db37e9cb8f469b7' \
            -F 'grant_type=authorization_code' \
            -F 'redirect_uri=https://twitter.com/JAIRONMAINDEN' \
            -F 'code=83d6e70cd3084b1d9762ff09526cffa0' \
    https://api.instagram.com/oauth/access_token*/

    //{"user": {"username": "azaveliz", "profile_picture": "https://instagram.fads1-1.fna.fbcdn.net/t51.2885-19/11906329_960233084022564_1448528159_a.jpg", "bio": "", "website": "", "id": "4735542793", "full_name": "Jairo Cruz"}, "access_token": "4735542793.d457e5e.6d982763351140daa2d44b2031bd36aa"}

    // https://api.instagram.com/v1/users/self/?access_token=4735542793.d457e5e.6d982763351140daa2d44b2031bd36aa
    // https://api.instagram.com/v1/users/4735542793/?access_token=4735542793.d457e5e.6d982763351140daa2d44b2031bd36aa


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private static final int CODIGO_SOLICITUD_PERMISO = 1;
    private static final int CODIGO_SOLITUD_HABILITAR_BLUETOOH = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
       // setSupportActionBar(miActionBar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();




        if (toolbar != null){
            setSupportActionBar(toolbar);
        }



       /* ArrayList<String> nombresContacto = new ArrayList<>();

        for (Contacto contacto : contactos){
            nombresContacto.add(contacto.getNombre());
        }*/

       /* ListView listView = (ListView) findViewById(R.id.lstContactos);

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresContacto));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetalleContacto.class);
                intent.putExtra("Nombre", contactos.get(i).getNombre());
                intent.putExtra("Telefono", contactos.get(i).getTelefono());
                intent.putExtra("Email", contactos.get(i).getEmail());
                startActivity(intent);
            }
        });*/

    }

//    Para mostrar el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }
//para Controlar lo que sucede al hacer click sobre un elemento del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mAbout:
                Toast.makeText(this,"Esto es About", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.mSettings:
                Toast.makeText(this, "Esto es configuracion", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }

    // poner en orbita los fragments
    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_contacs);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_user_profile);
    }



    public void habilitarBluetooth(View view){
        solitarPermiso();
        BluetoothAdapter bluettothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluettothAdapter == null){
            Toast.makeText(this, "tu dispositivo no tiene bluettooh", Toast.LENGTH_SHORT).show();
        }

        if (!bluettothAdapter.isEnabled()){
            Intent habilitarBluetooInten = new Intent(bluettothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(habilitarBluetooInten, CODIGO_SOLITUD_HABILITAR_BLUETOOH);
        }
    }


    public boolean checarStatusPermiso(){
        int resultado = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH);
        if (resultado == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            return false;
        }
    }

    public void solitarPermiso(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.BLUETOOTH)){
            Toast.makeText(this, "el permiso ya esta", Toast.LENGTH_SHORT).show();
        }else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.BLUETOOTH}, CODIGO_SOLICITUD_PERMISO);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case CODIGO_SOLICITUD_PERMISO:
                if (checarStatusPermiso()){
                    Toast.makeText(this, "ya esta activo el permiso", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(this, "no esta activo el permiso", Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }



}
