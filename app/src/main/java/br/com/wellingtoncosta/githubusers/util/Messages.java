package br.com.wellingtoncosta.githubusers.util;

import android.util.Log;

import java.net.UnknownHostException;

import br.com.wellingtoncosta.githubusers.BuildConfig;
import br.com.wellingtoncosta.githubusers.R;
import retrofit2.HttpException;

/**
 * @author Wellington Costa on 27/12/2017.
 */
public class Messages {

    public static int getErrorMessage(Throwable throwable) {
        if (BuildConfig.DEBUG) Log.e("error", throwable.toString());

        if (throwable instanceof UnknownHostException) {
            return R.string.server_connection_error;
        } else if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;

            if (httpException.code() == 403) {
                return R.string.limit_requests_exceeded;
            } else if (httpException.code() == 404) {
                return R.string.user_not_found;
            } else {
                return R.string.load_data_failure;
            }
        } else {
            return R.string.load_data_failure;
        }
    }

}