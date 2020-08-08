package com.kingwan.community.controller;

import com.kingwan.community.dto.PaginationDTO;
import com.kingwan.community.mapper.UserMapper;
import com.kingwan.community.model.User;
import com.kingwan.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kingwan on 2020/8/5.
 * 说明：
 */
@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page" ,defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "6")Integer size){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
//        加入列表信息
        PaginationDTO pagination = questionService.getList(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
