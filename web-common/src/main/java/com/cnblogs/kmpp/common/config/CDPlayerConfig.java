package com.cnblogs.kmpp.common.config;

import com.cnblogs.kmpp.common.CompactDisc;
import com.cnblogs.kmpp.common.Impl.BlankDisc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan
public class CDPlayerConfig {
    @Bean
    public CompactDisc disc() {
        return new BlankDisc("Jay", "Jay", getTracks());
    }

    private List<String> getTracks() {
        List<String> tracks = new ArrayList<String>();
        tracks.add("A");
        tracks.add("B");
        tracks.add("C");
        return tracks;
    }
}
