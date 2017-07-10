package ua.com.skeleton.presentation.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import io.reactivex.observers.DisposableObserver;

/**
 * Created on 27.04.17.
 */

public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    protected V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView(boolean retainInstance) {
        this.view = null;
    }

    protected boolean hasView() {
        return view != null;
    }
}
