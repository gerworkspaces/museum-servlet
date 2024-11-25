package net.blwsmartware.museum.service;

import net.blwsmartware.museum.dto.request.role.PermissionRequest;
import net.blwsmartware.museum.dto.request.role.PermissionUpdate;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.role.PermissionResponse;

public interface PermissionService {
    PermissionResponse createPermission(PermissionRequest request);
    PermissionResponse getPermissionByID(int id);
    PermissionResponse getPermissionByName(String name);
    PermissionResponse updatePermission(int id, PermissionUpdate permissionUpdate);
    void deletePermission(int id);
    DataResponse<PermissionResponse> getAll(Integer pageNumber, Integer pageSize, String sortBy);
}
