package com.account.replenishment.service;

import com.account.replenishment.config.RootContextConfig;
import com.account.replenishment.model.Status;
import com.account.replenishment.model.StatusDTO;
import com.account.replenishment.model.UserRole;
import com.account.replenishment.model.entity.User;
import com.account.replenishment.model.web.UserDTO;
import com.account.replenishment.repositories.UserRepository;
import com.account.replenishment.util.ConvertUtilUser;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by a2driano on 21.07.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootContextConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DbUnitConfiguration(databaseConnection = {"datasource"})
@DatabaseSetup("/testData.xml")
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    private ConvertUtilUser convertUtilUser;
    private User user;
    private UserDTO userDTO;
    private StatusDTO statusDTO;

    @Before
    public void setUp() {
        convertUtilUser = new ConvertUtilUser();
        statusDTO = new StatusDTO();

        user = new User();
        user.setIdUser(34L);
        user.setEmail("test@gmail.com");
        user.setPassword("qwerty");
        user.setBalance(11.2);
        user.setUserRole(UserRole.USER);

        userDTO = new UserDTO();
        userDTO.setIdUser(34L);
        userDTO.setEmail("test@gmail.com");
        userDTO.setPassword("qwerty");
        userDTO.setBalance(11.2);
        userDTO.setUserRole(UserRole.USER);
    }

    @Test
    public void testAddUserSuccess() throws Exception {
        user = convertUtilUser.convertUserDTOToUser(userDTO);
        userRepository.saveAndFlush(user);
        statusDTO.setStatus(Status.SUCCESS);
        assertEquals(Status.SUCCESS, statusDTO.getStatus());
    }

    @Test
    public void testAddUserFail() throws Exception {
        user = convertUtilUser.convertUserDTOToUser(userDTO);
        userRepository.saveAndFlush(user);
        statusDTO.setStatus(Status.FAIL);
        assertEquals(Status.FAIL, statusDTO.getStatus());
    }

    @Test
    public void testGetUserById() throws Exception {
        Long id = 1L;
        user = userRepository.findOne(id);
        userDTO = convertUtilUser.convertUserToUserDTO(user);
        assertEquals(id, userDTO.getIdUser());
    }

    @Test
    public void testGetUserByEmail() throws Exception {
        String email = "uriel@yandex.ru";
        user = userRepository.getUserByEmail(email);
        userDTO = convertUtilUser.convertUserToUserDTO(user);
        assertThat(user.getEmail()).isEqualTo(userDTO.getEmail());
    }

    @Test
    public void testUserExist() throws Exception {
        String email = "uriel@yandex.ru";
        Boolean exist = userRepository.userExist(email);
        assertEquals(true, exist);
    }

    @Test
    public void testUserExistFail() throws Exception {
        String email = "uriel23@yandex.ru";
        Boolean exist = userRepository.userExist(email);
        assertEquals(false, exist);
    }

    @Test
    public void testDeleteUser() throws Exception {
    }

    @Test
    public void testGetAllUserPlusPagination() throws Exception {
        String email = "";
        List<UserDTO> userDTOList;
        userDTOList = convertUtilUser
                .convertUserListToUserDTOList(userRepository.getAllUserPlusPagination(email, new PageRequest(0, 10)));
        assertEquals(2L, userDTOList.size());
    }

    @Test
    public void testGetCountOfUsersPlusPagination() throws Exception {
        String email = "";
        long count = userRepository.getCountOfUsersPlusPagination(email);
        assertEquals(2L, count);
    }
}