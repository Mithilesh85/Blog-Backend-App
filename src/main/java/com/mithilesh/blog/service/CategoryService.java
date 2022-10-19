package com.mithilesh.blog.service;

import com.mithilesh.blog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

     CategoryDto createCategory(CategoryDto categoryDto);

     List<CategoryDto> getAllCategory();

     CategoryDto getCategory(int categoryId);

     CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);

     void deleteCategory(int categoryId);
}
