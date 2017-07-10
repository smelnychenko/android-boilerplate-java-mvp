package ua.com.skeleton.data.net;

import ua.com.skeleton.data.net.wrapper.UserWrapper;
import ua.com.skeleton.domain.entity.UserEntity;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;
import io.reactivex.Observable;

public interface RestApi {

    int API_VERSION = 1;
    String URL_BASE = String.format("http://change.base.url.com/v%d/", API_VERSION);

    @POST("/users")
    Observable<Response<UserEntity>> createUser(@Body UserWrapper userWrapper);

    @POST("/users/login")
    Observable<Response<UserEntity>> doLogin(@Body UserWrapper userWrapper);

    @DELETE("/users/logout")
    Observable<Response<Void>> doLogout(@Header("Authorization") String token);

}
