package ua.com.skeleton.presentation;

import ua.com.skeleton.presentation.main.MainScope;
import ua.com.skeleton.presentation.dependency.component.ApplicationComponent;

import dagger.Component;

@MainScope
@Component(modules = TestMockerModule.class, dependencies = ApplicationComponent.class)
public interface TestMockerComponent {}
