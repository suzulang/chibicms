package com.tianpei.nytdforum.controller;

import com.tianpei.nytdforum.pojo.Article;
import com.tianpei.nytdforum.pojo.PageBean;
import com.tianpei.nytdforum.pojo.Result;
import com.tianpei.nytdforum.service.ArticleService;
import com.tianpei.nytdforum.utils.ThreadLocalUtil;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb =  articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id){
        Article a = articleService.findArticleById(id);
        return Result.success(a);
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        articleService.deleteById(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody @Validated Article article){
        articleService.update(article);
        return Result.success();
    }

    @GetMapping("/all")
    public Result all(){
        List<Article> list = articleService.getAll();
        return Result.success(list);
    }
}
