package com.HW6.demo.Repositories;


import com.HW6.demo.Entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query("SELECT COUNT(s) FROM Session s WHERE s.user.username = :userName")
    int  findNoOfSessionsByUserName(@Param("userName") String userName);

    Session findTopByOrderByLastUsedAtAsc();


}

