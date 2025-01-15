package com.example.forum.service.impl;

import com.example.forum.dto.CategoryDTO;
import com.example.forum.entity.Category;
import com.example.forum.mapper.CategoryMapper;
import com.example.forum.repository.CategoryRepository;
import com.example.forum.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> findAll() {
         List<Category> categoryList = categoryRepository.findAll();
         List<CategoryDTO> categoryDTOList = new ArrayList<>();
         for (Category category : categoryList) {
             categoryDTOList.add(CategoryMapper.toDTO(category));
         }
        return categoryDTOList;
    }
}
