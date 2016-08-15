package com.account.replenishment.util;

import com.account.replenishment.model.entity.Journal;
import com.account.replenishment.model.web.JournalDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Admin on 15.08.2016.
 */
public class ConvertUtilJournalTest {
    private ConvertUtilJournal convertUtilJournal = new ConvertUtilJournal();
    private Journal journal;
    private JournalDTO journalDTO;

    @Before
    public void setUp() {
        journal = new Journal();
        journal.setSum(20.20);

        journalDTO = new JournalDTO();
        journalDTO.setSum(20.20);
    }

    @Test
    public void testConvertJournalDTOToJournal() throws Exception {
        assertEquals(journal.getSum(), convertUtilJournal.convertJournalDTOToJournal(journalDTO).getSum());
    }

    @Test
    public void testConvertJournalDTOListToJournalList() throws Exception {
        List<Journal> journalList = new ArrayList<>();
        journalList.add(journal);

        List<JournalDTO> journalDTOList = new ArrayList<>();
        journalDTOList.add(journalDTO);

        assertEquals(journalDTOList.get(0).getSum(), convertUtilJournal
                .convertJournalDTOListToJournalList(journalDTOList).get(0).getSum());
    }

    @Test
    public void testConvertJournalToJournalDTO() throws Exception {
        assertEquals(journalDTO.getSum(), convertUtilJournal.convertJournalToJournalDTO(journal).getSum());
    }

    @Test
    public void testConvertJournalListToJournalDTOlList() throws Exception {
        List<Journal> journalList = new ArrayList<>();
        journalList.add(journal);

        List<JournalDTO> journalDTOList = new ArrayList<>();
        journalDTOList.add(journalDTO);

        assertEquals(journalList.get(0).getSum(), convertUtilJournal
                .convertJournalListToJournalDTOlList(journalList).get(0).getSum());
    }
}