package com.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reimbursement {

    //stored variables
    //----------------
    private int id;
    private double amount;
    private LocalDateTime timeSubmitted;
    private LocalDateTime timeResolved;
    private String description;
    //private file location for receipt can go here
    private String author;
    private String resolver;
    private String status;
    private String type;            //reimbursement type

    //for formatting
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss MM-dd-yyyy");

    //constructors
    //------------
    public Reimbursement() {
    }


    //getters and setters
    //-------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTimeSubmitted() {
        return timeSubmitted.format(formatter);
    }

    public void setTimeSubmitted(LocalDateTime timeSubmitted) {
        this.timeSubmitted = timeSubmitted;
    }

    public String getTimeResolved() {
        if(timeResolved == null){
            return "unresolved";
        }
        return timeResolved.format(formatter);
    }

    public void setTimeResolved(LocalDateTime timeResolved) {
        this.timeResolved = timeResolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getResolver() {
        if(resolver == null){
            return "-";
        }
        return resolver;
    }

    public void setResolver(String resolver) {
        this.resolver = resolver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if(timeResolved == null){
            return "Reimbursement{" +
                    "id=" + id +
                    ", amount=" + amount +
                    ", timeSubmitted=" + timeSubmitted.toString() +
                    ", timeResolved='unresolved'"  +
                    ", description='" + description + '\'' +
                    ", author='" + author + '\'' +
                    ", resolver='" + resolver + '\'' +
                    ", status='" + status + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
        return "Reimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", timeSubmitted=" + timeSubmitted.toString() +
                ", timeResolved=" + timeResolved.toString() +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", resolver='" + resolver + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
