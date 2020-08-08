package com.kingwan.community.model;

import lombok.Data;

/**
 * Created by kingwan on 2020/8/6.
 * 说明：
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;//头像
}
