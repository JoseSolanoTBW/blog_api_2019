package com.cenfotec.blogs.blog.service;

import com.cenfotec.blogs.blog.domain.Action;
import com.cenfotec.blogs.blog.domain.UserPost;
import com.cenfotec.blogs.blog.repository.ActionRepository;
import com.cenfotec.blogs.blog.repository.UserPostRepository;
import com.cenfotec.blogs.blog.utils.PostSearchParams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ActionService {

    private ActionRepository actionRepository;

    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public List<Action> getActionsByTypeAndUser(int actionType, Long userId){
        List<Action> result = new ArrayList<Action>();

        Optional<List<Action>> filter = actionRepository.findByActionTypeEqualsAndOwnerAction_Id(actionType, userId);

        if(filter.isPresent())
            result = filter.get();

        return result;
    }

    public List<Action> getActionsByPost(Long postId){
        List<Action> result = new ArrayList<Action>();

        Optional<List<Action>> filter = actionRepository.findByPost(postId);

        if(filter.isPresent())
            result = filter.get();

        return result;
    }

    public Action findById(Long id){
        Action result = new Action();

        Optional<Action> filter = actionRepository.findById(id);

        if(filter.isPresent())
            result = filter.get();

        return result;
    }

    public Action save(Action action){
        return actionRepository.save(action);
    }
}
