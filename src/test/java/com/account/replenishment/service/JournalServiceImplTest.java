package com.account.replenishment.service;

import com.account.replenishment.model.entity.Journal;
import com.account.replenishment.model.web.JournalDTO;
import com.account.replenishment.repositories.JournalRepository;
import com.account.replenishment.util.ConvertUtilJournal;
import org.junit.Test;

import com.account.replenishment.config.RootContextConfig;
import com.account.replenishment.model.StatusDTO;
import com.account.replenishment.model.entity.User;
import com.account.replenishment.repositories.UserRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by a2driano on 21.07.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootContextConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DbUnitConfiguration(databaseConnection = {"datasource"})
@DatabaseSetup("/testData.xml")
@WebAppConfiguration
public class JournalServiceImplTest {

    @Qualifier("journalRepository")
    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private UserRepository userRepository;

    private ConvertUtilJournal convertUtilJournal;

    @Before
    public void setUp() {
        JournalDTO journalDTO = new JournalDTO();
        journalDTO.setDateJournal(new Date());
        journalDTO.setIdJournal(1L);
        journalDTO.setSum(23.3);
        journalDTO.setUser(new User());

        Journal journal = new Journal();
        journal.setDateJournal(new Date());
        journal.setIdJournal(1L);
        journal.setSum(23.3);
        journal.setUser(new User());
    }

    @Test
    public void testGetJournalsFromCustomPeriod() throws Exception {
        Pageable pageRequest = new PageRequest(0, 10);
        Date dateStart = new Date(0L);
        Date dateEnd = new Date();
        List<Journal> journalList = journalRepository.getJournalsFromCustomPeriod(dateStart, dateEnd, pageRequest);
        assertThat(2).isEqualTo(journalList.size());
    }

    @Test
    public void testGetCountJournalsFromCustomPeriod() throws Exception {
        Date dateStart = new Date(0L);
        Date dateEnd = new Date();
        Long count = journalRepository.getCountJournalsFromCustomPeriod(dateStart, dateEnd);
        assertThat(2L).isEqualTo(count);
    }

    @Test
    public void testGetJournalByid() throws Exception {
        Long id = 4L;
        JournalDTO journalDTO = new JournalDTO();
        Journal journal = journalRepository.findOne(id);
        journalDTO.setIdJournal(journal.getIdJournal());
        assertEquals(id, journalDTO.getIdJournal());
    }

    @Test
    public void testAddJournal() throws Exception {
        StatusDTO statusDTO = new StatusDTO();
        Double payment = 100.00;
        Double balance;
        User user;
        User admin;
        String email = "admin@gmail.com";
        admin = userRepository.getUserByEmail(email);
        user = userRepository.findOne(1L);
        balance = user.getBalance() + payment;
        assertEquals(750.00, balance, 0);
    }
}