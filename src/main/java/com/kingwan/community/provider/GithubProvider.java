package com.kingwan.community.provider;

import com.alibaba.fastjson.JSON;
import com.kingwan.community.dto.AccessTokenDTO;
import com.kingwan.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by kingwan on 2020/8/5.
 * 说明：提供者，获得回调token
 * 详细api文档：https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/
 */
@Component
public class GithubProvider {
    /**
     * 从github的api中获取token
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json;charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder().url("https://github.com/login/oauth/access_token").post(body).build();
        try(Response response = client.newCall(request).execute()) {
            String string = response.body().string();
//            获取返回的token-->access_token=e72e16c7e42f292c6912e7710c838347ae178b4a&token_type=bearer
            String token = string.split("&")[0].split("=")[1];
            System.out.println(token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据token去请求github获取用户信息
     * @param accessToken
     * @return
     */
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.github.com/user").header("Authorization","token "+accessToken).build();
        try(Response response = client.newCall(request).execute()) {
            String string = response.body().string();
//            返回的string转成json-构造成githubUser
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
