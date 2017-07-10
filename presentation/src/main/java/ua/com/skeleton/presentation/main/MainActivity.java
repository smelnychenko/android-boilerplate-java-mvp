package ua.com.skeleton.presentation.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.Bind;
import ua.com.skeleton.domain.entity.UserEntity;
import ua.com.skeleton.presentation.Contracts.Main;
import ua.com.skeleton.presentation.R;
import ua.com.skeleton.presentation.view.activity.base.BaseActivity;

public class MainActivity extends BaseActivity<Main.ViewMVP, Main.PresenterMVP>
        implements Main.ViewMVP {

    @Inject
    protected Main.PresenterMVP mainPresenter;

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.image)
    protected ImageView image;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void callInjector() {
        DaggerMainComponent.builder()
                .applicationComponent(getAppComponent())
                .mainModule(new MainModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    public Main.PresenterMVP createPresenter() {
        return mainPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.title_activity_main);
        presenter.getInfo(new UserEntity("some.email@mail.com"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void showMessage(String message) {
        toast(message);
    }

    @Override
    public void displayLogin() {

    }

    @Override
    public void showImage(String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .fitCenter()
                .into(image);
    }
}
