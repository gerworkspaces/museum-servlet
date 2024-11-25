package net.blwsmartware.museum.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.blwsmartware.museum.constant.PagePrepare;
import net.blwsmartware.museum.dto.request.role.PermissionRequest;
import net.blwsmartware.museum.dto.request.role.PermissionUpdate;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.MessageResponse;
import net.blwsmartware.museum.dto.response.role.PermissionResponse;
import net.blwsmartware.museum.service.impl.PermissionServiceImpl;
import net.blwsmartware.museum.validator.IsAdmin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {

    PermissionServiceImpl permissionService;

    @PostMapping
    @IsAdmin
    public ResponseEntity<MessageResponse<PermissionResponse>> createPermission(@RequestBody @Valid PermissionRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.<PermissionResponse>builder()
                        .result(permissionService.createPermission(request))
                        .build()
                );
    }

    @GetMapping
    @IsAdmin
    public ResponseEntity<MessageResponse<DataResponse<PermissionResponse>>> getAllPageable(
            @RequestParam(value = "pageNumber",defaultValue = PagePrepare.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = PagePrepare.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = PagePrepare.SORT_BY, required = false) String sortBy) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<DataResponse<PermissionResponse>>builder()
                        .result(permissionService.getAll( pageNumber,pageSize,sortBy ))
                        .build());
    }

    @GetMapping("/name/{name}")
    @IsAdmin
    public ResponseEntity<MessageResponse<PermissionResponse>> getPermissionByName(@PathVariable String name) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.<PermissionResponse>builder()
                        .result(permissionService.getPermissionByName(name))
                        .build()
                );
    }

    @GetMapping("/{id}")
    @IsAdmin
    public ResponseEntity<MessageResponse<PermissionResponse>> getPermission(@PathVariable int id) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.<PermissionResponse>builder()
                        .result(permissionService.getPermissionByID(id))
                        .build()
                );
    }

    @PutMapping("/{id}")
    @IsAdmin
    public ResponseEntity<MessageResponse<PermissionResponse>> updatePermission(@RequestBody @Valid PermissionUpdate user, @PathVariable int id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.<PermissionResponse>builder()
                        .result(permissionService.updatePermission(id, user))
                        .build()
                );
    }

    @DeleteMapping("/{id}")
    @IsAdmin
    public ResponseEntity<?> deletePermission(@PathVariable int id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }

}
