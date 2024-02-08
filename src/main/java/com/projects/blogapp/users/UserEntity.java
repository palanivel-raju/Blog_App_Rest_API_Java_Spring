package com.projects.blogapp.users;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.springframework.lang.Nullable;
import jakarta.persistence.*;

@Getter
@Setter
@ToString
@Entity(name = "users")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    @Nonnull
    private String username;

    @Column(nullable = false)
    @Nonnull
    private String email;

    @Column(nullable = false)
    @Nonnull
    private String password;

    @Nullable
    private String bio;

    private String image;

}
