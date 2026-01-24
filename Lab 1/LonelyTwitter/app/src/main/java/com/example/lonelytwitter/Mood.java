package com.example.lonelytwitter;

import java.util.Date;

public abstract class Mood
{
    private Date date;

    public Mood(Date date)
    {
        this.date = date;
    }

    public Mood()
    {
        this.date = new Date();
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public abstract String get_mood();

}
