package com.majiang.community.model;

import lombok.Data;

/**
 * @objective :
 * @date :2019/9/22- 16:36
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatar_url;
}
