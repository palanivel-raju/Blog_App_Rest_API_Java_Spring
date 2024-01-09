package com.projects.blogapp.comments;


import com.projects.blogapp.articles.ArticleEntity;
import com.projects.blogapp.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.Nullable;

//import javax.persistence.*;
import java.util.Date;

@Entity(name ="comments")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true)
    @Nullable
    private String title;

    @NonNull
    private String body;

    @CreatedDate
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "articleId", nullable = false)
    private ArticleEntity articleEntity;

}
