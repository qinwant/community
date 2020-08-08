package com.kingwan.community.dto;

import lombok.Data;

/**
 * Created by kingwan on 2020/8/5.
 * 说明：
 */
@Data
public class GithubUser {
    private String name;//github名
    private String id;//github的用户id
    private String bio;//github的用户说明
    private String login;//github的登录名
    private String avatarUrl;
}
