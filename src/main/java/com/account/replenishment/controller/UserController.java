package com.account.replenishment.controller;

import com.account.replenishment.model.StatusDTO;
import com.account.replenishment.model.UserRole;
import com.account.replenishment.model.web.UserDTO;
import com.account.replenishment.service.UserService;
import com.account.replenishment.util.ConvertUtilUser;
import com.account.replenishment.util.UserRegistration;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private ConvertUtilUser convertUtilUser;

    /**
     * Return start page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        return "login";
    }

    /**
     * Return sign up page
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String registrationPage() {
        return "signup";
    }

    /**
     * Method do few operation:
     * 1. Validation E-mail input (for non-empty and write valid correctly).
     * 2. This E-mail equals in DB?
     * 3. Password and repeat password must be equals.
     * 4. All errors method return to User.
     * 5. If no errors - save new User and make the role is 'User'.
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView saveNewUser(@ModelAttribute("userReg") @Valid UserRegistration userRegistration, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("signup");
        String email = userRegistration.getEmail();
        String password = userRegistration.getPassword();
        String passwordRepeat = userRegistration.getPasswordRepeat();

        modelAndView.addObject("email", email);
        modelAndView.addObject("password", password);
        modelAndView.addObject("passwordRepeat", passwordRepeat);

        if (result.hasErrors()) {
        } else if (userService.userExist(email)) {
            modelAndView.addObject("messageErrorEmail", "Данный E-mail уже используется!");
        } else if (!password.equals(passwordRepeat)) {
            modelAndView.addObject("messageError", "Пароли должны совпадать");
        } else {
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(email);
            userDTO.setPassword(password);
            userDTO.setRegistrationDate(new Date());
            userDTO.setBalance(0.0);
            userDTO.setUserRole(UserRole.USER);
            StatusDTO statusDTO = userService.addUser(userDTO);
            if (statusDTO.getStatus().name() == "FAIL") {
                return modelAndView.addObject("messageErrorEmail", "Ошибка сервера! Повторите через 5 минут!");
            }
            modelAndView.addObject("messageSuccess", "Ваш аккаунт успешно создан!");
            List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
            auth.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return UserRole.USER.name();
                }
            });
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword(), auth);
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        return modelAndView;
    }

    /**
     * This method return balance of current user
     */
    @RequestMapping(value = "/userpage", method = RequestMethod.GET)
    public ModelAndView userPage() {
        ModelAndView modelAndView = new ModelAndView("userpage");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        UserDTO userDTO = userService.getUserByEmail(email);
        modelAndView.addObject("user", userDTO);
        return modelAndView;
    }
}