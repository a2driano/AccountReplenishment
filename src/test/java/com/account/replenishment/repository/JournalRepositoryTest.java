package com.account.replenishment.repository;

import com.account.replenishment.config.RootContextConfig;
import com.account.replenishment.model.entity.Journal;
import com.account.replenishment.repositories.JournalRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.Test;
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

import static org.assertj.core.api.Assertions.*;

/**
 * Created by a2driano on 14.08.2016.
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
public class JournalRepositoryTest {

    @Qualifier("journalRepository")
    @Autowired
    private JournalRepository journalRepository;

    @Test
    public void getJournalsFromCustomPeriod() {
        Pageable pageRequest = new PageRequest(0, 10);
        Date dateStart = new Date(0L);
        Date dateEnd = new Date();
        List<Journal> journalList = journalRepository.getJournalsFromCustomPeriod(dateStart, dateEnd, pageRequest);
        assertThat(2).isEqualTo(journalList.size());
    }

    @Test
    public void getCountJournalsFromCustomPeriod() {
        Date dateStart = new Date(0L);
        Date dateEnd = new Date();
        Long count = journalRepository.getCountJournalsFromCustomPeriod(dateStart, dateEnd);
        assertThat(2L).isEqualTo(count);
    }
}
