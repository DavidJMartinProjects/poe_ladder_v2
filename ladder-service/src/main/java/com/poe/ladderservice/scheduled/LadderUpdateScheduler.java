package com.poe.ladderservice.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
public class LadderUpdateScheduler {

    @Autowired
    LadderUpdateService ladderUpdateService;

//    @Autowired
//    CacheManager cacheManager;

    @Scheduled(initialDelay = 10000, fixedRate = 300000)
    public void pollLeaderboards() throws InterruptedException {
        log.info("scheduler has initiated an update leaderboards task.");
        try {
            Runtime.getRuntime().gc();
            ladderUpdateService.fetchAndSaveLatestLeaderboards();
            log.info("success: task complete - leaderboards have been updated.");
        } finally {
//            sleepAndClearCache();
        }
    }

//    private void sleepAndClearCache() throws InterruptedException {
//        log.info("clearing cache and sleeping for garbage collection.");
//        clearCache("leaderboards");
//        clearCache("leaderboardsTop100");
//        java.lang.Thread.sleep(10000);
//        Runtime.getRuntime().gc();
//        log.info("garbage collection complete. leaderboard polling operation completed successfully.");
//    }

//    public void clearCache(String cacheName) {
//        cacheManager.getCache(cacheName).clear();
//    }

}