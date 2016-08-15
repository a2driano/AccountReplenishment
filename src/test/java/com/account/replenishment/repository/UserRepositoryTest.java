package com.account.replenishment.repository;

import com.account.replenishment.config.RootContextConfig;
import com.account.replenishment.model.entity.User;
import com.account.replenishment.repositories.UserRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;


import static org.assertj.core.api.Assertions.*;

/**
 * Created by a2driano on 14.08.2016.
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
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void getUserByEmail() {
        String email = "uriel@yandex.ru";
        User user = userRepository.getUserByEmail(email);
        assertThat(user.getIdUser()).isEqualTo(1L);
    }

    @Test
    public void userExist() {
        String email = "dovahkiin@gmail.com";
        assertThat(true).isEqualTo(userRepository.userExist(email));
    }

    @Test
    public void getCountOfUsersPlusPagination() {
        String email = "a";
        assertThat(2L).isEqualTo(userRepository.getCountOfUsersPlusPagination(email));
    }

    @Test
    public void getAllUserPlusPagination() {
        Pageable pageRequest = new PageRequest(0, 10);
        String email = "a";
        assertThat(2).isEqualTo(userRepository.getAllUserPlusPagination(email, pageRequest).size());
    }

    @Test
    public void getUserById() {
        Long id = 1L;
        User user = userRepository.findOne(id);
        assertThat(user.getIdUser()).isEqualTo(id);
    }
}
