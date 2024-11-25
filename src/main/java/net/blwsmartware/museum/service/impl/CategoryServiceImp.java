package net.blwsmartware.museum.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.blwsmartware.museum.dto.request.museum.CategoryRequest;
import net.blwsmartware.museum.dto.request.museum.CategoryUpdate;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.museum.CategoryResponse;
import net.blwsmartware.museum.dto.response.museum.MuseumResponse;
import net.blwsmartware.museum.entity.Category;
import net.blwsmartware.museum.enums.ErrorResponse;
import net.blwsmartware.museum.exception.AppRuntimeException;
import net.blwsmartware.museum.mapper.CategoryMapper;
import net.blwsmartware.museum.repository.CategoryRepository;
import net.blwsmartware.museum.service.CategoryService;
import net.blwsmartware.museum.util.DataResponseUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImp implements CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = categoryMapper.toCategory(request);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public DataResponse<CategoryResponse> getAll(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Category> pageOfCategory = categoryRepository.findAll(pageable);
        List<Category> userList = pageOfCategory.getContent();
        List<CategoryResponse> categoryResponses = userList.stream().map(categoryMapper::toCategoryResponse).toList();
        return DataResponseUtils.convertPageInfo(pageOfCategory,categoryResponses);
    }

    @Override
    public CategoryResponse getCategoryByID(int id) {
        return categoryMapper.toCategoryResponse(categoryRepository.findById(id)
                .orElseThrow(() -> new AppRuntimeException(ErrorResponse.CATEGORY_NOT_FOUND)));
    }

    @Override
    public CategoryResponse updateCategory(int id, CategoryUpdate update) {

        Category old = categoryRepository.findById(id)
                .orElseThrow(() -> new AppRuntimeException(ErrorResponse.CATEGORY_NOT_FOUND));
        categoryMapper.updateCategory(update,old);
        return categoryMapper.toCategoryResponse(categoryRepository.save(old));
    }

    @Override
    public void delete(int id) {
        categoryRepository.deleteById(id);
    }
}
