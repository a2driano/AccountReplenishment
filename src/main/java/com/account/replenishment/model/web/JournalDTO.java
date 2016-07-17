package com.account.replenishment.model.web;

import com.account.replenishment.model.entity.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 29.06.2016
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class JournalDTO {
    @JsonProperty("idJournal")
    private Long idJournal;
    @JsonProperty("sum")
    private Double sum;
    @JsonProperty("date")
    private Date dateJournal;
    @JsonProperty("user")
    private User user;
    @JsonProperty("admin")
    private User admin;

    public Long getIdJournal() {
        return idJournal;
    }

    public void setIdJournal(Long idJournal) {
        this.idJournal = idJournal;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Date getDateJournal() {
        return dateJournal;
    }

    public void setDateJournal(Date dateJournal) {
        this.dateJournal = dateJournal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
