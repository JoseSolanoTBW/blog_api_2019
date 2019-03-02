package com.cenfotec.blogs.blog.web;

import com.cenfotec.blogs.blog.domain.Action;
import com.cenfotec.blogs.blog.domain.UserPost;
import com.cenfotec.blogs.blog.service.UserPostService;
import com.cenfotec.blogs.blog.utils.PostSearchParams;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class UserPostController {

    private UserPostService userPostService;

    public UserPostController(UserPostService userPostService) {
        this.userPostService = userPostService;
    }

    @CrossOrigin
    @PostMapping("/create")
    public UserPost postUser(@RequestBody UserPost userPost) throws NotFoundException {
        return userPostService.save(userPost);
    }

    @PostMapping("/get-posts")
    public List<UserPost> getPosts(@RequestBody PostSearchParams postParameters) throws NotFoundException {
        return userPostService.getPosts(postParameters);
    }

    @PostMapping("/action")
    public UserPost createAction(@RequestBody Action action) throws NotFoundException {
        return userPostService.save(action);
    }


}
