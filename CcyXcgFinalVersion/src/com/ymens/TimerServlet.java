package com.ymens;

import dao.TimerDao;

import javax.servlet.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class TimerServlet implements ServletContextListener {

    public void contextInitialized(ServletContextEvent e) {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormatter .parse("2017-09-19 11:35:10");
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        // int seconds = (int) (milliseconds / 1000) % 60 ;
        // int hours   = (int) ((milliseconds / (1000*60*60)) % 24);
        //Now create the time and schedule it
        Timer timer = new Timer();
        //Use this if you want to execute it once
        timer.schedule(new TimerDao(), date);
        //int period = 86400000;//24hours
        //timer.schedule(new TimerDao.MyTimeTask() , date);
    }

    public void contextDestroyed(ServletContextEvent e) {

    }
}