package net.blwsmartware.museum.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.blwsmartware.museum.constant.PagePrepare;
import net.blwsmartware.museum.dto.request.post.PostRequest;
import net.blwsmartware.museum.dto.request.post.PostUpdate;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.MessageResponse;
import net.blwsmartware.museum.dto.response.post.PostResponse;
import net.blwsmartware.museum.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class PostController {

    PostService postService;

    @PostMapping
    public ResponseEntity<MessageResponse<PostResponse>> createPost(@RequestBody @Valid PostRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.<PostResponse>builder()
                        .result(postService.createPost(request))
                        .build()
                );
    }
    @GetMapping
    public ResponseEntity<MessageResponse<DataResponse<PostResponse>>> getAllPageable(
            @RequestParam(value = "pageNumber",defaultValue = PagePrepare.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = PagePrepare.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = PagePrepare.SORT_BY, required = false) String sortBy) {

        return ResponseEntity
                .status(HttpStatus.OK)
                    .body(MessageResponse.<DataResponse<PostResponse>>builder()
                    .result(postService.getAll(pageNumber,pageSize,sortBy))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse<PostResponse>> getPost(@PathVariable int id){

        return ResponseEntity
                .status(HttpStatus.OK)
                    .body(MessageResponse.<PostResponse>builder()
                    .result(postService.getPostByID(id))
                .build());
    }
    @GetMapping("/type/{id}")
    public ResponseEntity<MessageResponse<DataResponse<PostResponse>>> getByCategory(
            @PathVariable int id,
            @RequestParam(value = "pageNumber",defaultValue = PagePrepare.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = PagePrepare.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = PagePrepare.SORT_BY, required = false) String sortBy ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<DataResponse<PostResponse>>builder()
                        .result(postService.getPostByType(id,pageNumber,pageSize,sortBy))
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse<PostResponse>> updateMuseum(@RequestBody @Valid PostUpdate postUpdate, @PathVariable int id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<PostResponse>builder()
                    .result(postService.updatePost(id,postUpdate))
                    .build()
                );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {

        postService.delete(id);

        return ResponseEntity.noContent().build();

    }





}
