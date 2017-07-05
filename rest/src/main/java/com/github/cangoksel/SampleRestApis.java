package com.github.cangoksel;

import com.github.cangoksel.common.rest.SimpleRestResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tozyurek on 12.05.2016.
 */

public interface SampleRestApis {

    @GET("api/khy/connectionTest")
    Call<SimpleRestResponse> test();


}