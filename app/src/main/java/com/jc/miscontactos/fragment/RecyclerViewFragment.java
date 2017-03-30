package com.jc.miscontactos.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jc.miscontactos.Adapter.ContactoAdaptador;
import com.jc.miscontactos.R;
import com.jc.miscontactos.pojo.Contacto;
import com.jc.miscontactos.presentador.RecyclerViewFragmentPresenter;
import com.jc.miscontactos.presentador.iRecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by JC on 07/11/2016.
 */

public class RecyclerViewFragment extends Fragment implements iRecyclerViewFragmentView {

    private RecyclerView listaContactos;
    ArrayList<Contacto> contactos;
    private iRecyclerViewFragmentPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        listaContactos = (RecyclerView) v.findViewById(R.id.rvContactos);

        presenter = new RecyclerViewFragmentPresenter(this, getContext());

        return v;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }




   /* public void inicializarListaContactos(){
        contactos = new ArrayList<Contacto>();

        contactos.add(new Contacto(R.drawable.person_4_240x240,"Jairo Quijano", "7425-4545","azaveliz@gamil.com"));
        contactos.add(new Contacto(R.drawable.person_4_240x240,"Jairo Alberto", "7425-4545","azaveliz@gamil.com"));
        contactos.add(new Contacto(R.drawable.person_4_240x240,"Jairo Cruz", "7425-4545","azaveliz@gamil.com"));
        contactos.add(new Contacto(R.drawable.person_4_240x240,"Jairo Rafael", "7425-4545","azaveliz@gamil.com"));
        contactos.add(new Contacto(R.drawable.person_4_240x240,"Jairo Estaban", "7425-4545","azaveliz@gamil.com"));
        contactos.add(new Contacto(R.drawable.person_4_240x240,"Jairo Miguel", "7425-4545","azaveliz@gamil.com"));
    }*/

    @Override
    public void generarLinearLayoutVertical() {

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //GridLayoutManager glm = new GridLayoutManager(this, 2);

        //listaContactos.setLayoutManager(glm);
        listaContactos.setLayoutManager(llm);

    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        listaContactos.setLayoutManager(gridLayoutManager);
    }

    @Override
    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos) {
        ContactoAdaptador adaptador = new ContactoAdaptador(contactos,getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(ContactoAdaptador adaptador) {
        listaContactos.setAdapter(adaptador);
    }
}
