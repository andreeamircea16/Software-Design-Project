package com.store.app.controller;

import com.store.app.entity.Category;
import com.store.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Category> getCategories() {
        List<Category> category = categoryService.getCategories();
        return category;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Category createPost(@RequestBody Category category) {
        Category newCategory = categoryService.createCategory(category);
        return newCategory;
    }
}
