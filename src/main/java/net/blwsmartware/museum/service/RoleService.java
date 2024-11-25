package net.blwsmartware.museum.service;

import net.blwsmartware.museum.dto.request.role.RoleRequest;
import net.blwsmartware.museum.dto.request.role.RoleUpdate;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.role.RoleResponse;

public interface RoleService {
    RoleResponse createRole(RoleRequest request);
    RoleResponse getRoleByID(int id);
    RoleResponse getRoleByName(String name);
    RoleResponse updateRole(int id, RoleUpdate roleUpdate);
    void delete(int id);
    DataResponse<RoleResponse> getAll(Integer pageNumber, Integer pageSize, String sortBy);
}
