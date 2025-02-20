package io.bitken.tts.service;

import io.bitken.tts.repo.stats.ListenStatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatsService {

    @Autowired
    ListenStatRepo statRepo;

    @Transactional
    public void logPaperListen(String session, long paperId, String src, int current, int total) {
        statRepo.insertStat(session, paperId, src, current, total);
    }

}
