package com.cenfotec.blogs.blog.utils;

import java.time.LocalDate;
import java.util.List;

public class PostSearchParams {

    private List<String> Preferences;

    private Long UserId;

    private boolean Liked;

    private Long PostId;

    public Long getPostId() {
        return PostId;
    }

    public void setPostId(Long postId) {
        PostId = postId;
    }

    public List<String> getPreferences() {
        return Preferences;
    }

    public void setPreferences(List<String> preferences) {
        Preferences = preferences;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public boolean isLiked() {
        return Liked;
    }

    public void setLiked(boolean liked) {
        Liked = liked;
    }
}
