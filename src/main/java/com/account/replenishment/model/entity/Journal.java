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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Journal journal = (Journal) o;

        if (idJournal != null ? !idJournal.equals(journal.idJournal) : journal.idJournal != null) return false;
        if (sum != null ? !sum.equals(journal.sum) : journal.sum != null) return false;
        if (dateJournal != null ? !dateJournal.equals(journal.dateJournal) : journal.dateJournal != null) return false;
        if (user != null ? !user.equals(journal.user) : journal.user != null) return false;
        return !(admin != null ? !admin.equals(journal.admin) : journal.admin != null);

    }

    @Override
    public int hashCode() {
        int result = idJournal != null ? idJournal.hashCode() : 0;
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        result = 31 * result + (dateJournal != null ? dateJournal.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (admin != null ? admin.hashCode() : 0);
        return result;
    }
}
