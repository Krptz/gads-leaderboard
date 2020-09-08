package com.example.gadsleaderboard;


import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static junit.framework.TestCase.assertEquals;

public class ApiUtilTest {

    private ApiUtil apiUtil;

    @Before
    public void getApiUtilInstance() {
        apiUtil = ApiUtil.getInstance();
    }

    @Test
    public void testBuildUrl() {
        URL learningHoursUrl = apiUtil.buildUrl(ApiUtil.Endpoint.LEARNING_HOURS);
        URL skillIqUrl = apiUtil.buildUrl(ApiUtil.Endpoint.SKILL_IQ);
        System.out.println(learningHoursUrl.toString());
        System.out.println(skillIqUrl.toString());
        assertEquals("https://gadsapi.herokuapp.com/api/hours", learningHoursUrl.toString());
        assertEquals("https://gadsapi.herokuapp.com/api/skilliq", skillIqUrl.toString());
    }

    @Test
    public void getJson() {
        URL learningHoursUrl = apiUtil.buildUrl(ApiUtil.Endpoint.LEARNING_HOURS);
        URL skillIqUrl = apiUtil.buildUrl(ApiUtil.Endpoint.SKILL_IQ);
        try {
            System.out.println(apiUtil.getJson(learningHoursUrl));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try {
            System.out.println(apiUtil.getJson(skillIqUrl));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
