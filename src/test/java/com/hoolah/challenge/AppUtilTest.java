package com.hoolah.challenge;

import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;

public class AppUtilTest {

    @Test
    public void shouldGetInstant(){
        Instant actual = AppUtil.getInstant("20/08/2018 12:00:00");
        Assert.assertEquals(1534737600000l, actual.toEpochMilli());
    }


    @Test
    public void shouldGetInstantReturnNull(){
        Instant actual = AppUtil.getInstant("20-08-2018 12:00:00");
        Assert.assertNull(actual);
    }
}
