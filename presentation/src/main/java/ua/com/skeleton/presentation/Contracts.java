package ua.com.skeleton.presentation;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import ua.com.skeleton.domain.entity.UserEntity;

/**
 * Created on 27.04.17.
 */

public interface Contracts {

    interface BaseMvpView extends MvpView {
        void showLoader();
        void hideLoader();
        void handleError(Throwable error);
        void showMessage(String message);

        void displayLogin();
    }

    interface Main {
        interface ViewMVP extends BaseMvpView {
           void showImage(String info);
        }

        interface PresenterMVP extends MvpPresenter<ViewMVP> {
            void getInfo(UserEntity user);
        }
    }

    // TODO Your view/presenter contracts goes here
}
