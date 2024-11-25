package net.blwsmartware.museum.mapper;

import net.blwsmartware.museum.dto.request.role.RoleRequest;
import net.blwsmartware.museum.dto.request.role.RoleUpdate;
import net.blwsmartware.museum.dto.response.role.RoleResponse;
import net.blwsmartware.museum.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    @Mapping(target = "permissions", ignore = true)
    void updateRole(RoleUpdate userUpdate, @MappingTarget Role newRole);
    RoleResponse toRoleResponse(Role role);

}
