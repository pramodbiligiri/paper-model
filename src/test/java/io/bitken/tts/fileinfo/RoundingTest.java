package io.bitken.tts.fileinfo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundingTest {

    @Test
    public void testRounding() {
        BigDecimal num1 = new BigDecimal("3.51").setScale(0, RoundingMode.HALF_UP);
        Assertions.assertEquals(4, num1.intValue());

        BigDecimal num2 = new BigDecimal("3.49").setScale(0, RoundingMode.HALF_UP);
        Assertions.assertEquals(3, num2.intValue());

        BigDecimal num3 = new BigDecimal("3.5").setScale(0, RoundingMode.HALF_UP);
        Assertions.assertEquals(4, num3.intValue());
    }
}
