package com.cnblogs.kmpp.common.Impl;

import com.cnblogs.kmpp.common.CompactDisc;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlankDisc implements CompactDisc {

    private String title;
    private String artist;
    private List<String> tracks;
    private String listener;
    private static Logger log = Logger.getLogger(BlankDisc.class);

    public BlankDisc(String title, String artist, List<String> tracks) {
        this.title = title;
        this.artist = artist;
        this.tracks = tracks;
    }

    public void setListener(String listener) {
        this.listener = listener;
    }

    public String getListener() {
        return this.listener;
    }

    public void play() {
        log.info("listener is " + listener + " playing " + title + " by " + artist);
        System.out.println("listener is " + listener + " playing " + title + " by " + artist);
        for (String track : tracks) {
            log.info("track ->" + track);
            System.out.println("-Track:" + track);
        }
    }
}
