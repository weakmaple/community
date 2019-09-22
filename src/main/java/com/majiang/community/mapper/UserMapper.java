package com.majiang.community.mapper;

import com.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @objective :
 * @date :2019/9/22- 16:24
 */
@Mapper
@Component
public interface UserMapper {
    @Insert("insert into user (NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFIED) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
}
