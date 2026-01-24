package com.example.lonelytwitter;

import java.util.Date;

public class DisgustedMood extends Mood
{
    public DisgustedMood(Date date)
    {
        super(date);
    }

    public DisgustedMood()
    {

    }

    @Override
    public String get_mood()
    {
        return "You're soOoOoOoO disgusting 🤮🤮🤮🤮🤮🤮🤮";
    }
}