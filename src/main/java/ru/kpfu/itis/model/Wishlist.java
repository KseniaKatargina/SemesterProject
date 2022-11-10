package ru.kpfu.itis.model;

public class Wishlist {
    private long id;
    private long user_id;
    private String title;

    public Wishlist(long user_id, String title) {
        this.user_id = user_id;
        this.title = title;
    }

    public Wishlist(long id) {
        this.id = id;
    }

    public Wishlist(long id, long user_id, String title) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
