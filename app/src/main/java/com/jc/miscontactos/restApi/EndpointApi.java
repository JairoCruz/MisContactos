package com.jc.miscontactos.restApi;

import com.jc.miscontactos.restApi.model.ContactoResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by TSE on 27/3/2017.
 */

public interface EndpointApi {

    @GET(ConstanteRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<ContactoResponse> getRecentMedia();
}
