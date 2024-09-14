package com.tianpei.nytdforum.service;

import com.tianpei.nytdforum.pojo.Article;
import com.tianpei.nytdforum.pojo.PageBean;

import java.util.List;

public interface ArticleService {
    //新增文章
    void add(Article article);

    //条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    Article findArticleById(Integer id);

    void deleteById(Integer id);

    void update(Article article);

    List<Article> getAll();
}
