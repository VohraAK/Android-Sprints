package com.example.lonelytwitter;

import java.util.Date;

public class AngryMood extends Mood
{
    public AngryMood(Date date)
    {
        super(date);
    }

    public AngryMood()
    {

    }

    @Override
    public String get_mood()
    {
        return "WHAT THE HELL?? WHAT THE HELLY!?!? 🤬🤬🤬🤬🤬🤬";
    }
}
