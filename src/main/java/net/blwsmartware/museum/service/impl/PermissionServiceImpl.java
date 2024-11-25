package net.blwsmartware.museum.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.blwsmartware.museum.dto.request.role.PermissionRequest;
import net.blwsmartware.museum.dto.request.role.PermissionUpdate;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.role.PermissionResponse;
import net.blwsmartware.museum.entity.Permission;
import net.blwsmartware.museum.enums.ErrorResponse;
import net.blwsmartware.museum.exception.AppRuntimeException;
import net.blwsmartware.museum.mapper.PermissionMapper;
import net.blwsmartware.museum.repository.PermissionRepository;
import net.blwsmartware.museum.service.PermissionService;
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
public class PermissionServiceImpl implements PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse createPermission(PermissionRequest request) {
//        if (permissionRepository.findByName(request.getName()).isPresent())
//            throw new IdentityRuntimeException(ErrorResponse.PERMISSION_EXISTED);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permissionMapper.toPermission(request)));
    }


    @Override
    public DataResponse<PermissionResponse> getAll(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Permission> pageOfPermission = permissionRepository.findAll(pageable);
        List<Permission> permissions = pageOfPermission.getContent();
        List<PermissionResponse> content = permissions.stream().map(permissionMapper::toPermissionResponse).toList();
        return DataResponseUtils.convertPageInfo(pageOfPermission , content );
    }

    @Override
    public PermissionResponse getPermissionByID(int id) {
        return permissionMapper.toPermissionResponse(permissionRepository.findById(id).orElseThrow(() -> new AppRuntimeException(ErrorResponse.PERMISSION_NOT_EXISTED)));
    }

    @Override
    public PermissionResponse getPermissionByName(String name) {
        return permissionMapper.toPermissionResponse(permissionRepository.findByName(name).orElseThrow(() -> new AppRuntimeException(ErrorResponse.PERMISSION_NOT_EXISTED)));
    }

    @Override
    public PermissionResponse updatePermission(int id, PermissionUpdate request) {

        Permission old = permissionRepository.findById(id).orElseThrow(() -> new AppRuntimeException(ErrorResponse.PERMISSION_NOT_EXISTED));
        permissionMapper.updatePermission(request, old);
        return permissionMapper.toPermissionResponse(permissionRepository.save(old));
    }

    public void deletePermission(int id) {
        permissionRepository.deleteById(id);
    }

}
