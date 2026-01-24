package com.example.lonelytwitter;

import java.util.Date;

public abstract class Tweet implements Tweetable
{
    // attributes
    private Date date;
    private String message;

    // constructors
    public Tweet(String message)
    {
        this.message = message;
        this.date = new Date();
    }

    public Tweet(Date date, String message)
    {
        this.date = date;
        this.message = message;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    // abstract method
    public abstract Boolean isImportant();


}


