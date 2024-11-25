package net.blwsmartware.museum.service;

import net.blwsmartware.museum.dto.request.museum.CategoryRequest;
import net.blwsmartware.museum.dto.request.museum.CategoryUpdate;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.museum.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    DataResponse<CategoryResponse> getAll(Integer pageNumber, Integer pageSize, String sortBy);
    CategoryResponse getCategoryByID(int id);

    CategoryResponse updateCategory(int id, CategoryUpdate update);
    void delete(int id);
}
