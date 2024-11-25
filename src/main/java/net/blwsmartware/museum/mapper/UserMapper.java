package net.blwsmartware.museum.mapper;

import net.blwsmartware.museum.dto.request.account.UserRequest;
import net.blwsmartware.museum.dto.request.account.UserUpdate;
import net.blwsmartware.museum.dto.response.user.UserResponse;
import net.blwsmartware.museum.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    User toUser(UserRequest request);
    @Mapping(target = "roles", ignore = true)
    void updateUser(UserUpdate userUpdate,@MappingTarget User user);
    UserResponse toUserResponse(User user);
}
