package com.projects.blogapp.articles;

import com.projects.blogapp.articles.DTo.CreateArticleRequest;
import com.projects.blogapp.articles.DTo.UpdateArticleRequest;
import com.projects.blogapp.users.UserRepository;
import com.projects.blogapp.users.UserService;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;
    private UserRepository userRepository;
    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository){
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }
    public Iterable<ArticleEntity> getAllArticles(){
        return articleRepository.findAll();
    }
    public ArticleEntity getArticleBySlug(String slug){
        var article = articleRepository.findBySlug(slug);
        if(article == null){
            throw new ArticleNotFoundException(slug);
        }
        return article;
    }
    public ArticleEntity createArticle(CreateArticleRequest a, long authorId){
        var author = userRepository.findByUserId(authorId).orElseThrow(()-> new UserService.UserNotFoundException(authorId));
        return articleRepository.save(ArticleEntity.builder()
                .author(author)
                //Todo proper slugification function
                .slug(a.getTitle().toLowerCase().replaceAll("\\s","-"))
                .title(a.getTitle())
                .body(a.getBody())
                .subtitle(a.getSubtitle())
                .build());
    }
    public ArticleEntity updateArticle(Long id, UpdateArticleRequest u){
        var article = articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
        if(u.getTitle() != null){
            article.setTitle(u.getTitle());
            article.setSlug(u.getTitle().toLowerCase().replaceAll("\\s","-"));
        }
        if(u.getBody() != null){
            article.setBody(u.getBody());
        }
        if(u.getSubtitle() != null){
            article.setTitle(u.getSubtitle());
        }
        return articleRepository.save(article);
    }
    public static class ArticleNotFoundException extends IllegalArgumentException{
        public ArticleNotFoundException(String slug){
            super("Article "+ slug + " is not found");
        }
        public ArticleNotFoundException(Long id){
            super("Article id: "+ id + " is not found");
        }
    }

}
