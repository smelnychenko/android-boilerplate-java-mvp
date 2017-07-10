package ua.com.skeleton.presentation.dependency.module;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import ua.com.skeleton.data.net.RestApi;
import ua.com.skeleton.data.net.interceptor.ConnectivityInterceptor;
import ua.com.skeleton.data.net.interceptor.HttpInterceptor;
import ua.com.skeleton.data.repository.UserDataRepository;
import ua.com.skeleton.domain.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    @Provides
    @Singleton
    RestApi provideRestApi(Context context) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new ConnectivityInterceptor(context))
                .addInterceptor(new HttpInterceptor())
                .build();

        GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create());

        return new Retrofit.Builder()
                .baseUrl(RestApi.URL_BASE)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(RestApi.class);
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(RestApi restApi) {
        return new UserDataRepository(restApi);
    }

}
