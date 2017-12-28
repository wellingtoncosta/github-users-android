package br.com.wellingtoncosta.githubusers.util.scheduler;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

/**
 * @author Wellington Costa on 28/12/2017.
 */
public interface BaseScheduler {

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();

}