package net.blwsmartware.museum.mapper;

import net.blwsmartware.museum.dto.request.role.PermissionRequest;
import net.blwsmartware.museum.dto.request.role.PermissionUpdate;
import net.blwsmartware.museum.dto.response.role.PermissionResponse;
import net.blwsmartware.museum.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    void updatePermission(PermissionUpdate userUpdate, @MappingTarget Permission permission);
    PermissionResponse toPermissionResponse(Permission permission);

}
