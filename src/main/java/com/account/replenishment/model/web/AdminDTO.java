package com.account.replenishment.model.web;

import com.account.replenishment.model.UserRole;
import com.account.replenishment.model.entity.Journal;

import java.util.List;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 29.06.2016
 */
public class AdminDTO {
    private Long idUser;
    private String email;
    private String password;
    private UserRole userRole;
    private List<Journal> journalList;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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
}
