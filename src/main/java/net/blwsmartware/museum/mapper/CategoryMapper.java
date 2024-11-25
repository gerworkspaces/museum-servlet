package net.blwsmartware.museum.mapper;

import net.blwsmartware.museum.dto.request.museum.CategoryRequest;
import net.blwsmartware.museum.dto.request.museum.CategoryUpdate;
import net.blwsmartware.museum.dto.response.museum.CategoryResponse;
import net.blwsmartware.museum.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest request);
    void updateCategory(CategoryUpdate categoryUpdate, @MappingTarget Category category);
    CategoryResponse toCategoryResponse(Category category);
}
