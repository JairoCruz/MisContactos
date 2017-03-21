package com.jc.miscontactos.db;

import android.content.ContentValues;
import android.content.Context;

import com.jc.miscontactos.R;
import com.jc.miscontactos.pojo.Contacto;

import org.apache.http.conn.ConnectTimeoutException;

import java.util.ArrayList;

/**
 * Created by JC on 16/11/2016.
 */

public class ConstructorContactos {
    private static final Integer LIKE = 1;

    // Interactor

    private Context context;

    public ConstructorContactos(Context context) {
        this.context = context;
    }

    public ArrayList<Contacto> obtenerDatos(){
        ArrayList<Contacto> contactos = new ArrayList<Contacto>();

        /*contactos.add(new Contacto(R.drawable.person_4_240x240,"Jairo Quijano", "7425-4545","azaveliz@gamil.com",5));
        contactos.add(new Contacto(R.drawable.person_4_240x240,"Jairo Alberto", "7425-4545","azaveliz@gamil.com",5));
        contactos.add(new Contacto(R.drawable.person_4_240x240,"Jairo Cruz", "7425-4545","azaveliz@gamil.com",5));
        contactos.add(new Contacto(R.drawable.person_4_240x240,"Jairo Rafael", "7425-4545","azaveliz@gamil.com",5));
        contactos.add(new Contacto(R.drawable.person_4_240x240,"Jairo Estaban", "7425-4545","azaveliz@gamil.com",5));
        contactos.add(new Contacto(R.drawable.person_4_240x240,"Jairo Miguel", "7425-4545","azaveliz@gamil.com",5));

        return contactos;*/


        BaseDatos db = new BaseDatos(context);
        insertarContactos(db);
        return db.obtenerTodos();
    }

    public void insertarContactos(BaseDatos db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "Jairo Cruz");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "2290-7271");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "azaveliz@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.drawable.person_4_240x240);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "Jairito Cruz");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "2290-7271");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "azaveliz@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.drawable.person_4_240x240);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "Alberto Cruz");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "2290-7271");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "azaveliz@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.drawable.person_4_240x240);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "Esteban Cruz");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "2290-7271");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "azaveliz@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.drawable.person_4_240x240);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "Ernesto Cruz");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "2290-7271");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "azaveliz@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.drawable.person_4_240x240);

        db.insertarContacto(contentValues);
    }


    public void darLikeContacto(Contacto contacto){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO, contacto.getId() );
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES, LIKE );
        db.insertarLikeContacto(contentValues);
    }


    public int obtenerLikesContacto(Contacto contacto){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesContacto(contacto);
    }
}
