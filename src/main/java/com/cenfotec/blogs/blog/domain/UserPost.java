package com.cenfotec.blogs.blog.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(columnDefinition="TEXT")
    private String postText;

    private String imageSrc;

    @Column(columnDefinition="TEXT")
    private String postTitle;

    private LocalDate date;

    @OneToOne(cascade = CascadeType.ALL)
    private User owner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "post_preferences",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "preference_id")
    )
    private Set<Preferences> preferences = new HashSet<>();

    @Transient
    private int LikeCount;

    @Transient
    private int CommentCount;

    @OneToMany(cascade = { CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    @JoinTable(name = "user_actions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "action_id")
    )
    private Set<Action> actions = new HashSet<>();

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public int getLikeCount() {
        return LikeCount;
    }

    public void setLikeCount(int likeCount) {
        LikeCount = likeCount;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }

    public Set<Preferences> getPreferences() {
        return preferences;
    }

    public void setPreferences(Set<Preferences> preferences) {
        this.preferences = preferences;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getCommentCount() {
        return CommentCount;
    }

    public void setCommentCount(int commentCount) {
        CommentCount = commentCount;
    }
}
