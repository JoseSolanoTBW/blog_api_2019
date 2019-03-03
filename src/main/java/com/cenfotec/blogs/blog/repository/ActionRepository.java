package com.cenfotec.blogs.blog.repository;

import com.cenfotec.blogs.blog.domain.Action;
import com.cenfotec.blogs.blog.domain.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActionRepository extends JpaRepository<Action, Long> {

    Optional<List<Action>> findByActionTypeAndOwnerAction_Id(int actionType , Long ownerId);

    Optional<List<Action>> findByPost(Long postId);
}
