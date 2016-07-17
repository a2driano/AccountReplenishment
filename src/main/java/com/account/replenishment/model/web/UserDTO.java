package com.account.replenishment.model.web;

import com.account.replenishment.model.UserRole;
import com.account.replenishment.model.entity.Journal;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 29.06.2016
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO implements Serializable {
    @JsonProperty("id")
    private Long idUser;
    @JsonIgnore
    private Long idAdmin;
    @NotEmpty(message = "Please enter your email address.")
    @Email(message = "Некорректный e-mail")
    @JsonProperty("email")
    private String email;
    @NotNull
    @JsonIgnore
    private String password;
    @JsonProperty("balance")
    private Double balance;
    @JsonProperty("date")
    private Date registrationDate;
    @JsonIgnore
    private UserRole userRole;
    @JsonIgnore
    private List<Journal> journalList;
    @JsonIgnore
    private List<Journal> journalListAdmin;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Journal> getJournalList() {
        return journalList;
    }

    public void setJournalList(List<Journal> journalList) {
        this.journalList = journalList;
    }

    public List<Journal> getJournalListAdmin() {
        return journalListAdmin;
    }

    public void setJournalListAdmin(List<Journal> journalListAdmin) {
        this.journalListAdmin = journalListAdmin;
    }
}
