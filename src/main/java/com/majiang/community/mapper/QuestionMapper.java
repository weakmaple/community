package com.majiang.community.mapper;

import com.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @objective :
 * @date :2019/9/22- 21:34
 */
@Mapper
@Component
public interface QuestionMapper {
    @Insert("insert into QUESTION " +
            "(TITLE,DESCRIPTION,GMT_CREATE,GMT_MODIFIED,CREATOR,COMMENT_COUNT,VIEW_COUNT,LIKE_COUNT,TAG) values " +
            "(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void insert(Question question);
}
