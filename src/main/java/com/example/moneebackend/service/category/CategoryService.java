package com.example.moneebackend.service.category;

import com.example.moneebackend.domain.category.Category;
import com.example.moneebackend.domain.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getCategoriesByType(String type) {
        return categoryRepository.findByType(type);
    }
}
