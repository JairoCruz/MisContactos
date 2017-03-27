package com.jc.miscontactos.presentador;

import android.content.Context;

import com.jc.miscontactos.db.ConstructorContactos;
import com.jc.miscontactos.fragment.iRecyclerViewFragmentView;
import com.jc.miscontactos.pojo.Contacto;

import java.util.ArrayList;

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

    }

    @Override
    public void mostrarContactosRv() {

        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(contactos));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();

    }
}
