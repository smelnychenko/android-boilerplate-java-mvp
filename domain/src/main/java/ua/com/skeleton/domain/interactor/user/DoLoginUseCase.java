package ua.com.skeleton.domain.interactor.user;


import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import ua.com.skeleton.domain.entity.UserEntity;
import ua.com.skeleton.domain.interactor.UseCase;
import ua.com.skeleton.domain.repository.UserRepository;

public class DoLoginUseCase extends UseCase<UserEntity> {

    private UserRepository userRepository;

    private UserEntity user;

    @Inject
    public DoLoginUseCase(@Named("IO") Scheduler executionScheduler,
                          @Named("UI") Scheduler postExecutionScheduler,
                          UserRepository userRepository) {
        super(executionScheduler, postExecutionScheduler);
        this.userRepository = userRepository;
    }

    public void setParams(UserEntity user) {
        this.user = user;
    }

    @Override
    protected Observable<UserEntity> buildUseCaseObservable() {
        return this.userRepository.loginUser(this.user);
    }
}
