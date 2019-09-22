package com.majiang.community.dto;

import lombok.Data;

/**
 * @objective :
 * @date :2019/9/22- 12:31
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
