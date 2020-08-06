package com.kingwan.community.dto;

/**
 * Created by kingwan on 2020/8/5.
 * 说明：
 */
public class GithubUser {
    private String name;//github名
    private String id;//github的用户id
    private String bio;//github的用户说明
    private String login;//github的登录名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", bio='" + bio + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
