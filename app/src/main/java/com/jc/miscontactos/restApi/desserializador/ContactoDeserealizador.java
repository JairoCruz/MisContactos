package com.jc.miscontactos.restApi.desserializador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.jc.miscontactos.pojo.Contacto;
import com.jc.miscontactos.restApi.JsonKeys;
import com.jc.miscontactos.restApi.model.ContactoResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by TSE on 28/3/2017.
 */

public class ContactoDeserealizador implements JsonDeserializer<ContactoResponse> {
    @Override
    public ContactoResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        ContactoResponse contactoResponse = gson.fromJson(json, ContactoResponse.class);
        JsonArray contactoResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        contactoResponse.setContactos(deserializarContactoDeJson(contactoResponseData));
        return contactoResponse;
    }


    private ArrayList<Contacto> deserializarContactoDeJson(JsonArray contactoResponseData){
        ArrayList<Contacto> contactos = new ArrayList<>();
        for (int i = 0; i < contactoResponseData.size() ; i++) {
            JsonObject contactoResponseDataObject = contactoResponseData.get(i).getAsJsonObject();
            JsonObject userJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            JsonObject imageJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            Contacto contactoActual = new Contacto();
            contactoActual.setId(id);
            contactoActual.setNombreCompleto(nombreCompleto);
            contactoActual.setUrlFoto(urlFoto);
            contactoActual.setLikes(likes);

            contactos.add(contactoActual);

        }

        return contactos;
    }
}
