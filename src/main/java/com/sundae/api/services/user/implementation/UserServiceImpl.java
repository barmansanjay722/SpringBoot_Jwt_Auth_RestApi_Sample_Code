package com.sundae.api.services.user.implementation;

import com.sundae.api.data.entities.*;
import com.sundae.api.data.repos.*;
import com.sundae.api.mapper.UserMapper;
import com.sundae.api.models.request.AddUserRequest;
import com.sundae.api.models.response.UserResponse;
import com.sundae.api.services.email.EmailService;
import com.sundae.api.services.user.UserService;
import com.sundae.api.utils.EncryptionUtils;
import com.sundae.api.utils.PasswordGenerateUtils;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Service implementation for handling user-related operations.
 */
@AllArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RolesRespository rolesRespository;

    private final CompanyBranchRepository companyBranchRepository;

    private final CompanyDepartmentRepository companyDepartmentRepository;

    private final UserRolesRepository userRolesRepository;

    private final EmailService emailService;

    private final Environment env;

    /**
     * Retrieves user information based on the provided user ID.
     *
     * @param userId The unique identifier of the user.
     * @return UserResponse containing user details (ID, email, phone number, name).
     */
    @Override
    public UserResponse getUserId(String userId) {

        UserResponse userResponse = null;
        PageRequest pageRequest = PageRequest.of(0, 1);
        Users users = userRepository.findByIdAndStatus(userId, "ACTIVE", pageRequest);
        if (users != null) {
            userResponse = userResponse
                    .builder()
                    .id(users.getId())
                    .email(users.getEmail())
                    .name(users.getName())
                    .build();
        }
        return userResponse;
    }

    /**
     * Saves a new User based on the provided request.
     *
     * @param addUserRequest the request containing the User data to be saved
     * @return the response containing the saved User data
     */
    @Override
    public UserResponse save(AddUserRequest addUserRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {

        log.info("Saving new User");

        String userEmail = addUserRequest.getEmail();
        if (userRepository.findByEmail(userEmail) != null) {
            throw new RuntimeException("Email " + userEmail + " is already in use!");
        }

        CompanyDepartment companyDepartment = validateAndGetDepartment(addUserRequest.getDepartment());
        CompanyBranches companyBranch = validateAndGetBranch(addUserRequest.getBranch());
        Roles role = validateAndGetRole(addUserRequest.getRole());

        String password = PasswordGenerateUtils.generateSecureRandomPassword();
        String passwordSalt = env.getProperty("app.passwordSalt");
        String passwordHash = EncryptionUtils.generateHash(password, passwordSalt);

        Users user = UserMapper.mapAddUserRequestToUser(
                addUserRequest, companyDepartment.getId(), companyBranch.getId(), passwordSalt, passwordHash);
        user = userRepository.save(user);

        UserRoles userRoles = UserMapper.mapUserToUserRoles(user.getId(), role.getId());
        userRolesRepository.save(userRoles);
        log.info("User with email {} successfully saved.", userEmail);

        emailService.sendMessageToEmail(password, userEmail);
        log.info("User with email {} successfully send.", userEmail);

        return toModel(user, companyBranch.getBranchName(), companyDepartment.getDepartmentName(), role.getRoleName());
    }

    /**
     * Validates and retrieves the department based on the provided department name.
     *
     * @param departmentName The name of the department.
     * @return The validated CompanyDepartment.
     * @throws RuntimeException If the provided department is not valid.
     */
    private CompanyDepartment validateAndGetDepartment(String departmentName) {
        CompanyDepartment companyDepartment = companyDepartmentRepository.findByDepartmentName(departmentName);
        if (companyDepartment == null) {
            log.error("Department {} is not valid", departmentName);
            throw new RuntimeException("Department " + departmentName + " is not Exists");
        }
        return companyDepartment;
    }

    /**
     * Validates and retrieves the branch based on the provided branch name.
     *
     * @param branchName The name of the branch.
     * @return The validated CompanyBranches.
     * @throws RuntimeException If the provided branch is not valid.
     */
    private CompanyBranches validateAndGetBranch(String branchName) {
        CompanyBranches companyBranch = companyBranchRepository.findByBranchName(branchName);
        if (companyBranch == null) {
            log.error("BranchName {} is not valid", branchName);
            throw new RuntimeException("BranchName " + branchName + " is not valid");
        }
        return companyBranch;
    }

    /**
     * Validates and retrieves the role based on the provided role name.
     *
     * @param roleName The name of the role.
     * @return The validated Roles.
     * @throws RuntimeException If the provided role is not valid.
     */
    private Roles validateAndGetRole(String roleName) {
        Roles role = rolesRespository.findByRoleName(roleName);
        if (role == null) {
            log.error("RoleName {} is not valid", roleName);
            throw new RuntimeException("RoleName " + roleName + " is not Present");
        }
        return role;
    }

    /**
     * Maps a user to a UserResponse with additional information.
     *
     * @param user           The user to be mapped.
     * @param branchName     The name of the branch.
     * @param departmentName The name of the department.
     * @param roleName       The name of the role.
     * @return A UserResponse containing user information.
     */
    private UserResponse toModel(Users user, String branchName, String departmentName, String roleName) {
        return UserMapper.mapUserToUserResponse(user, branchName, departmentName, roleName);
    }
}
