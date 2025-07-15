package com.example.moneebackend.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // 타입(수입/지출)에따라 카테고리를 나눠서 보여주기 위해 사용
    // SELECT * FROM CATEGORY WHERE TYPE = ?
    List<Category> findByType(String type);
    Optional<Category> findByTypeAndName(String type, String Name);
}
