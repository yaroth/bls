package ch.yaro.bls.rest.pojos;

import org.junit.Assert;
import org.junit.Test;

public class TrainServiceRequestTest {

    @Test
    public void isValid() {
        String correctTime = "08:30";
        String incorrectTime = "25:59";
        TrainServiceRequest tsr = new TrainServiceRequest();
        tsr.setTime(correctTime);
        tsr.setFrom("Bern");
        tsr.setTo("Thun");
        Assert.assertEquals(true, tsr.isValid());
        tsr.setTime(incorrectTime);
        Assert.assertNotEquals(true, tsr.isValid());
        tsr.setFrom(null);
        Assert.assertNotEquals(true, tsr.isValid());
    }
}