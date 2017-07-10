package ua.com.skeleton.domain.repository;

import ua.com.skeleton.domain.entity.UserEntity;

public interface SessionRepository {
    UserEntity getCurrentUser();
    void setCurrentUser(UserEntity user);
    void invalidateSession();
}
