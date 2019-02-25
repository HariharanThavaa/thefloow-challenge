package com.thefloow.challenge.functionalTest;

import com.thefloow.challenge.Main;
import com.thefloow.challenge.repo.Repository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static com.thefloow.challenge.helper.TestConstantsUtil.addLineBreak;
import static com.thefloow.challenge.helper.TestConstantsUtil.SOURCE_PARAM;
import static com.thefloow.challenge.helper.TestConstantsUtil.FIXTURES_FILE_AB_PATH;
import static com.thefloow.challenge.Main.VALID_INPUT_FORMAT;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeITest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private Repository repository;

    //java –Xmx8192m -jar challenge.jar –source dump.xml

    @Test
    public void givenNoArg_whenCalculateRate_thePrintInvalidInputMessage() {
        runTest();
        assertEquals(addLineBreak(VALID_INPUT_FORMAT), systemOutRule.getLog());
    }

    @Test
    public void givenNoOfferFound_whenCalculateRate_thePrintNoOfferMessage() {
        runTest(SOURCE_PARAM, FIXTURES_FILE_AB_PATH);
        assertTrue(systemOutRule.getLog().contains(repository.findByFileName(FIXTURES_FILE_AB_PATH).toString()));
    }

    public void runTest(String... args) {
        try {
            CommandLineRunner runner = ctx.getBean(Main.class);
            runner.run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
