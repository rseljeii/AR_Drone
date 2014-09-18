package com.codeminders.ardrone.commands;

import android.util.Log;

public class TakeOffCommand extends RefCommand
{
    public TakeOffCommand()
    {
        value |= (1<<9);
        Log.i("TakeOffCommand", "Value: " + value);
    }
    
    public boolean isSticky()
    {
        return true;
    }
    
    public String getCategory()
    {
        return LAND_TAKEOFF_CATEGORY;
    }
}
