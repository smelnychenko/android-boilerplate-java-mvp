package ua.com.skeleton.presentation;

import android.app.Application;

import ua.com.skeleton.presentation.dependency.component.ApplicationComponent;
import ua.com.skeleton.presentation.dependency.component.DaggerApplicationComponent;
import ua.com.skeleton.presentation.dependency.module.ApplicationModule;

public class BaseApplication extends Application {

    protected ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    protected void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                                        .applicationModule(new ApplicationModule(this))
                                        .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}
