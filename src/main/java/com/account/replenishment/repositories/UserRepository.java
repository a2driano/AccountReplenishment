package com.account.replenishment.repositories;

import com.account.replenishment.model.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 29.06.2016
 */
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN 'true' ELSE 'false' END FROM User a WHERE a.email = :email")
    Boolean userExist(@Param("email") String email);

    @Query("SELECT a FROM User a WHERE a.email LIKE CONCAT('%',:email,'%') AND a.userRole LIKE 'USER'")
    List<User> getAllUserPlusPagination(@Param("email") String email, Pageable pageRequest);

    @Query("SELECT a FROM User a WHERE a.email= :email")
    User getUserByEmail(@Param("email") String email);

    @Query("SELECT count(a) FROM User a WHERE a.email LIKE CONCAT('%',:email,'%') AND a.userRole LIKE 'USER'")
    Long getCountOfUsersPlusPagination(@Param("email") String email);
}
