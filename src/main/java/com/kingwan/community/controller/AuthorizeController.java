package com.kingwan.community.controller;

import com.kingwan.community.dto.AccessTokenDTO;
import com.kingwan.community.dto.GithubUser;
import com.kingwan.community.mapper.UserMapper;
import com.kingwan.community.model.User;
import com.kingwan.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by kingwan on 2020/8/5.
 * 说明：
 */
@Controller
public class AuthorizeController {

    @Autowired
    private  GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;


    @GetMapping("callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
//        获取token
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
//        获取用户
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser!=null){
            //登陆成功
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getLogin());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userMapper.insert(user);
            //写入cookie
            response.addCookie(new Cookie("token",token));
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else {
            //登陆失败
            return "redirect:/";
        }
    }
}
