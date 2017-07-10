package ua.com.skeleton.presentation.dependency.module;

import android.content.Context;
import android.content.SharedPreferences;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ua.com.skeleton.domain.interactor.user.DoLoginUseCase;
import ua.com.skeleton.domain.repository.SessionRepository;
import ua.com.skeleton.domain.repository.UserRepository;
import ua.com.skeleton.presentation.BaseApplication;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private static final String SHARED_PACKAGE = "base_shared_preferences";

    private final BaseApplication baseApplication;

    public ApplicationModule(BaseApplication baseApplication) {
        this.baseApplication = baseApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.baseApplication;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PACKAGE, Context.MODE_PRIVATE);
    }

    @Provides
    @Named("IO")
    @Singleton
    Scheduler provideThreadExecutor() {
        return Schedulers.io();
    }

    @Provides
    @Named("UI")
    @Singleton
    Scheduler providePostExecutionThread() {
        return AndroidSchedulers.mainThread();
    }

}
