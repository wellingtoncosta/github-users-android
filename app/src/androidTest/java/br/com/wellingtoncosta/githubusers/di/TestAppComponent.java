package br.com.wellingtoncosta.githubusers.di;

import javax.inject.Singleton;

import br.com.wellingtoncosta.githubusers.TestApp;
import br.com.wellingtoncosta.githubusers.di.modules.ActivityBuildersModule;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author Wellington Costa on 28/12/2017.
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuildersModule.class,
        TestAppModule.class
})
public interface TestAppComponent extends AndroidInjector<TestApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<TestApp> {}

}