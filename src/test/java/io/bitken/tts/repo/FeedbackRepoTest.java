package io.bitken.tts.repo;

import io.bitken.tts.config.ConfigForTests;
import io.bitken.tts.model.entity.Feedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest(classes = ConfigForTests.class)
public class FeedbackRepoTest {

    @Autowired
    FeedbackRepo feedbackRepo;

    @Test
    public void testEmailIsSaved() {
        feedbackRepo.save(new Feedback().setData("This is some feedback"));
        List<Feedback> all = feedbackRepo.findAll();

        Assertions.assertEquals(1, all.size());
        Assertions.assertEquals("This is some feedback", all.get(0).getData());
    }
}
