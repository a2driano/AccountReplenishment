package com.account.replenishment.controller;

import com.account.replenishment.config.ServletContextConfig;
import com.account.replenishment.config.WebAppInitializer;
import com.account.replenishment.model.Status;
import com.account.replenishment.model.StatusDTO;
import com.account.replenishment.model.UserRole;
import com.account.replenishment.model.web.UserDTO;
import com.account.replenishment.service.UserService;
import com.account.replenishment.util.UserRegistration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;

/**
 * Created by Admin on 23.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppInitializer.class})
public class UserControllerTest {
    @Mock
    private UserService userServiceMock;
    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testMainPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));

    }

    @Test
    public void testRegistrationPage() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("signupuser"));
    }

    @Test
    public void testSaveNewUser() throws Exception {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        ModelAndView modelAndView = new ModelAndView("signupuser");
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", "auriel")
                        .param("password", "pass")
                        .param("passwordRepeat", "pass")
                        .sessionAttr("userReg", new UserRegistration())
        )
                .andExpect(status().isOk())
                .andExpect(view().name("signupuser"));

        String email = "auriel";
        String password = "pass";
        String passwordRepeat = "pass";

        modelAndView.addObject("email", email);
        modelAndView.addObject("password", password);
        modelAndView.addObject("passwordRepeat", passwordRepeat);

        if (!userServiceMock.userExist(email)) {
            modelAndView.addObject("messageErrorEmail", "Данный E-mail уже используется!");
            assertEquals(true, modelAndView.getModel().containsKey("messageErrorEmail"));
        }
        if (password.equals(passwordRepeat)) {
            modelAndView.addObject("messageError", "Пароли должны совпадать");
            assertEquals(true, modelAndView.getModel().containsKey("messageError"));
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setRegistrationDate(new Date());
        userDTO.setBalance(0.0);
        userDTO.setUserRole(UserRole.USER);
        StatusDTO statusDTO = userServiceMock.addUser(userDTO);
        if (new StatusDTO().setStatus(Status.SUCCESS) == Status.SUCCESS)
            modelAndView.addObject("messageSuccess", "Ваш аккаунт успешно создан!");
        if (new StatusDTO().setStatus(Status.FAIL) == Status.FAIL)
            modelAndView.addObject("messageErrorEmail", "Ошибка сервера! Повторите через 5 минут!");

        assertEquals(true, modelAndView.getModel().containsKey("messageSuccess"));
        Authentication auth = new UsernamePasswordAuthenticationToken(email, password,
                AuthorityUtils.createAuthorityList("ROLE_USER"));
        SecurityContextHolder.getContext().setAuthentication(auth);
        assertEquals("auriel", auth.getName());
        assertEquals("signupuser", modelAndView.getViewName());
        assertEquals("auriel", modelAndView.getModel().get("email"));
        assertEquals("pass", modelAndView.getModel().get("password"));
        assertEquals("pass", modelAndView.getModel().get("passwordRepeat"));
    }

    @Test
    public void testUserPage() throws Exception {
        ModelAndView modelAndView = new ModelAndView("myuserpage");

        Authentication auth = new UsernamePasswordAuthenticationToken("auriel", "pass",
                AuthorityUtils.createAuthorityList("ROLE_USER"));
        SecurityContextHolder.getContext().setAuthentication(auth);

        String email = auth.getName();
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        modelAndView.addObject("user", userDTO);
        mockMvc.perform(get("/userpage"))
                .andExpect(status().isOk())
                .andExpect(view().name("myuserpage"));
    }
}