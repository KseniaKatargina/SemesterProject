package ru.kpfu.itis.model;

public class User {
    private long id;
    private String username;
    private String email;
    private String password;
    private String birthday;
    private int years;


    public User(String username, String email, String password, String birthday, int years) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.years = years;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(long id, String username, String email, String password, String birthday, int years) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.years = years;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }
}
