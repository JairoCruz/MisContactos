package com.jc.miscontactos.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jc.miscontactos.restApi.ConstanteRestApi;
import com.jc.miscontactos.restApi.EndpointApi;
import com.jc.miscontactos.restApi.desserializador.ContactoDeserealizador;
import com.jc.miscontactos.restApi.model.ContactoResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TSE on 28/3/2017.
 */

public class RestApiAdapter {

    public EndpointApi establecerConexionRestApiInstagram(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstanteRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ContactoResponse.class, new ContactoDeserealizador());

        return gsonBuilder.create();
    }
}
