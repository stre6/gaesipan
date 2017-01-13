package com.kaka.gaesipan;

/**
 * Created by stre6 on 2017-01-03.
 */

public class ListviewItem {
    private String number;
    private String title;
    private String nae;
    private String nick;

    public void setNumber(String num){
        number=num;
    }
    public void setTitle(String tit){
        title=tit;
    }
    public void setnae(String n){
        nae=n;
    }
    public void setNick(String nic){
        nick=nic;
    }
    public String getNumber(){
        return this.number;
    }
    public String getTitle(){
        return this.title;
    }
    public String getNae(){
        return this.nae;
    }
    public String getNick(){
        return this.nick;
    }
}
