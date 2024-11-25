package net.blwsmartware.museum.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.blwsmartware.museum.dto.request.post.PostTypeRequest;
import net.blwsmartware.museum.dto.request.post.PostTypeUpdate;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.post.PostTypeResponse;
import net.blwsmartware.museum.entity.PostType;
import net.blwsmartware.museum.enums.ErrorResponse;
import net.blwsmartware.museum.exception.AppRuntimeException;
import net.blwsmartware.museum.mapper.PostTypeMapper;
import net.blwsmartware.museum.repository.PostTypeRepository;
import net.blwsmartware.museum.service.PostTypeService;
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
public class PostTypeServiceImp implements PostTypeService {

    PostTypeMapper postTypeMapper;
    PostTypeRepository postTypeRepository;

    @Override
    public PostTypeResponse createPostType(PostTypeRequest request) {
        PostType postType = postTypeMapper.toPostType(request);
        return postTypeMapper.toPostTypeResponse(postTypeRepository.save(postType));
    }

    @Override
    public PostTypeResponse getPostTypeByID(int id) {
        return postTypeMapper.toPostTypeResponse(postTypeRepository.findById(id)
                .orElseThrow(() -> new AppRuntimeException(ErrorResponse.TYPE_NOT_FOUND) ));
    }

    @Override
    public PostTypeResponse updatePostType(int id, PostTypeUpdate update) {
        PostType old = postTypeRepository.findById(id)
                .orElseThrow(() -> new AppRuntimeException(ErrorResponse.TYPE_NOT_FOUND));
        postTypeMapper.updatePostType(update,old);
        return postTypeMapper.toPostTypeResponse(postTypeRepository.save(old));
    }

    @Override
    public DataResponse<PostTypeResponse> getAll(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<PostType> pageOfPostType = postTypeRepository.findAll(pageable);
        List<PostType> typeList = pageOfPostType.getContent();
        List<PostTypeResponse> typeResponses = typeList.stream().map(postTypeMapper::toPostTypeResponse).toList();
        return DataResponseUtils.convertPageInfo(pageOfPostType,typeResponses);
    }

    @Override
    public void delete(int id) {
        postTypeRepository.deleteById(id);
    }
}
