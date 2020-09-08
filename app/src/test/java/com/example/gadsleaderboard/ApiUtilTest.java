package com.example.gadsleaderboard;


import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.gadsleaderboard.ApiUtil.Endpoint.LEARNING_HOURS;
import static com.example.gadsleaderboard.ApiUtil.Endpoint.SKILL_IQ;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ApiUtilTest {

    private ApiUtil apiUtil;

    @Before
    public void getApiUtilInstance() {
        apiUtil = ApiUtil.getInstance();
    }

    @Test
    public void testBuildUrl() {
        URL learningHoursUrl = apiUtil.buildUrl(LEARNING_HOURS);
        URL skillIqUrl = apiUtil.buildUrl(SKILL_IQ);
        System.out.println(learningHoursUrl.toString());
        System.out.println(skillIqUrl.toString());
        assertEquals("https://gadsapi.herokuapp.com/api/hours", learningHoursUrl.toString());
        assertEquals("https://gadsapi.herokuapp.com/api/skilliq", skillIqUrl.toString());
    }

    @Test
    public void testGetJson() {
        URL learningHoursUrl = apiUtil.buildUrl(LEARNING_HOURS);
        URL skillIqUrl = apiUtil.buildUrl(SKILL_IQ);
        try {
            String learningHoursJsonString = apiUtil.getJson(learningHoursUrl);
            assertNotEquals("", learningHoursJsonString);
            System.out.println(learningHoursJsonString);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        try {
            String skillIqJsonString = apiUtil.getJson(skillIqUrl);
            assertNotEquals("", skillIqJsonString);
            System.out.println(skillIqJsonString);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void testGetLeadersFromJson() {
        ArrayList<Leader> learningHoursLeaders = apiUtil.getLeadersFromJson(LEARNING_HOURS);
        ArrayList<Leader> skillIqLeaders = apiUtil.getLeadersFromJson(SKILL_IQ);
        assertEquals(20, learningHoursLeaders.size());
        assertEquals(20, skillIqLeaders.size());
        for (Leader leader : learningHoursLeaders) {
            System.out.println(leader.getName() + " " + leader.getHours() + " " + leader.getCountry());
        }
        for (Leader leader : skillIqLeaders) {
            System.out.println(leader.getName() + " " + leader.getScore() + " " + leader.getCountry());
        }
    }
}


