package net.blwsmartware.museum.repository;

import net.blwsmartware.museum.entity.Category;
import net.blwsmartware.museum.entity.Museum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
