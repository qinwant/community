package com.kingwan.community.mapper;

import com.kingwan.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by kingwan on 2020/8/6.
 * 说明：
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    public void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "account_id",property = "accountId"),
            @Result(column = "token",property = "token"),
            @Result(column = "gmt_create",property = "gmtCreate"),
            @Result(column = "gmt_modified",property = "gmtModified"),
            @Result(column = "avatar_url",property = "avatarUrl"),
    })
    User findById(Integer creator);
}
