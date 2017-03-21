package com.jc.miscontactos.fragment;


import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jc.miscontactos.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private static final int CODIGO_SOLICITUD_PERMISO = 1;
    private static final int CODIGO_SOLITUD_HABILITAR_BLUETOOH = 0;
    private Context context;
    private Activity activity;
    private Button btnActivarBluetooh;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
        activity = getActivity();
    }

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_perfil, container, false);
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        btnActivarBluetooh = (Button) v.findViewById(R.id.btnHabilitarBluetoo);

        btnActivarBluetooh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habilitarBluetooth(view);
            }
        });

        return v;
    }


    public void habilitarBluetooth(View view){
        solitarPermiso();
        BluetoothAdapter bluettothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluettothAdapter == null){
            Toast.makeText(getActivity(), "tu dispositivo no tiene bluettooh", Toast.LENGTH_SHORT).show();
        }

        if (!bluettothAdapter.isEnabled()){
            Intent habilitarBluetooInten = new Intent(bluettothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(habilitarBluetooInten, CODIGO_SOLITUD_HABILITAR_BLUETOOH);
        }
    }


    public boolean checarStatusPermiso(){
        int resultado = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH);
        if (resultado == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            return false;
        }
    }

    public void solitarPermiso(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.BLUETOOTH)){
            Toast.makeText(getActivity(), "el permiso ya esta", Toast.LENGTH_SHORT).show();
        }else {
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.BLUETOOTH}, CODIGO_SOLICITUD_PERMISO);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case CODIGO_SOLICITUD_PERMISO:
                if (checarStatusPermiso()){
                    Toast.makeText(getActivity(), "ya esta activo el permiso", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(getActivity(), "no esta activo el permiso", Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }
}
