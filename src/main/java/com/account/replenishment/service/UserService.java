package com.account.replenishment.service;

import com.account.replenishment.model.StatusDTO;
import com.account.replenishment.model.web.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 29.06.2016
 */
public interface UserService {
    /**
     * Add new user to DB
     *
     * @param userDTO
     * @return
     */
    StatusDTO addUser(UserDTO userDTO);

    /**
     * Return a UserDTO by id
     *
     * @param id
     * @return
     */
    UserDTO getUserById(Long id);

    /**
     * Return a UserDTO by E-mail
     *
     * @param email
     * @return
     */
    UserDTO getUserByEmail(String email);

    /**
     * Show exist User witn param E-mail in DB
     *
     * @param email
     * @return
     */
    Boolean userExist(String email);

    /**
     * Delete User
     *
     * @param id
     */
    void deleteUser(Long id);

    /**
     * Return a list of UserDTO
     * whose mail contains a search option (E-mail)
     *
     * @param email
     * @param pageRequest
     * @return
     */
    List<UserDTO> getAllUserPlusPagination(String email, Pageable pageRequest);

    /**
     * Return count Users
     * whose mail contains a search option (E-mail)
     *
     * @param email
     * @return
     */
    Long getCountOfUsersPlusPagination(String email);
}
