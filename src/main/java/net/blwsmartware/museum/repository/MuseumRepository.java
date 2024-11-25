package net.blwsmartware.museum.repository;

import net.blwsmartware.museum.entity.Museum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MuseumRepository extends JpaRepository<Museum, Integer> {
    Page<Museum> findByCategoryId(int categoryId, Pageable pageable);
}
