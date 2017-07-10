package ua.com.skeleton.presentation.main;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ua.com.skeleton.domain.entity.UserEntity;
import ua.com.skeleton.domain.interactor.user.DoLoginUseCase;
import ua.com.skeleton.presentation.Contracts.Main;
import ua.com.skeleton.presentation.presenter.BasePresenter;

/**
 * Created on 27.04.17.
 */

public class MainPresenter extends BasePresenter<Main.ViewMVP>
        implements Main.PresenterMVP {

    private DoLoginUseCase doLoginUseCase;
    private LoginSubscriber loginSubscriber;

    @Inject
    public MainPresenter(DoLoginUseCase doLoginUseCase) {
        this.doLoginUseCase = doLoginUseCase;
    }

    @Override
    public void getInfo(UserEntity user) {
        doLoginUseCase.setParams(user);
        loginSubscriber = new LoginSubscriber();
        doLoginUseCase.execute(loginSubscriber);
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (loginSubscriber != null && !loginSubscriber.isDisposed()) {
            loginSubscriber.dispose();
        }
        super.detachView(retainInstance);
    }

    class LoginSubscriber extends DisposableObserver<UserEntity> {

        @Override
        public void onNext(UserEntity user) {
            view.showImage(user.getAvatar());
        }

        @Override
        public void onError(Throwable e) {
            view.showMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
        }

    }

}
