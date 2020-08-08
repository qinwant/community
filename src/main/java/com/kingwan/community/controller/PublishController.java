package com.kingwan.community.controller;

import com.kingwan.community.mapper.QuestionMapper;
import com.kingwan.community.mapper.UserMapper;
import com.kingwan.community.model.Question;
import com.kingwan.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kingwan on 2020/8/6.
 * 说明：
 */
@Controller
public class PublishController {

    @Autowired
    private  QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        System.out.println("跳转发布界面...");
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request,
                            Model model){
//        页面回显
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
//        校验内容
        if(title==null||title==""){
            System.out.println("内容不完整，发布失败...");
            model.addAttribute("error","请填写标题!");
            return "publish";
        }
        if(description==null||description==""){
            System.out.println("内容不完整，发布失败...");
            model.addAttribute("error","请填写内容!");
            return "publish";
        }
        if(tag==null||tag==""){
            System.out.println("内容不完整，发布失败...");
            model.addAttribute("error","请填写标签!");
            return "publish";
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }

        if(user==null){
            System.out.println("发布失败...");
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        System.out.println("发布成功，返回首页...");
        return "redirect:/";
    }
}
