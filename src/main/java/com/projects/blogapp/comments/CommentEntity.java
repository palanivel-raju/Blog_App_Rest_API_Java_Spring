package com.projects.blogapp.comments;


import com.projects.blogapp.articles.ArticleEntity;
import com.projects.blogapp.users.UserEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

//import javax.persistence.*;
import java.util.Date;


//@Builder
//@RequiredArgsConstructor
//@AllArgsConstructor

//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name ="comments")
@Getter
@Setter
@ToString
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true)
    @Nullable
    private String title;

    @Nonnull
    private String body;

    @CreatedDate
    private Date createdDate;


//    @JoinColumn(name = "authorId", nullable = false)
    @ManyToOne
    private UserEntity author;


//    @JoinColumn(name = "articleId", nullable = false)
    @ManyToOne
    private ArticleEntity articleEntity;

}
