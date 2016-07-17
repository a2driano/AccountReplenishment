package com.account.replenishment.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 29.06.2016
 */
@Entity
@Table(name = "journal")
public class Journal {
    @Id
    @Column(name = "id_journal")
    @GenericGenerator(name = "kaugen", strategy = "increment")
    @GeneratedValue(generator = "kaugen")
    private Long idJournal;
    @Column(name = "sum_journal")
    private Double sum;
    @Column(name = "date_journal")
    private Date dateJournal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_admin")
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
