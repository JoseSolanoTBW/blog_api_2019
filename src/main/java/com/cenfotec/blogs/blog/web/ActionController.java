package com.cenfotec.blogs.blog.web;

import com.cenfotec.blogs.blog.domain.Action;
import com.cenfotec.blogs.blog.domain.UserPost;
import com.cenfotec.blogs.blog.service.ActionService;
import com.cenfotec.blogs.blog.service.UserPostService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/action")
public class ActionController {

    private UserPostService userPostService;
    private ActionService actionService;

    public ActionController(UserPostService userPostService, ActionService actionService) {
        this.userPostService = userPostService;
        this.actionService = actionService;
    }

    @PostMapping("/create")
    public UserPost createAction(@RequestBody Action action) throws NotFoundException {
        return userPostService.save(action);
    }

    @PutMapping("/update")
    public Action updateAction(@RequestBody Action action) throws NotFoundException {
        return actionService.update(action);
    }
}
