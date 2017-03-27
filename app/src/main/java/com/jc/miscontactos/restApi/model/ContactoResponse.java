package com.jc.miscontactos.restApi.model;

import com.jc.miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by TSE on 27/3/2017.
 */

public class ContactoResponse {

    ArrayList<Contacto> contactos;

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(ArrayList<Contacto> contactos) {
        this.contactos = contactos;
    }
}
