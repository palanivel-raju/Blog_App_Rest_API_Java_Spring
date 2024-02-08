package com.projects.blogapp.articles;

import com.projects.blogapp.users.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @GetMapping("")
    String getArticles(){
        return "Articles";
    }
    @GetMapping("/{id}")
    String getArticleById(@PathVariable("id") String id){
        return "article with id " + id;
    }
    @PostMapping("")
    String createArticle(@AuthenticationPrincipal UserEntity user){
        return  "create article called by" + user.getUsername();
    }
}
