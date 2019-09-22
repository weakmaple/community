package com.majiang.community.dto;

import lombok.Data;

/**
 * @objective :
 * @date :2019/9/22- 11:06
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
