package net.blwsmartware.museum.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.blwsmartware.museum.constant.PagePrepare;
import net.blwsmartware.museum.dto.request.account.*;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.MessageResponse;
import net.blwsmartware.museum.dto.response.user.UserResponse;
import net.blwsmartware.museum.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class UserController {

    UserService userService;

    @PostMapping
    public ResponseEntity<MessageResponse<UserResponse>> createUser(@RequestBody @Valid  UserRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.<UserResponse>builder()
                    .result(userService.createUser(request))
                    .build()
                );
    }

    @GetMapping
    public ResponseEntity<MessageResponse<DataResponse<UserResponse>>> getAllPageable(
            @RequestParam(value = "pageNumber",defaultValue = PagePrepare.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = PagePrepare.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = PagePrepare.SORT_BY, required = false) String sortBy) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(MessageResponse.<DataResponse<UserResponse>>builder()
                    .result(userService.getAll(pageNumber,pageSize,sortBy))
                    .build()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse<UserResponse>> getUser(@PathVariable long id){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<UserResponse>builder()
                    .result(userService.getUserByID(id))
                    .build()
                );
    }

    @GetMapping("/me")
    public ResponseEntity<MessageResponse<UserResponse>> me(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<UserResponse>builder()
                    .result(userService.getUserByID(Long.parseLong(authentication.getName())))
                    .build()
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse<UserResponse>> updateUser(@RequestBody @Valid  UserUpdate user, @PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<UserResponse>builder()
                    .result(userService.updateUser(id,user))
                    .build()
                );
    }
    @PutMapping("/username/{id}")
    public ResponseEntity<MessageResponse<UserResponse>> updateUsername(@RequestBody @Valid UsernameUserUpdate user, @PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<UserResponse>builder()
                        .result(userService.updateUsername(id,user))
                        .build()
                );
    }
    @PutMapping("/email/{id}")
    public ResponseEntity<MessageResponse<UserResponse>> updateEmail(@RequestBody @Valid EmailUserUpdate user, @PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<UserResponse>builder()
                        .result(userService.updateEmail(id,user))
                        .build()
                );
    }
    @PutMapping("/passwd/{id}")
    public ResponseEntity<MessageResponse<UserResponse>> updatePasswd(@RequestBody @Valid PasswordUserUpdate user, @PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<UserResponse>builder()
                        .result(userService.updatePassword(id,user))
                        .build()
                );
    }
    @PutMapping("/status/{id}")
    public ResponseEntity<MessageResponse<UserResponse>> disableUser(@RequestBody @Valid ActiveUserUpdate user, @PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<UserResponse>builder()
                        .result(userService.disableUser(id,user))
                        .build()
                );
    }
    @PutMapping("/role/{id}")
    public ResponseEntity<MessageResponse<UserResponse>> updateRoleOfUser(@RequestBody RoleOfUpdate roles, @PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MessageResponse.<UserResponse>builder()
                        .result(userService.updateRoleOfUser(id,roles))
                        .build()
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();

    }

}
