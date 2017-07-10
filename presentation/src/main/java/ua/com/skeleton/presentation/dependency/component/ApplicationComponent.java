package ua.com.skeleton.presentation.dependency.component;

import android.content.Context;
import android.content.SharedPreferences;

import io.reactivex.Scheduler;
import ua.com.skeleton.data.net.RestApi;
import ua.com.skeleton.domain.repository.UserRepository;
import ua.com.skeleton.presentation.dependency.module.ApplicationModule;
import ua.com.skeleton.presentation.dependency.module.DataModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class, DataModule.class })
public interface ApplicationComponent {

    Context context();
    SharedPreferences sharedPreferences();

    @Named("UI")
    Scheduler uiScheduler();
    @Named("IO")
    Scheduler ioScheduler();

    RestApi restApi();
    UserRepository userRepository();
}
