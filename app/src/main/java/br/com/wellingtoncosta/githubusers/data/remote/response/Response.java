package br.com.wellingtoncosta.githubusers.data.remote.response;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static br.com.wellingtoncosta.githubusers.data.remote.response.Status.ERROR;
import static br.com.wellingtoncosta.githubusers.data.remote.response.Status.SUCCESS;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class Response<T> {

    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    public Throwable throwable;

    private Response(@NonNull Status status, @Nullable T data, @Nullable Throwable throwable) {
        this.status = status;
        this.data = data;
        this.throwable = throwable;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(SUCCESS, data, null);
    }

    public static <T> Response<T> error(Throwable throwable) {
        return new Response<>(ERROR, null, throwable);
    }

}