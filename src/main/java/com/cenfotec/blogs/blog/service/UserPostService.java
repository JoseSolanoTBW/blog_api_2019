package com.cenfotec.blogs.blog.service;

import com.cenfotec.blogs.blog.domain.Action;
import com.cenfotec.blogs.blog.domain.Preferences;
import com.cenfotec.blogs.blog.domain.User;
import com.cenfotec.blogs.blog.domain.UserPost;
import com.cenfotec.blogs.blog.repository.UserPostRepository;
import com.cenfotec.blogs.blog.utils.ActionType;
import com.cenfotec.blogs.blog.utils.PostSearchParams;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserPostService {

    private UserPostRepository userPostRepository;
    private ActionService actionService;
    private PreferencesService preferencesService;
    private UserService userService;

    public UserPostService(UserPostRepository userPostRepository, ActionService actionService, PreferencesService preferencesService, UserService userService) {
        this.userService = userService;
        this.preferencesService = preferencesService;
        this.actionService = actionService;
        this.userPostRepository = userPostRepository;
    }

    public UserPost save(UserPost userPost) throws NotFoundException {

        Set<Preferences> userPreferences = new HashSet<>();
        Set<Action> actions = new HashSet<>();
        User owner = userService.findById(userPost.getOwner().getId());
        userPost.setDate(LocalDate.now());

        if (!userPost.getPreferences().isEmpty()){
            userPost.getPreferences().forEach(
                    p -> {
                        p.setName(p.getName().toLowerCase());

                        if(p.getId() != null)
                            userPreferences.add(preferencesService.findById(p));
                        else{
                            userPreferences.add(p);
                        }
                    }
            );
        }

        userPost.setPreferences(userPreferences);

        if (!userPost.getActions().isEmpty()){
            userPost.getActions().forEach(
                    p -> {
                        if(p.getId() != null)
                            actions.add(actionService.findById(p.getId()));
                        else{
                            actions.add(p);
                        }
                    }
            );
        }

        userPost.setActions(actions);
        userPost.setOwner(owner);

        return userPostRepository.save(userPost);
    }

    @Transactional(readOnly = true)
    public List<UserPost> getPosts(PostSearchParams searchParams){
        List<UserPost> result = new ArrayList<UserPost>();

        if(searchParams.getPreferences() != null  && !searchParams.getPreferences().isEmpty()){
            Optional<List<UserPost>> filter = userPostRepository.findDistinctByPreferencesIsInOrderByDateDesc(searchParams.getPreferences());
            if (filter.isPresent())
                result = filter.get();
        }

        if (searchParams.isLiked() && searchParams.getUserId() != null){
            List<Long> ids = new ArrayList<>();
            List<Action> actions = actionService.getActionsByTypeAndUser(ActionType.Like.getValue(),searchParams.getUserId());
            ids = actions.stream().map(Action::getPost).collect(Collectors.toList());

            Optional<List<UserPost>> filter = userPostRepository.findByIdIsInOrderByDateDesc(ids);
                if (filter.isPresent())
                    result = filter.get();
        }

        //Owner posts
        if (searchParams.getUserId() != null && !searchParams.isLiked()){
            Optional<List<UserPost>> filter = userPostRepository.findByOwner_IdOrderByDateDesc(searchParams.getUserId());
            if (filter.isPresent())
                result = filter.get();
        }

        if(searchParams.getPostId() != null){
            Optional<UserPost> filter = userPostRepository.findById(searchParams.getPostId());
            if (filter.isPresent())
                result.add(filter.get());
        }

        result.forEach(p -> calculateLikes(p));

        return result;
    }

    public UserPost save (Action action) throws NotFoundException {
        PostSearchParams searchParams = new PostSearchParams();
        searchParams.setPostId(action.getPost());

        UserPost userPost = getPosts(searchParams).stream().findFirst().get();

        Set<Action> actions = userPost.getActions();
        action.setOwnerAction(userService.findById(action.getOwnerAction().getId()));
        actionService.save(action);
        actions.add(action);
        userPost.setActions(actions);

        return calculateLikes(userPostRepository.save(userPost));
    }

    private UserPost calculateLikes(UserPost userPost){
        userPost.setLikeCount((int) userPost.getActions().stream().filter(x -> x.getActionType() == ActionType.Like.getValue()).count());
        return  userPost;
    }

}
