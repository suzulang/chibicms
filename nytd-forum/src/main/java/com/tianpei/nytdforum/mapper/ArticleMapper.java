package com.tianpei.nytdforum.mapper;

import com.tianpei.nytdforum.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time) " +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    List<Article> list(Integer userId, Integer categoryId, String state);

    @Select("select * from article where id=#{id}")
    Article findArticleById(Integer id);

    @Delete("DELETE FROM article WHERE id = #{id}")
    void deleteById(Integer id);
    @Update("UPDATE article SET title=#{title}, content=#{content}, cover_img=#{coverImg}, state=#{state}, category_id=#{categoryId}, update_time=NOW() WHERE id=#{id}")
    void update(Article article);
    @Select("select * from article")
    List<Article> getAll();
}
