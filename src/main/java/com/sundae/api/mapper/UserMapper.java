package com.sundae.api.mapper;

import com.sundae.api.constants.CommonStatus;
import com.sundae.api.data.entities.UserRoles;
import com.sundae.api.data.entities.Users;
import com.sundae.api.models.request.AddUserRequest;
import com.sundae.api.models.response.UserResponse;
import com.sundae.api.utils.UuidUtils;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserMapper {

    public static Users mapAddUserRequestToUser(
            AddUserRequest addUserRequest,
            Integer departmentId,
            Integer branchId,
            String passwordSalt,
            String passwordHash) {
        return Users.builder()
                .id(UuidUtils.generateUUID())
                .email(addUserRequest.getEmail())
                .name(addUserRequest.getFullName())
                .branchId(branchId)
                .departmentId(departmentId)
                .status(CommonStatus.ACTIVE.name())
                .passwordHash(passwordHash)
                .passwordSalt(passwordSalt)
                .build();
    }

    public static UserResponse mapUserToUserResponse(
            Users user, String branchName, String departmentName, String roleName) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .branch(branchName)
                .department(departmentName)
                .role(roleName)
                .build();
    }

    public static UserRoles mapUserToUserRoles(String userId, Integer roleId) {
        return UserRoles.builder().userId(userId).roleId(roleId).build();
    }
}
