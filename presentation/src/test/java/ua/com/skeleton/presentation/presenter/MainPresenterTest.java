package ua.com.skeleton.presentation.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ua.com.skeleton.domain.entity.UserEntity;
import ua.com.skeleton.domain.interactor.user.DoLoginUseCase;
import ua.com.skeleton.domain.repository.UserRepository;
import ua.com.skeleton.presentation.Contracts;
import ua.com.skeleton.presentation.main.MainPresenter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created on 28.04.17.
 */
public class MainPresenterTest {

    @Mock Contracts.Main.ViewMVP mainView;
    @Mock UserRepository userRepository;

    DoLoginUseCase doLoginUseCase;
    MainPresenter mainPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        doLoginUseCase = new DoLoginUseCase(
                Schedulers.io(),
                Schedulers.io(),
                userRepository);
        mainPresenter = new MainPresenter(doLoginUseCase);
        mainPresenter.attachView(mainView);
    }

    @Test
    public void showInfoResult() {
        UserEntity user = new UserEntity("");
        UserEntity loggedInUser = new UserEntity("");
        loggedInUser.setAvatar("https://media.licdn.cYTAyOQ.png");
        when(userRepository.loginUser(user)).thenReturn(Observable.just(loggedInUser));
        mainPresenter.getInfo(user);
        verify(mainView).showImage("https://media.licdn.cYTAyOQ.png");
    }
}