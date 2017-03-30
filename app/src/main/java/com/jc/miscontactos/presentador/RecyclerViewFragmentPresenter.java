package com.jc.miscontactos.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jc.miscontactos.db.ConstructorContactos;
import com.jc.miscontactos.fragment.iRecyclerViewFragmentView;
import com.jc.miscontactos.pojo.Contacto;
import com.jc.miscontactos.restApi.ConstanteRestApi;
import com.jc.miscontactos.restApi.EndpointApi;
import com.jc.miscontactos.restApi.adapter.RestApiAdapter;
import com.jc.miscontactos.restApi.model.ContactoResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JC on 16/11/2016.
 */

public class RecyclerViewFragmentPresenter implements iRecyclerViewFragmentPresenter {

    private iRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorContactos constructorContactos;
    private ArrayList<Contacto> contactos;

    public RecyclerViewFragmentPresenter(iRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
       //  obtenerContactosBaseDatos();
        obtenerMediosRecientes();
    }

    @Override
    public void obtenerContactosBaseDatos() {

        constructorContactos = new ConstructorContactos(context);
        contactos = constructorContactos.obtenerDatos();
        mostrarContactosRv();


    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointApi endpointApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<ContactoResponse> contactoResponseCall = endpointApi.getRecentMedia();

        Log.e("url api", ConstanteRestApi.URL_GET_RECENT_MEDIA_USER);

        contactoResponseCall.enqueue(new Callback<ContactoResponse>() {
            @Override
            public void onResponse(Call<ContactoResponse> call, Response<ContactoResponse> response) {
                Log.e("entro aqui", " " + response.body());
                ContactoResponse contactoResponse = response.body();
                contactos = contactoResponse.getContactos();

                mostrarContactosRv();
            }

            @Override
            public void onFailure(Call<ContactoResponse> call, Throwable t) {
                Toast.makeText(context, "no conecta", Toast.LENGTH_SHORT).show();
                Log.e("Error api", "fallo en recuperar datos" + t.getCause());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void mostrarContactosRv() {

        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(contactos));
        iRecyclerViewFragmentView.generarGridLayout();

    }
}
