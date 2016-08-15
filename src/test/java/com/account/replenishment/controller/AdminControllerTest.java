package com.account.replenishment.controller;

import com.account.replenishment.config.WebAppInitializer;
import com.account.replenishment.model.entity.User;
import com.account.replenishment.model.web.JournalDTO;
import com.account.replenishment.service.JournalService;
import com.account.replenishment.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import java.util.Date;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;


/**
 * Created by Admin on 24.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppInitializer.class})
@WebAppConfiguration
@ComponentScan
public class AdminControllerTest {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AdminControllerTest.class);

    @Mock
    private UserService userServiceMock;
    @Mock
    private JournalService journalServiceMock;
    @InjectMocks
    private AdminController adminController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }


    @Test
    public void testPrintWelcomeStart() throws Exception {
        mockMvc.perform(get("/admin")
        )
                .andExpect(status().is(302));
    }

    @Test
    public void testPrintWelcome() throws Exception {
        mockMvc.perform(get("/admin/")
        )
                .andExpect(status().is(302));
    }

    @Test
    public void testAddNewJournal() throws Exception {
        JournalDTO journalDTO = new JournalDTO();
        journalDTO.setDateJournal(new Date());
        journalDTO.setIdJournal(1L);
        journalDTO.setSum(23.3);
        journalDTO.setUser(new User());

        mockMvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON)
                        .body(IntegrationTestUtil.convertObjectToJsonBytes(journalDTO))
        )
                .andExpect(status().is(200));
    }

    @Test
    public void testPrintJournalWelcomeStart() throws Exception {
        mockMvc.perform(get("/admin_journal")
        )
                .andExpect(status().is(302));

    }

    @Test
    public void testPrintJournalWelcome() throws Exception {
        mockMvc.perform(get("/admin_journal/").contentType(MediaType.APPLICATION_JSON)
                        .param("numberPage", "1")
                        .param("dateStartStr", "1982-04-29 03:30:37")
                        .param("dateEndStr", "2016-01-31 11:42:37")
        )
                .andExpect(status().is(302));
    }
}