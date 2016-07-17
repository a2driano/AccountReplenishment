package com.account.replenishment.util;

import com.account.replenishment.model.entity.Journal;
import com.account.replenishment.model.web.JournalDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 29.06.2016
 */
@Component
public class ConvertUtilJournal {
    public Journal convertJournalDTOToJournal(JournalDTO journalDTO) {
        Journal journal = new Journal();
        journal.setIdJournal(journalDTO.getIdJournal());
        journal.setSum(journalDTO.getSum());
        journal.setDateJournal(journalDTO.getDateJournal());
        journal.setUser(journalDTO.getUser());
        journal.setAdmin(journalDTO.getAdmin());

        return journal;
    }

    public List<Journal> convertJournalDTOListToJournalList(List<JournalDTO> journalDTOList) {
        List<Journal> journalList = new ArrayList<>();
        for (JournalDTO journalDTO : journalDTOList) {
            journalList.add(convertJournalDTOToJournal(journalDTO));
        }
        return journalList;
    }

    public JournalDTO convertJournalToJournalDTO(Journal journal) {
        JournalDTO journalDTO = new JournalDTO();
        journalDTO.setIdJournal(journal.getIdJournal());
        journalDTO.setSum(journal.getSum());
        journalDTO.setDateJournal(journal.getDateJournal());
        journalDTO.setUser(journal.getUser());
        journalDTO.setAdmin(journal.getAdmin());

        return journalDTO;
    }

    public List<JournalDTO> convertJournalListToJournalDTOlList(List<Journal> journalList) {
        List<JournalDTO> journalDTOList = new ArrayList<>();
        for (Journal journal : journalList) {
            journalDTOList.add(convertJournalToJournalDTO(journal));
        }
        return journalDTOList;
    }
}
