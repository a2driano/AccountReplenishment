package com.account.replenishment.model.entity;

import com.account.replenishment.model.UserRole;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 29.06.2016
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id_user")
    @GenericGenerator(name = "kaugen", strategy = "increment")
    @GeneratedValue(generator = "kaugen")
    private Long idUser;
    @Column(name = "id_admin")
    private Long idAdmin;
    @Column(name = "email_user", unique = true, nullable = false, length = 100)
    private String email;
    @Column(name = "password_user", nullable = false, length = 100)
    private String password;
    @Column(name = "balance_user", nullable = false, precision = 20, scale = 2)
    private Double balance;
    @Column(name = "registration_date")
    private Date registrationDate;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Journal> journalList;
    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
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
