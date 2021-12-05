package com.example.studyapp;

import java.util.Date;

public class event {
    private int Id;
    private String title;
    private String desc;
    private Date start;
    private Date end;
    private int type;
    public event(int _id,String tit, String des, Date st, Date en, int ty){
        this.Id = _id;
        this.title = tit;
        this.desc = des;
        this.start = st;
        this.end = en;
        this.type = ty;
    }
    public int getID(){return this.Id;}
    public String getTitle(){
        return title;
    }
    public void setTitle(String tit){
        this.title=tit;
    }
    public String getDesc(){
        return desc;
    }
    public void setDesc(String des){
        this.desc = des;
    }
    public Date getStart(){
        return start;
    }
    public Date getEnd(){
        return end;
    }
    public void setStart(Date st){
        this.start = st;
    }
    public void setEnd(Date en){
        this.end = en;
    }
    public int getType(){
        return type;
    }
    public void setType(int ty){
        this.type = ty;
    }

}
