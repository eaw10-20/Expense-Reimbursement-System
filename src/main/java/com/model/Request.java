package com.model;

public class Request {
    private double amount;
    private String description;
    private int author;
    private int type;

    //constructors

    public Request() {
    }

    public Request(double amount, String description, int author, int type) {
        this.amount = amount;
        this.description = description;
        this.author = author;
        this.type = type;
    }

    public Request(double amount, String description, int type) {
        this.amount = amount;
        this.description = description;
        this.type = type;
    }

    //getters and setters


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Request{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", type=" + type +
                '}';
    }
}
