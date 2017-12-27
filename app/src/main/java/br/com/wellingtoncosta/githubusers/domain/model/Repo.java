package br.com.wellingtoncosta.githubusers.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class Repo {

    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("language")
    private String language;

    @SerializedName("stargazers_count")
    private Long stargazersCount;

    @SerializedName("forks_count")
    private Long forksCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(Long stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public Long getForksCount() {
        return forksCount;
    }

    public void setForksCount(Long forksCount) {
        this.forksCount = forksCount;
    }

    public boolean hasLanguage() {
        return language != null && !language.isEmpty();
    }

}