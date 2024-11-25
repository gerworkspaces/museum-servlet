package net.blwsmartware.museum.service;

import net.blwsmartware.museum.dto.request.post.PostRequest;
import net.blwsmartware.museum.dto.request.post.PostUpdate;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.post.PostResponse;

public interface PostService {
    PostResponse createPost(PostRequest request);
    PostResponse getPostByID(long id);
    PostResponse updatePost(long id, PostUpdate update);
    DataResponse<PostResponse> getAll(Integer pageNumber, Integer pageSize, String sortBy);
    DataResponse<PostResponse> getPostByType(int id,Integer pageNumber, Integer pageSize, String sortBy);
    void delete(long id);
}
