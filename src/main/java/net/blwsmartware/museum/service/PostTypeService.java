package net.blwsmartware.museum.service;

import net.blwsmartware.museum.dto.request.post.PostTypeRequest;
import net.blwsmartware.museum.dto.request.post.PostTypeUpdate;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.post.PostTypeResponse;

import java.util.List;

public interface PostTypeService {
    PostTypeResponse createPostType(PostTypeRequest request);
    PostTypeResponse getPostTypeByID(int id);
    PostTypeResponse updatePostType(int id, PostTypeUpdate update);
    DataResponse<PostTypeResponse> getAll(Integer pageNumber, Integer pageSize, String sortBy);
    void delete(int id);
}
