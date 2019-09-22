package com.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import com.majiang.community.dto.AccessTokenDTO;
import com.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @objective :
 * @date :2019/9/22- 11:04
 */
@Component
public class GithubProvider {
    //获得AccessToken参数
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String url= "https://github.com/login/oauth/access_token";
        String json = com.alibaba.fastjson.JSON.toJSONString(accessTokenDTO);

        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        }catch (Exception e){
            System.out.println("getAccessToken "+e);
        }
        return null;
    }

    // 获得用户信息
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.github.com/user?access_token="+accessToken;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try{
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
            return githubUser;
        }catch (Exception e){
            System.out.println("getUser "+e);
        }
        return null ;

    }

}
