package ua.com.skeleton.data.repository;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.Response;
import ua.com.skeleton.data.net.RestApi;
import ua.com.skeleton.data.net.wrapper.UserWrapper;
import ua.com.skeleton.domain.entity.UserEntity;
import ua.com.skeleton.domain.repository.UserRepository;

public class UserDataRepository extends RestApiRepository implements UserRepository {

    private final RestApi restApi;

    public UserDataRepository(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<UserEntity> loginUser(UserEntity user) {
        UserEntity loggedInUser = new UserEntity();
        loggedInUser.setAvatar("https://media.licdn.com/media/AAEAAQAAAAAAAAbpAAAAJDlmNmJkOTRhLTViMmUtNDI2OC1hNjNmLWUwMmQ0ZDg5YTAyOQ.png");
        return restApi.doLogin(new UserWrapper(user)).flatMap(new Function<Response<UserEntity>, ObservableSource<UserEntity>>() {
            @Override
            public ObservableSource<UserEntity> apply(Response<UserEntity> userEntityResponse) {
                return userEntityResponse.isSuccessful()
                        ? Observable.just(userEntityResponse.body())
                        : Observable.error(processErrorResponse(userEntityResponse));
            }
        });
    }
}
