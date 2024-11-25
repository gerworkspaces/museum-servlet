package net.blwsmartware.museum.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Museum {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @NotNull(message = "NAME_NOT_NULL")
    @Column(unique = true)
    String name;

    String description, location;
    @CreationTimestamp
    Instant createAt;

    @UpdateTimestamp
    Instant updateAt;

    String  created_by, thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

}
