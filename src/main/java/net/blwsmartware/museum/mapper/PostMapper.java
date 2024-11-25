package net.blwsmartware.museum.mapper;

import net.blwsmartware.museum.dto.request.post.PostRequest;
import net.blwsmartware.museum.dto.request.post.PostUpdate;
import net.blwsmartware.museum.dto.response.post.PostResponse;
import net.blwsmartware.museum.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "postType", ignore = true)
    Post toPost(PostRequest request);
    @Mapping(target = "postType", ignore = true)
    void updatePost(PostUpdate post, @MappingTarget Post p);
    PostResponse toPostResponse(Post post);
}
