package com.account.replenishment.service;

import com.account.replenishment.model.Status;
import com.account.replenishment.model.StatusDTO;
import com.account.replenishment.model.entity.User;
import com.account.replenishment.model.web.UserDTO;
import com.account.replenishment.repositories.UserRepository;
import com.account.replenishment.util.ConvertUtilUser;
import org.hibernate.HibernateException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 30.06.2016
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConvertUtilUser convertUtilUser;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public StatusDTO addUser(UserDTO userDTO) {
        User user = convertUtilUser.convertUserDTOToUser(userDTO);
        StatusDTO statusDTO = new StatusDTO();
        try {
            user = userRepository.saveAndFlush(user);
            statusDTO.setStatus(Status.SUCCESS);
        } catch (HibernateException e) {
            LOGGER.error("{}", e.toString(), e);
            statusDTO.setStatus(Status.FAIL);
        }
        return statusDTO;
    }

    @Override
    public UserDTO getUserById(Long id) {
        UserDTO userDTO = new UserDTO();
        try {
            userDTO = convertUtilUser.convertUserToUserDTO(userRepository.findOne(id));
        } catch (HibernateException e) {
            LOGGER.error("{}", e.toString(), e);
        }
        return userDTO;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserDTO userDTO = new UserDTO();
        try {
            userDTO = convertUtilUser.convertUserToUserDTO(userRepository.getUserByEmail(email));
        } catch (HibernateException e) {
            LOGGER.error("{}", e.toString(), e);
        }
        return userDTO;
    }

    @Override
    public Boolean userExist(String email) {
        Boolean exist = new Boolean(true);
        try {
            exist = userRepository.userExist(email);
        } catch (HibernateException e) {
            LOGGER.error("{}", e.toString(), e);
        }
        return exist;
    }

    @Override
    public void deleteUser(Long id) {
        try {
            userRepository.delete(id);
        } catch (HibernateException e) {
            LOGGER.error("{}", e.toString(), e);
        }
    }

    @Override
    public List<UserDTO> getAllUserPlusPagination(String email, Pageable pageRequest) {
        if (email == null)
            email = "";
        List<UserDTO> userDTOList;
        try {
            userDTOList = convertUtilUser
                    .convertUserListToUserDTOList(userRepository.getAllUserPlusPagination(email, pageRequest));
        } catch (HibernateException e) {
            LOGGER.error("{}", e.toString(), e);
            return null;
        }
        return userDTOList;
    }

    @Override
    public Long getCountOfUsersPlusPagination(String email) {
        Long count = 0L;
        if (email == null)
            email = "";
        try {
            count = userRepository.getCountOfUsersPlusPagination(email);
        } catch (HibernateException e) {
            LOGGER.error("{}", e.toString(), e);
        }
        return count;
    }
}
