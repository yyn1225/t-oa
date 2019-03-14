package com.jtech.toa.service;

import com.google.common.collect.Lists;
import com.jtech.toa.model.vo.MailVo;
import com.xiaoleilu.hutool.date.DateTime;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MailService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 13, 2018</pre>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: sendSimpleMail(String from, String to, String subject, String text)
     */
    @Test
    public void testSendSimpleMail() throws Exception {
        mailService.sendSimpleMail("xujiuhuamoney@163.com", "601385484@qq.com", "subject", "text");
    }

    @Test
    public void sendHtmlMailUsingFreeMarker() throws Exception {
        Map<String, Object> model = new HashMap<>(3);

        List<MailVo> list = Lists.newArrayList();
        MailVo mailVo1 = new MailVo();
        mailVo1.setFileName("test1");
        mailVo1.setFileUrl("https://test1.com");
        list.add(mailVo1);
        model.put("list", list);

        mailService.sendHtmlMailUsingFreeMarker("xujiuhuamoney@163.com", "601385484@qq.com", "subject", model);
    }

    public static void testDateTimeCalc() {
        long now = System.currentTimeMillis();
        long expired = now + 10*60*1000;
        System.out.println(new DateTime(now));
        System.out.println(new DateTime(expired));
    }

    public static void main(String[] args) {
        testDateTimeCalc();
    }
}
