package com.kingwan.community.model;

import lombok.Data;

/**
 * Created by kingwan on 2020/8/6.
 * 说明：
 */
@Data
public class Question {
    private Integer id;//id
    private String title;//标题
    private String description;//内容
    private String tag;//标签
    private Long gmtCreate;//发布时间
    private Long gmtModified;//更新时间
    private Integer creator;//发布人
    private Integer viewCount;//阅读数
    private Integer commentCount;//评论数
    private Integer likeCount;//点赞数
}
