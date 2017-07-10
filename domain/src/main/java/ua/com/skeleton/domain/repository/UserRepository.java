package ua.com.skeleton.domain.repository;

import ua.com.skeleton.domain.entity.UserEntity;

import io.reactivex.Observable;

public interface UserRepository {

    Observable<UserEntity> loginUser(UserEntity user);
}
