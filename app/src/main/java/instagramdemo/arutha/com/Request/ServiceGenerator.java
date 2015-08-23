package instagramdemo.arutha.com.Request;

/**
 * Created by ilkinartuc on 23/08/15.
 */


import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class ServiceGenerator {


    // No need to instantiate this class.
    private ServiceGenerator() {
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setClient(new OkClient(new OkHttpClient()));

        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
    }


}
