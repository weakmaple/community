package com.majiang.community.model;

import lombok.Data;

/**
 * @objective :
 * @date :2019/9/22- 21:30
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
