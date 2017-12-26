package br.com.wellingtoncosta.githubusers.di.components;

import javax.inject.Singleton;

import br.com.wellingtoncosta.githubusers.di.modules.AppModule;
import br.com.wellingtoncosta.githubusers.di.modules.NetworkModule;
import br.com.wellingtoncosta.githubusers.ui.App;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author Wellington Costa on 26/12/2017.
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        NetworkModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {}

}