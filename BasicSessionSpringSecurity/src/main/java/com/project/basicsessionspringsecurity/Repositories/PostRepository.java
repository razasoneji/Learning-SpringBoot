package com.project.basicsessionspringsecurity.Repositories;

import com.project.basicsessionspringsecurity.Entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
