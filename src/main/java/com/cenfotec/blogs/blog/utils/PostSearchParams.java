package com.cenfotec.blogs.blog.utils;

import com.cenfotec.blogs.blog.domain.Preferences;

import java.util.List;

public class PostSearchParams {

    private List<Preferences> preferences;

    private Long userId;

    private boolean liked;

    private Long postId;

    public List<Preferences> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Preferences> preferences) {
        this.preferences = preferences;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
