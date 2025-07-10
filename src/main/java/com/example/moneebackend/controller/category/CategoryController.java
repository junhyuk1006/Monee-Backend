package com.example.moneebackend.controller.category;

import com.example.moneebackend.domain.category.Category;
import com.example.moneebackend.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories(@RequestParam(required = false) String type) {
        return categoryService.getCategoriesByType(type);
    }
}
