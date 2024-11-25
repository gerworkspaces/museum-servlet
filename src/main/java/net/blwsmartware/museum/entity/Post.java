package net.blwsmartware.museum.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id ;
    @CreationTimestamp
    Instant createAt;

    @UpdateTimestamp
    Instant updateAt;

    Instant publishedAt;

    String  created_by,
            thumbnail ,
            title ,
            content ;
    int status ;

    @ManyToOne(fetch = FetchType.LAZY)
    PostType postType;

}
