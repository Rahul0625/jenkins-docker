package com.example.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name should only have alphabets and spaces")
    private String name;

    @NotBlank(message = "Author name is required")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Author name should only have alphabets and spaces")
    @Column(name = "authorname")
    private String author;

    @Column(name = "price")
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Integer price;

    @NotBlank(message = "Genre is required")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Genre should only have alphabets and spaces")
    @Column(name = "genre")
    private String genre;

    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "Invalid gmail")
    @NotBlank(message = "Email is required")
    @Column(name = "Authormail")
    private String authormail;

    @Column(name = "PublishedYear")
    @NotNull(message = "Year is required")
    @Positive(message = "Year must be greater than zero")
    private Integer publishedyear;


    @Column(name = "AvailableCount")
    private Integer availablecount;

    @Column(name = "isExist")
    @JsonIgnore
    private int isExist=1;


    public Item() {
    }

    public Item(Long id, String name, String author, int price, String genre, String authormail, int publishedyear, int availablecount, int isExist) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.genre = genre;
        this.authormail = authormail;
        this.publishedyear = publishedyear;
        this.availablecount = availablecount;
        this.isExist = isExist;
    }

    public int getIsExist() {
        return isExist;
    }

    public void setIsExist(int isExist) {
        this.isExist = isExist;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthormail() {
        return authormail;
    }

    public void setAuthormail(String authormail) {
        this.authormail = authormail;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPublishedyear() {
        return publishedyear;
    }

    public void setPublishedyear(Integer publishedyear) {
        this.publishedyear = publishedyear;
    }

    public Integer getAvailablecount() {
        return availablecount;
    }

    public void setAvailablecount(Integer availablecount) {
        this.availablecount = availablecount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", genre='" + genre + '\'' +
                ", authormail='" + authormail + '\'' +
                ", publishedyear=" + publishedyear +
                ", availablecount=" + availablecount +
                ", isExist=" + isExist +
                '}';
    }
}
