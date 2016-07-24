package com.account.replenishment.service;

import com.account.replenishment.controller.AdminController;
import com.account.replenishment.controller.UserController;
import com.account.replenishment.model.UserRole;
import com.account.replenishment.model.entity.User;
import com.account.replenishment.model.web.UserDTO;
import com.account.replenishment.repositories.UserRepository;
import com.account.replenishment.util.ConvertUtilUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Admin on 21.07.2016.
 */
public class UserServiceImplTest {
    //    private UserController userController;
    private UserRepository userRepository;
    private UserService userService = new UserServiceImpl();
    private ConvertUtilUser convertUtilUser = new ConvertUtilUser();
    private User user = new User();

    @Before
    public void setUp() {
        userRepository = mock(UserRepository.class);
        user.setIdUser(34L);
        user.setEmail("test@gmail.com");
        user.setPassword("qwerty");
        user.setBalance(11.2);
        user.setUserRole(UserRole.USER);
        when(userRepository.getUserByEmail("test@gmail.com")).thenReturn(user);
    }

    @Test
    public void testSuccessReturnUserByEmail() {

    }

    @Test
    public void testAddUser() throws Exception {

    }

    @Test
    public void testGetUserById() throws Exception {

    }

    @Test
    public void testGetUserByEmail() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("a@a");
        userDTO.setPassword("qwerty");

        User user = new User();
        user.setEmail("a@a");
        user.setPassword("qwerty");
        assertThat(user, instanceOf(User.class));
        assertNotNull(user.getEmail());
        assertNotNull(user);
    }

    @Test
    public void testUserExist() throws Exception {

    }

    @Test
    public void testDeleteUser() throws Exception {

    }

    @Test
    public void testGetAllUserPlusPagination() throws Exception {

    }

    @Test
    public void testGetCountOfUsersPlusPagination() throws Exception {

    }
}