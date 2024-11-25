package net.blwsmartware.museum.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.blwsmartware.museum.constant.PagePrepare;
import net.blwsmartware.museum.dto.request.museum.CategoryRequest;
import net.blwsmartware.museum.dto.request.museum.CategoryUpdate;
import net.blwsmartware.museum.dto.request.post.PostTypeRequest;
import net.blwsmartware.museum.dto.request.post.PostTypeUpdate;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.MessageResponse;
import net.blwsmartware.museum.dto.response.museum.CategoryResponse;
import net.blwsmartware.museum.dto.response.post.PostTypeResponse;
import net.blwsmartware.museum.service.CategoryService;
import net.blwsmartware.museum.service.PostTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class TypeController {

    PostTypeService typeService;

    @PostMapping
    public ResponseEntity<MessageResponse<PostTypeResponse>> createCategory(@RequestBody @Valid PostTypeRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.<PostTypeResponse>builder()
                        .result(typeService.createPostType(request))
                        .build()
                );
    }
    @GetMapping
    public ResponseEntity<MessageResponse<DataResponse<PostTypeResponse>>> getAllPageable(
            @RequestParam(value = "pageNumber",defaultValue = PagePrepare.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = PagePrepare.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = PagePrepare.SORT_BY, required = false) String sortBy) {

        return ResponseEntity
                .status(HttpStatus.OK)
                    .body(MessageResponse.<DataResponse<PostTypeResponse>>builder()
                    .result(typeService.getAll(pageNumber,pageSize,sortBy))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse<PostTypeResponse>> getCategory(@PathVariable int id){

        return ResponseEntity
                .status(HttpStatus.OK)
                    .body(MessageResponse.<PostTypeResponse>builder()
                    .result(typeService.getPostTypeByID(id))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse<PostTypeResponse>> updateCategory(@RequestBody @Valid PostTypeUpdate typeUpdate, @PathVariable int id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<PostTypeResponse>builder()
                    .result(typeService.updatePostType(id,typeUpdate))
                    .build()
                );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {

        typeService.delete(id);

        return ResponseEntity.noContent().build();

    }





}
