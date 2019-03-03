package com.cenfotec.blogs.blog.utils;

public enum ActionType {
    Dislike(0),
    Like (1),
    Comment (2);

    private final int actionType;

    private ActionType(int type) {
        this.actionType = type;
    }

    public int getValue() {
        return actionType;
    }
}
