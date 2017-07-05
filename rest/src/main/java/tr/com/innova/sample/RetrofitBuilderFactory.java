package tr.com.innova.sample;

import com.google.common.collect.Lists;
import okhttp3.ConnectionSpec;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by tozyurek on 12.05.2016.
 */
public class RetrofitBuilderFactory {

    public static Retrofit retrofitBuilderWithAuth(String url, String username, String pass){
        String credentials = Credentials.basic(username,pass);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(JacksonConverterFactory.create());
        OkHttpClient httpClient = new OkHttpClient.Builder()
            .followSslRedirects(false)
            .connectionSpecs(Lists.newArrayList(ConnectionSpec.CLEARTEXT))
            .addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                                       .addHeader("Authorization", credentials)
                                       .addHeader("Access-Control-Allow-Origin", "*").build();

                return chain.proceed(request);
            }).build();
        return builder.client(httpClient).build();
    }
}
