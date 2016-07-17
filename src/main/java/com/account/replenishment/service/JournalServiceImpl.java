package com.account.replenishment.service;

import com.account.replenishment.model.Status;
import com.account.replenishment.model.StatusDTO;
import com.account.replenishment.model.entity.Journal;
import com.account.replenishment.model.entity.User;
import com.account.replenishment.model.web.JournalDTO;
import com.account.replenishment.repositories.JournalRepository;
import com.account.replenishment.repositories.UserRepository;
import com.account.replenishment.util.ConvertUtilJournal;
import org.hibernate.HibernateException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 30.06.2016
 */
@Service
public class JournalServiceImpl implements JournalService {
    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private ConvertUtilJournal convertUtilJournal;
    @Autowired
    private UserRepository userRepository;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JournalServiceImpl.class);

    @Override
    public List<JournalDTO> getJournalsFromCustomPeriod(Date bottomLimit, Date upperLimit, Pageable pageRequest) {
        List<JournalDTO> journalDTOList = new ArrayList<>();
        try {
            journalDTOList = convertUtilJournal
                    .convertJournalListToJournalDTOlList(journalRepository
                            .getJournalsFromCustomPeriod(bottomLimit, upperLimit, pageRequest));
        } catch (HibernateException e) {
            LOGGER.error("{}", e.toString(), e);
        }
        return journalDTOList;
    }

    @Override
    public Long getCountJournalsFromCustomPeriod(Date bottomLimit, Date upperLimit) {
        Long count = 0L;
        try {
            count = journalRepository.getCountJournalsFromCustomPeriod(bottomLimit, upperLimit);
        } catch (HibernateException e) {
            LOGGER.error("{}", e.toString(), e);
        }
        return count;
    }

    @Override
    public JournalDTO getJournalByid(Long id) {
        JournalDTO journalDTO = new JournalDTO();
        try {
            journalDTO = convertUtilJournal.convertJournalToJournalDTO(journalRepository.findOne(id));
        } catch (HibernateException e) {
            LOGGER.error("{}", e.toString(), e);
        }
        return journalDTO;
    }

    @Transactional
    @Override
    public StatusDTO addJournal(JournalDTO journalDTO) {
        StatusDTO statusDTO = new StatusDTO();
        User user;
        User admin;
        //get administrator email from security context
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        try {
            admin = userRepository.getUserByEmail(email);
            user = userRepository.findOne(journalDTO.getUser().getIdUser());
            user.setBalance(user.getBalance() + journalDTO.getUser().getBalance());
            userRepository.save(user);

            journalDTO.setUser(user);
            journalDTO.setAdmin(admin);
            Journal journal = convertUtilJournal.convertJournalDTOToJournal(journalDTO);
            journalRepository.save(journal);
            statusDTO.setStatus(Status.SUCCESS);
        } catch (HibernateException e) {
            LOGGER.error("{}", e.toString(), e);
            statusDTO.setStatus(Status.FAIL);
        }
        return statusDTO;
    }
}
