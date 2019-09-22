package com.majiang.community.controller;

import com.majiang.community.dto.AccessTokenDTO;
import com.majiang.community.dto.GithubUser;
import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.User;
import com.majiang.community.provider.GithubProvider;
import com.sun.deploy.net.HttpResponse;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

/**
 * @objective :
 * @date :2019/9/22- 10:57
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        // 在html配置了返回请求的链接，会带上code和state参数
        // 读取配置文件
        Properties properties = PropertiesLoaderUtils.loadAllProperties("config/GithubSetting.properties");
        // 完善accessTokenDTO
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(properties.getProperty("github.Client_id"));
        accessTokenDTO.setClient_secret(properties.getProperty("github.Client_secret"));
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(properties.getProperty("github.Redirect_uri"));
        accessTokenDTO.setState(state);
        // 获得AccessToken参数
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        // 获得用户信息
        GithubUser githubUser = githubProvider.getUser(accessToken);
        // 跳转回index页面
        if(githubUser != null && githubUser.getId() != null){
            // 登录成功
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatar_url(githubUser.getAvatar_url());
            userMapper.insert(user);
            //request.getSession().setAttribute("user",githubUser);
            response.addCookie(new Cookie("token",token));

            System.out.println("登录成功 "+user.getName());
            return "redirect:/";
        }else{
            // 登录失败
            return "redirect:/";
        }
    }
}
