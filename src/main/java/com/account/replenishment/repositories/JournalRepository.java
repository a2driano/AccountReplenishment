package com.account.replenishment.repositories;

import com.account.replenishment.model.entity.Journal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 29.06.2016
 */
@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {
    @Query("SELECT a FROM Journal a WHERE a.dateJournal>=:bottomLimit AND a.dateJournal<=:upperLimit")
    List<Journal> getJournalsFromCustomPeriod(@Param("bottomLimit") Date bottomLimit,
                                              @Param("upperLimit") Date upperLimit, Pageable pageRequest);

    @Query("SELECT count(a) FROM Journal a WHERE a.dateJournal>=:bottomLimit AND a.dateJournal<=:upperLimit")
    Long getCountJournalsFromCustomPeriod(@Param("bottomLimit") Date bottomLimit,
                                          @Param("upperLimit") Date upperLimit);
}
