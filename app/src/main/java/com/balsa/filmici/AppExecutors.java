package com.balsa.filmici;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

    private static AppExecutors instance;

    //creating executor service
    private final ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

    //creating getter for our executor service
    public ScheduledExecutorService getService(){
        return service;
    }



    public static AppExecutors getInstance() {
        if(instance == null){
            instance = new AppExecutors();
        }
        return instance;
    }
}
