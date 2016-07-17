package com.account.replenishment.service;

import com.account.replenishment.model.StatusDTO;
import com.account.replenishment.model.web.JournalDTO;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 29.06.2016
 */
public interface JournalService {
    /**
     * Return a List of JournalDTO
     * with search options: time period
     *
     * @param bottomLimit
     * @param upperLimit
     * @param pageRequest
     * @return
     */
    List<JournalDTO> getJournalsFromCustomPeriod(Date bottomLimit, Date upperLimit, Pageable pageRequest);

    /**
     * Return count of Journal
     * whose include in a search time period
     *
     * @param bottomLimit
     * @param upperLimit
     * @return
     */
    Long getCountJournalsFromCustomPeriod(Date bottomLimit, Date upperLimit);

    /**
     * Return a JournalDTO by id
     *
     * @param id
     * @return
     */
    JournalDTO getJournalByid(Long id);

    /**
     * Add new Journal in DB
     *
     * @param journalDTO
     * @return
     */
    StatusDTO addJournal(JournalDTO journalDTO);
}
