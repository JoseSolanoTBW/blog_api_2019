package com.cenfotec.blogs.blog.repository;

import com.cenfotec.blogs.blog.domain.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserPostRepository extends JpaRepository<UserPost, Long> {

    Optional<List<UserPost>> findUserPostByPreferencesIsInOrderByDateDesc(List<String> preferences);

    Optional<List<UserPost>> findByOwner_IdOrderByDateDesc(Long ownerId);

    Optional<List<UserPost>> findByIdIsInOrderByDateDesc (List<Long> ids);
}
