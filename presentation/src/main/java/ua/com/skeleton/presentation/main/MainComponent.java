package ua.com.skeleton.presentation.main;

import dagger.Component;
import ua.com.skeleton.presentation.dependency.component.ApplicationComponent;

@MainScope
@Component(dependencies = ApplicationComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
