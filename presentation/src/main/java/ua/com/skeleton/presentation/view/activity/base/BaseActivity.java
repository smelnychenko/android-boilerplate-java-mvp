package ua.com.skeleton.presentation.view.activity.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

import butterknife.ButterKnife;
import ua.com.skeleton.data.net.error.RestApiErrorException;
import ua.com.skeleton.presentation.BaseApplication;
import ua.com.skeleton.presentation.Contracts.BaseMvpView;
import ua.com.skeleton.presentation.R;
import ua.com.skeleton.presentation.dependency.component.ApplicationComponent;

public abstract class BaseActivity<V extends BaseMvpView, P extends MvpPresenter<V>>
        extends MvpActivity<V, P> {

    private ProgressDialog progressDialog;
    private Toast toastMessage;

    protected abstract int getContentView();

    public ApplicationComponent getAppComponent() {
        return ((BaseApplication)getApplication()).getApplicationComponent();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        callInjector();
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
    }

    protected abstract void callInjector();

    public boolean isLoaderShowing() {
        if (this.progressDialog == null) return false;
        return this.progressDialog.isShowing();
    }

    public void showLoader() {
        if (this.progressDialog == null) this.progressDialog = new ProgressDialog(this);
        this.progressDialog.show();
    }

    public void hideLoader() {
        if (this.progressDialog != null) this.progressDialog.dismiss();
        this.progressDialog = null;
    }

    protected void hideKeyboard() {
        hideKeyboard(getCurrentFocus());
    }

    protected void hideKeyboard(View view) {
        if (view == null) {
            view = new View(this);
        }
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void toast(String msg) {
        toast(msg, Toast.LENGTH_SHORT);
    }

    public void toast(Integer resId) {
        toast(getString(resId), Toast.LENGTH_SHORT);
    }

    public void toast(String msg, int duration) {
        if (toastMessage != null) {
            toastMessage.cancel();
        }
        toastMessage = Toast.makeText(this, msg, duration);
        toastMessage.show();
    }

    public void handleError(Throwable error) {
        if (error instanceof RestApiErrorException) {
            switch (((RestApiErrorException) error).getStatusCode()) {
                case RestApiErrorException.UNAUTHORIZED:
                    closeAndDisplayLogin();
                    break;
               default:
                    toast(error.getMessage());
            }
        }
        else toast(R.string.message_error);
    }

    public void closeAndDisplayLogin() {
        //TODO show login screen
    }

}
