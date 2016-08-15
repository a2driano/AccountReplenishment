package com.account.replenishment.util;

import com.account.replenishment.model.entity.User;
import com.account.replenishment.model.web.UserDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Admin on 10.08.2016.
 */
public class ConvertUtilUserTest {
    private ConvertUtilUser convertUtilUser = new ConvertUtilUser();
    private User user;
    private UserDTO userDTO;

    @Before
    public void setUp() {
        user = new User();
        user.setIdUser(34L);
        user.setEmail("test@gmail.com");

        userDTO = new UserDTO();
        userDTO.setIdUser(34L);
        userDTO.setEmail("test@gmail.com");
    }

    @Test
    public void testConvertUserDTOToUser() throws Exception {
        assertEquals(user.getEmail(), convertUtilUser.convertUserDTOToUser(userDTO).getEmail());
    }

    @Test
    public void testConvertUserDTOListToUserList() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(user);

        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(userDTO);

        assertEquals(userList.get(0).getEmail(), convertUtilUser.convertUserDTOListToUserList(userDTOList).get(0).getEmail());
    }

    @Test
    public void testConvertUserToUserDTO() throws Exception {
        assertEquals(userDTO.getEmail(), convertUtilUser.convertUserToUserDTO(user).getEmail());
    }

    @Test
    public void testConvertUserListToUserDTOList() throws Exception {
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(userDTO);

        List<User> userList = new ArrayList<>();
        userList.add(user);

        assertEquals(userDTOList.get(0).getEmail(), convertUtilUser.convertUserListToUserDTOList(userList).get(0).getEmail());
    }
}