package br.com.wellingtoncosta.githubusers;

import br.com.wellingtoncosta.githubusers.di.DaggerTestAppComponent;
import dagger.android.AndroidInjector;

/**
 * @author Wellington Costa on 28/12/2017.
 */
public class TestApp extends App {

    @Override
    protected AndroidInjector<? extends TestApp> applicationInjector() {
        return DaggerTestAppComponent.builder().create(this);
    }

}