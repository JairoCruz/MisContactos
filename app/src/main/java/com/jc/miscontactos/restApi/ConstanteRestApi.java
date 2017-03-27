package com.jc.miscontactos.restApi;

/**
 * Created by TSE on 27/3/2017.
 */

public final class ConstanteRestApi {

    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCES_TOKEN = "4735542793.d457e5e.6d982763351140daa2d44b2031bd36aa";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCES_TOKEN;

}
