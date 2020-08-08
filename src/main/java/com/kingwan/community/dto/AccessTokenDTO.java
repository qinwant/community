package com.kingwan.community.dto;

import lombok.Data;

/**
 * Created by kingwan on 2020/8/5.
 * 说明：github认证参数
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
