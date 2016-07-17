package com.account.replenishment.util;

import com.account.replenishment.model.entity.User;
import com.account.replenishment.model.web.UserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 29.06.2016
 */
@Component
public class ConvertUtilUserOLD {
    public User convertUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setIdUser(userDTO.getIdUser());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setBalance(userDTO.getBalance());
        user.setRegistrationDate(userDTO.getRegistrationDate());
        user.setUserRole(userDTO.getUserRole());
        user.setJournalList(userDTO.getJournalList());

        return user;
    }

    public List<User> convertUserDTOListToUserList(List<UserDTO> userDTOList) {
        List<User> userList = new ArrayList<>();
        for (UserDTO userDTO : userDTOList) {
            userList.add(convertUserDTOToUser(userDTO));
        }
        return userList;
    }

    public UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setIdUser(user.getIdUser());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setBalance(user.getBalance());
        userDTO.setRegistrationDate(user.getRegistrationDate());
        userDTO.setUserRole(user.getUserRole());
        userDTO.setJournalList(user.getJournalList());

        return userDTO;
    }

    public List<UserDTO> convertUserListToUserDTOList(List<User> userList) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(convertUserToUserDTO(user));
        }
        return userDTOList;
    }
}
