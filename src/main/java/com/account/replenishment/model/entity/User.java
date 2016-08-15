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

    public User setUserRole(UserRole userRole) {
        this.userRole = userRole;
        return null;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (idUser != null ? !idUser.equals(user.idUser) : user.idUser != null) return false;
        if (idAdmin != null ? !idAdmin.equals(user.idAdmin) : user.idAdmin != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (balance != null ? !balance.equals(user.balance) : user.balance != null) return false;
        if (registrationDate != null ? !registrationDate.equals(user.registrationDate) : user.registrationDate != null)
            return false;
        if (userRole != user.userRole) return false;
        if (journalList != null ? !journalList.equals(user.journalList) : user.journalList != null) return false;
        return !(journalListAdmin != null ? !journalListAdmin.equals(user.journalListAdmin) : user.journalListAdmin != null);

    }

    @Override
    public int hashCode() {
        int result = idUser != null ? idUser.hashCode() : 0;
        result = 31 * result + (idAdmin != null ? idAdmin.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        result = 31 * result + (journalList != null ? journalList.hashCode() : 0);
        result = 31 * result + (journalListAdmin != null ? journalListAdmin.hashCode() : 0);
        return result;
    }
}
