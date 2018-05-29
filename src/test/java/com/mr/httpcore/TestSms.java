package com.mr.httpcore;

import com.mr.util.HttpClientUtil;
import org.junit.Test;

/**
 * Created by ydd on 2018/5/9.
 */
public class TestSms {

    @Test
    public void test1(){
        String mesg = HttpClientUtil.sendMesg("15203458830");
        System.out.println(mesg);
    }
}
