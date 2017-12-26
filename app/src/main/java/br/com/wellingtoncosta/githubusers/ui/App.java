package br.com.wellingtoncosta.githubusers.ui;

import com.facebook.drawee.backends.pipeline.Fresco;

import br.com.wellingtoncosta.githubusers.di.components.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class App extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

    @Override
    protected AndroidInjector<? extends App> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

}
