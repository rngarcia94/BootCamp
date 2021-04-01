package com.company;

import java.util.Date;

public class Time {

    private Date start;
    private Date stop;

    public void start(){
        this.start = new Date();
    }

    public void stop(){
        this.stop = new Date();
    }

    public long elapsedTime(){
        return this.stop.getTime() - this.start.getTime();
    }

}
