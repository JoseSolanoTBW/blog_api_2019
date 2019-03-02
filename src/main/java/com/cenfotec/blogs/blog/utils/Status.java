package com.cenfotec.blogs.blog.utils;

public enum Status {
    ACTIVE (1),
    INACTIVE (0);
    private final int status;

    private Status(int status) {
        this.status = status;
    }

    public int getValue() {
        return status;
    }
}
