package net.blwsmartware.museum.service;

import net.blwsmartware.museum.dto.request.account.*;
import net.blwsmartware.museum.dto.response.DataResponse;
import net.blwsmartware.museum.dto.response.user.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest request);
    DataResponse<UserResponse> getAll(Integer pageNumber, Integer pageSize, String sortBy);
    UserResponse getUserByID(long id);
    UserResponse getUserByEmail(String email);
    UserResponse getUserByUsername(String username);
    UserResponse updateUser(long id, UserUpdate request);
    UserResponse updateRoleOfUser(long id, RoleOfUpdate request);
    UserResponse updateEmail(long id, EmailUserUpdate request);
    UserResponse updateUsername(long id, UsernameUserUpdate request);
    UserResponse updatePassword(long id, PasswordUserUpdate request);
    UserResponse disableUser(long id, ActiveUserUpdate request);
    void deleteUser(long id);

}
