package com.cenfotec.blogs.blog.repository;

import com.cenfotec.blogs.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNickName(String nickName);
}
