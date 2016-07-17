package com.account.replenishment.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 15.07.2016
 */
public class UserRegistration {
    @NotEmpty(message = "Пожалуйста, введите Ваш Е-mail адрес")
    @Email(message = "Некорректный e-mail")
    @JsonProperty("email")
    private String email;
    @NotEmpty(message = "Заполните это поле")
    private String password;
    @NotEmpty(message = "Заполните это поле")
    private String passwordRepeat;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}
