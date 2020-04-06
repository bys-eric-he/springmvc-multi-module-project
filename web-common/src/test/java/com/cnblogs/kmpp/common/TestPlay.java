package com.cnblogs.kmpp.common;

import com.cnblogs.kmpp.common.config.CDPlayerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class TestPlay {
    @Autowired
    private CompactDisc blankDisc;

    @Test
    public void play() {
        blankDisc.play();
    }
}

