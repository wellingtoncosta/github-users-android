package br.com.wellingtoncosta.githubusers.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Wellington Costa on 27/12/2017.
 */
public class RepoOwner {

    @SerializedName("login")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}