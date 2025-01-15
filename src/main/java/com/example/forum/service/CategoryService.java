package com.example.forum.service;

import com.example.forum.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findAll();
}
