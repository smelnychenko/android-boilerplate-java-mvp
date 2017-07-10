package ua.com.skeleton.data.net.interceptor;


import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import ua.com.skeleton.data.net.NetworkUtil;
import ua.com.skeleton.data.net.error.NoConnectionError;

public class ConnectivityInterceptor implements Interceptor {

    private Context context;

    public ConnectivityInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        if (NetworkUtil.isConnected(context)) {
            Response response = chain.proceed(chain.request());
            return response;
        } else {
            throw new NoConnectionError();
        }
    }
}