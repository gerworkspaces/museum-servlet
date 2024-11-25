package net.blwsmartware.museum.mapper;

import net.blwsmartware.museum.dto.request.post.PostTypeRequest;
import net.blwsmartware.museum.dto.request.post.PostTypeUpdate;
import net.blwsmartware.museum.dto.response.post.PostTypeResponse;
import net.blwsmartware.museum.entity.PostType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostTypeMapper {
    PostType toPostType(PostTypeRequest request);
    void updatePostType(PostTypeUpdate post, @MappingTarget PostType p);
    PostTypeResponse toPostTypeResponse(PostType postType);
}
