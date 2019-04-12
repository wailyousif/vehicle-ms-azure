package com.alten.challenge.config;

import ch.qos.logback.classic.Logger;
import com.alten.challenge.helper.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.invoke.MethodHandles;

/**
 * Created by wailm.yousif on 4/8/19.
 */

@Component
public class Initialization {

    private static final Logger logger =
            (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${ping.interval}")
    private int pingInterval;

    @Value("${ping.request-grace-period}")
    private int pingRequestGracePeriod;


    @PostConstruct
    public void init() {
        Constants.maxPingGracePeriod = pingInterval + pingRequestGracePeriod;
        logger.info("Constants.maxPingGracePeriod = " + Constants.maxPingGracePeriod);
    }
}
