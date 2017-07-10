package ua.com.skeleton.domain.interactor;

import io.reactivex.Scheduler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UseCaseTest {

    private TestObserver<Integer> testObserver;
    private FakeUseCase fakeUseCase;

    private TestScheduler testExecutionScheduler;
    @Mock private Scheduler mockPostExecutionScheduler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testExecutionScheduler = new TestScheduler();
        this.fakeUseCase = new FakeUseCase(testExecutionScheduler, mockPostExecutionScheduler);
    }

    @Test
    public void testUseCaseExecutionResult() {

        this.testObserver = fakeUseCase.buildUseCaseObservable().test();
        testExecutionScheduler.triggerActions();

        this.testObserver.assertNoErrors();
        this.testObserver.assertResult(1, 2, 3);
    }

    @Test
    public void testUseCaseUnsubscription() {

        this.testObserver = fakeUseCase.buildUseCaseObservable().test();
        assertThat(this.fakeUseCase.isUnsubscribed(), is(false));

        this.fakeUseCase.unsubscribe();
        assertThat(this.fakeUseCase.isUnsubscribed(), is(true));
    }

    private static class FakeUseCase extends UseCase<Integer> {

        protected FakeUseCase(Scheduler executionScheduler,
                              Scheduler postExecutionScheduler) {
            super(executionScheduler, postExecutionScheduler);
        }

        @Override protected Observable<Integer> buildUseCaseObservable() {
            return Observable.just(1, 2, 3);
        }

    }
}
