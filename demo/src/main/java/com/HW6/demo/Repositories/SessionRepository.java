package com.HW6.demo.Repositories;


import com.HW6.demo.Entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query("SELECT COUNT(s) FROM Session s WHERE s.user.username = :userName")
    int  findNoOfSessionsByUserName(@Param("userName") String userName);

    Session findTopByOrderByLastUsedAtAsc();


    List<Session> findSessionByRefreshToken(String refreshToken);

    boolean existsByRefreshToken(String refreshToken);

    boolean existsSessionByRefreshToken(String refreshToken);

    void deleteByRefreshToken(String refreshToken);



    @Modifying
    @Query("DELETE FROM Session s WHERE s.refreshToken = :refreshToken")
    void removeSessionByRefreshToken(@Param("refreshToken") String refreshToken);


    @Query("SELECT s FROM Session s WHERE s.refreshToken = :refreshToken")
    Optional<Session> findByRefreshToken(@Param("refreshToken") String refreshToken);

}

