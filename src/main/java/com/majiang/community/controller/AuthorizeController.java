package com.majiang.community.controller;

import com.majiang.community.dto.AccessTokenDTO;
import com.majiang.community.dto.GithubUser;
import com.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Properties;

/**
 * @objective :
 * @date :2019/9/22- 10:57
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state) throws IOException {
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
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
