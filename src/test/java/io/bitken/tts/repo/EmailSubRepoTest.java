package io.bitken.tts.repo;

import io.bitken.tts.config.ConfigForTests;
import io.bitken.tts.model.entity.EmailSubscriber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = ConfigForTests.class)
public class EmailSubRepoTest {

    @Autowired
    EmailSubRepo emailSubRepo;

    @Test
    public void testEmailIsSaved() {
        emailSubRepo.save(new EmailSubscriber().setEmailId("test@gmail.com"));
        List<EmailSubscriber> all = emailSubRepo.findAll();

        Assertions.assertEquals(1, all.size());
        Assertions.assertEquals("test@gmail.com", all.get(0).getEmailId());
    }
}
