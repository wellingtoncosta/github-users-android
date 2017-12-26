package br.com.wellingtoncosta.githubusers.data.remote.response;

import java.util.Collections;
import java.util.List;

import io.reactivex.annotations.NonNull;

import static br.com.wellingtoncosta.githubusers.data.remote.response.Status.ERROR;
import static br.com.wellingtoncosta.githubusers.data.remote.response.Status.SUCCESS;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class Response<T> {

    @NonNull
    public final Status status;

    @NonNull
    public final List<T> data;

    private Response(@NonNull Status status, @NonNull List<T> data) {
        this.status = status;
        this.data = data;
    }

    public static <T> Response<T> success(List<T> data) {
        return new Response<>(SUCCESS, data);
    }

    public static <T> Response<T> error() {
        return new Response<>(ERROR, Collections.emptyList());
    }

}