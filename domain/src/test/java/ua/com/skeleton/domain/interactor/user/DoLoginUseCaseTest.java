package ua.com.skeleton.domain.interactor.user;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.TestObserver;
import ua.com.skeleton.domain.entity.UserEntity;
import ua.com.skeleton.domain.repository.SessionRepository;
import ua.com.skeleton.domain.repository.UserRepository;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class DoLoginUseCaseTest {

    private static final String FAKE_PASS = "1234";

    @Mock private Scheduler mockExecutionScheduler;
    @Mock private Scheduler mockPostExecutionScheduler;
    @Mock private UserRepository mockUserRepository;
    @Mock private UserEntity mockUser;

    @Before
    public void setup() { MockitoAnnotations.initMocks(this); }

    @Test
    public void testDoLoginUseCaseSuccess() {
        DoLoginUseCase doLoginUseCase = new DoLoginUseCase(mockExecutionScheduler,
                mockPostExecutionScheduler, mockUserRepository);
        TestObserver<UserEntity> testObserver = new TestObserver<>();
        given(mockUserRepository.loginUser(mockUser)).willReturn(Observable.just(mockUser));

        doLoginUseCase.setParams(mockUser);
        doLoginUseCase.buildUseCaseObservable().subscribeWith(testObserver);

        verify(mockUserRepository).loginUser(mockUser);
        Assert.assertEquals(mockUser, (testObserver.getEvents().get(0)).get(0));
        verifyNoMoreInteractions(mockUserRepository);
        verifyZeroInteractions(mockExecutionScheduler);
        verifyZeroInteractions(mockPostExecutionScheduler);
    }
}