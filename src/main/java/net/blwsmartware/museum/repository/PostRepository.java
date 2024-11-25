package net.blwsmartware.museum.repository;

import net.blwsmartware.museum.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByPostTypeId(int typeId, Pageable pageable);
}
