package ru.kpfu.itis.model;

public class Product {

    private long id;
    private String img;
    private String text;

    public Product() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getText() {
        return text;
    }

    public Product(int id, String img, String text) {
        this.id = id;
        this.img = img;
        this.text = text;
    }
}
