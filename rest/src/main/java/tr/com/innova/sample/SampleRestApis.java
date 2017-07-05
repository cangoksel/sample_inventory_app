package tr.com.innova.sample;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import tr.com.innova.sample.common.rest.SimpleRestResponse;

/**
 * Created by tozyurek on 12.05.2016.
 */

public interface SampleRestApis {

    @GET("api/khy/connectionTest")
    Call<SimpleRestResponse> test();


}