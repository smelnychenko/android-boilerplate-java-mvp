package ua.com.skeleton.presentation.main;

import dagger.Module;
import dagger.Provides;
import ua.com.skeleton.domain.interactor.user.DoLoginUseCase;
import ua.com.skeleton.presentation.Contracts.Main;

@Module
public class MainModule {

    @MainScope
    @Provides
    Main.PresenterMVP provideMainPresenter(DoLoginUseCase doLoginUseCase) {
        return new MainPresenter(doLoginUseCase);
    }
}
