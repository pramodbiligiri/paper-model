package io.bitken.tts.repo;

import io.bitken.tts.config.ConfigForTests;
import io.bitken.tts.model.entity.PaperAudio;
import io.bitken.tts.model.entity.PaperCategory;
import io.bitken.tts.model.entity.PaperData;
import io.bitken.tts.model.entity.converter.BlobStorageHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest (classes = ConfigForTests.class)
public class PaperDataRepoTest {

    private static final byte[] AUDIO_FILE_DUMMY_BYTES = {65, 66, 67, 68};

    @Autowired
    PaperDataRepo paperDataRepo;

    @Autowired
    PaperAudioRepo paperAudioRepo;

    @Autowired
    PaperCategoryRepo paperCategoryRepo;

    @Autowired
    BlobStorageHandler storageHandler;

    @AfterEach
    public void afterEach() {
        paperDataRepo.deleteAll();
        paperCategoryRepo.deleteAll();
    }

    @Test
    public void testFindLatestAIPapersWithAudio() throws IOException {
        initTestSync1();
        List<PaperData> papers = paperDataRepo.findLatestPapersWithAudioInCategory("cs.AI", 1, 0);
        Assertions.assertEquals(1, papers.size());
        PaperData p = papers.get(0);
        Assertions.assertEquals("Paper 2", p.getTitle());
    }

    @Test
    public void testExistenceOfArxivId() {
        Assertions.assertEquals(false, paperDataRepo.checkExists("abcdef"));

        PaperData pd = new PaperData();
        pd.setArxivId("abcdef");
        paperDataRepo.save(pd);

        Assertions.assertEquals(true, paperDataRepo.checkExists("abcdef"));
    }

    @Test
    public void testFindPapersWithoutAudioInCategories() {
        PaperData p1 = new PaperData();
        p1.setCategories(Arrays.asList(new PaperCategory().setCategory("cs.DC")));
        paperDataRepo.save(p1);

        PaperData p2 = new PaperData();
        p2.setCategories(Arrays.asList(new PaperCategory().setCategory("cs.OS")));
        paperDataRepo.save(p2);

        PaperData p3 = new PaperData();
        p3.setCategories(Arrays.asList(new PaperCategory().setCategory("cs.OS")));
        paperDataRepo.save(p3);

        PaperData p4 = new PaperData();
        p4.setCategories(Arrays.asList(new PaperCategory().setCategory("cs.AI")));
        paperDataRepo.save(p4);

        Assertions.assertEquals(3,
            paperDataRepo.findLatestPapersWithoutAudioInCategories(Arrays.asList("cs.DC", "cs.OS")).size()
        );

        Assertions.assertEquals(1,
            paperDataRepo.findLatestPapersWithoutAudioInCategories(Arrays.asList("cs.AI")).size()
        );
    }

    @Test
    public void testSearchForPapers() throws InterruptedException, IOException {
        PaperData p1 = new PaperData();
        p1.setTitle("America");
        p1.setPubDate(new Timestamp(System.currentTimeMillis()));
        p1 = paperDataRepo.save(p1);
        PaperAudio pa1 = new PaperAudio();
        pa1.setPaper(p1);
        pa1.setAudio(storageHandler.newFile(AUDIO_FILE_DUMMY_BYTES));
        paperAudioRepo.save(pa1);

        PaperData p2 = new PaperData();
        p2.setTitle("India");
        p2.setPubDate(new Timestamp(System.currentTimeMillis()));
        p2 = paperDataRepo.save(p2);
        PaperAudio pa2 = new PaperAudio();
        pa2.setPaper(p2);
        pa2.setAudio(storageHandler.newFile(AUDIO_FILE_DUMMY_BYTES));
        paperAudioRepo.save(pa2);

        PaperData p3 = new PaperData();
        p3.setTitle("England");
        p3.setPubDate(new Timestamp(System.currentTimeMillis()));
        p3 = paperDataRepo.save(p3);
        PaperAudio pa3 = new PaperAudio();
        pa3.setPaper(p3);
        pa3.setAudio(storageHandler.newFile(AUDIO_FILE_DUMMY_BYTES));
        paperAudioRepo.save(pa3);

        PaperData p4 = new PaperData();
        p4.setTitle("France");
        p4.setPubDate(new Timestamp(System.currentTimeMillis()));
        p4 = paperDataRepo.save(p4);
        PaperAudio pa4 = new PaperAudio();
        pa4.setPaper(p4);
        pa4.setAudio(storageHandler.newFile(AUDIO_FILE_DUMMY_BYTES));
        paperAudioRepo.save(pa4);

        int searchResult1 = paperDataRepo.searchPapers("America", 1, 0).size();
        int searchResult2 = paperDataRepo.searchPapers("Germany", 1, 0).size();
        Assertions.assertEquals(1, searchResult1);
        Assertions.assertEquals(0, searchResult2);

        BigInteger dbCount = (BigInteger) paperDataRepo.searchPapersCount("India").get(0)[0];
        Assertions.assertEquals(1, dbCount.intValue());
    }

    private void initTestSync1() throws IOException {
        PaperData pd1 = new PaperData();
        pd1.setTitle("Paper 1");
        pd1.setPubDate(new Timestamp(new Date().getTime()));
        pd1 = paperDataRepo.save(pd1);

        PaperAudio pa1 = new PaperAudio();
        pa1.setPaper(pd1);
        pa1.setAudio(storageHandler.newFile(AUDIO_FILE_DUMMY_BYTES));
        pa1 = paperAudioRepo.save(pa1);
        pd1.setAudio(pa1);

        PaperData pd2 = new PaperData();
        pd2.setTitle("Paper 2");
        pd2.setPubDate(new Timestamp(new Date().getTime()));
        pd2.addCategory(new PaperCategory().setCategory("cs.AI"));
        pd2 = paperDataRepo.save(pd2);

        PaperAudio pa2 = new PaperAudio();
        pa2.setPaper(pd2);
        pa2.setAudio(storageHandler.newFile(AUDIO_FILE_DUMMY_BYTES));
        pa2 = paperAudioRepo.save(pa2);
        pd2.setAudio(pa2);

        PaperData pd3 = new PaperData();
        pd3.setTitle("Paper 3");
        pd3 = paperDataRepo.save(pd3);

        PaperData pd4 = new PaperData();
        pd4.setTitle("Paper 4");
        pd4 = paperDataRepo.save(pd4);
    }

}
