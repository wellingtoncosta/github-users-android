package br.com.wellingtoncosta.githubusers.util.scheduler;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

/**
 * @author Wellington Costa on 28/12/2017.
 */
public class TestSchedulerProvider implements BaseScheduler {

    private final TestScheduler mTestScheduler;

    public TestSchedulerProvider(TestScheduler testScheduler) {
        mTestScheduler = testScheduler;
    }

    @NonNull
    @Override
    public Scheduler io() {
        return mTestScheduler;
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return mTestScheduler;
    }

}