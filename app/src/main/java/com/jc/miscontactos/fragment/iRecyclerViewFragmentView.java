package com.jc.miscontactos.fragment;

import com.jc.miscontactos.Adapter.ContactoAdaptador;
import com.jc.miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by JC on 16/11/2016.
 */

public interface iRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos);

    public void inicializarAdaptadorRV(ContactoAdaptador adaptador);
}
