package net.blwsmartware.museum.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.blwsmartware.museum.constant.PagePrepare;
import net.blwsmartware.museum.dto.request.museum.CategoryRequest;
import net.blwsmartware.museum.dto.request.museum.CategoryUpdate;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.MessageResponse;
import net.blwsmartware.museum.dto.response.museum.CategoryResponse;
import net.blwsmartware.museum.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class CategoryController {

    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<MessageResponse<CategoryResponse>> createCategory(@RequestBody @Valid CategoryRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.<CategoryResponse>builder()
                        .result(categoryService.createCategory(request))
                        .build()
                );
    }
    @GetMapping
    public ResponseEntity<MessageResponse<DataResponse<CategoryResponse>>> getAllPageable(
            @RequestParam(value = "pageNumber",defaultValue = PagePrepare.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = PagePrepare.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = PagePrepare.SORT_BY, required = false) String sortBy) {

        return ResponseEntity
                .status(HttpStatus.OK)
                    .body(MessageResponse.<DataResponse<CategoryResponse>>builder()
                    .result(categoryService.getAll(pageNumber,pageSize,sortBy))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse<CategoryResponse>> getCategory(@PathVariable int id){

        return ResponseEntity
                .status(HttpStatus.OK)
                    .body(MessageResponse.<CategoryResponse>builder()
                    .result(categoryService.getCategoryByID(id))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse<CategoryResponse>> updateCategory(@RequestBody @Valid CategoryUpdate categoryUpdate, @PathVariable int id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<CategoryResponse>builder()
                    .result(categoryService.updateCategory(id,categoryUpdate))
                    .build()
                );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {

        categoryService.delete(id);

        return ResponseEntity.noContent().build();

    }
}
