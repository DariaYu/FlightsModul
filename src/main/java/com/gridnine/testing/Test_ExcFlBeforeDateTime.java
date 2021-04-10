package com.gridnine.testing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class Test_ExcFlBeforeDateTime {
    private ExcFlBeforeDateTime exc;
    private List<Flight> listIn;
    private List<Flight> listOut;
    private static LocalDateTime now = LocalDateTime.now();

    public Test_ExcFlBeforeDateTime(List<Flight> listIn, List<Flight> listOut) {
        this.listIn = listIn;
        this.listOut = listOut;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        List<Flight> in1 = Arrays.asList(
                TestService.createFlight(now, now.plusHours(2)),
                TestService.createFlight(now.minusHours(2), now.plusHours(2)),
                TestService.createFlight(now.minusDays(1), now.minusDays(1).plusHours(2), now.minusDays(1).plusHours(4), now.minusDays(1).plusHours(6)),
                TestService.createFlight(now, now.minusHours(2)));
        List<Flight> out1 = Arrays.asList(
                TestService.createFlight(now, now.plusHours(2)),
                TestService.createFlight(now, now.minusHours(2)));

        List<Flight> in2 = Arrays.asList(
                TestService.createFlight(now.plusDays(1), now.plusDays(1).plusHours(2)),
                TestService.createFlight(now, now.plusHours(8), now.plusHours(12), now.plusHours(9)),
                TestService.createFlight(now.minusDays(1), now.minusDays(1).plusHours(2), now.minusDays(1).plusHours(4), now.minusDays(1).plusHours(6)));
        List<Flight> out2 = Arrays.asList(
                TestService.createFlight(now.plusDays(1), now.plusDays(1).plusHours(2)),
                TestService.createFlight(now, now.plusHours(8), now.plusHours(12), now.plusHours(9)));

        List<Flight> in3 = Arrays.asList(
                TestService.createFlight(now.minusDays(1), now.minusDays(1).plusHours(2)),
                TestService.createFlight(now.minusHours(2), now.plusHours(2), now.plusHours(4), now.plusHours(5)));
        List<Flight> out3 = Arrays.asList();

        List<Flight> in4 = Arrays.asList(
                TestService.createFlight(now.plusDays(1), now.plusDays(1).minusHours(1)),
                TestService.createFlight(now, now.plusHours(8), now.plusHours(10), now.plusHours(9)));
        List<Flight> out4 = Arrays.asList(
                TestService.createFlight(now.plusDays(1), now.plusDays(1).minusHours(1)),
                TestService.createFlight(now, now.plusHours(8), now.plusHours(10), now.plusHours(9)));

        List<Flight> in5 = Arrays.asList(
                TestService.createFlight(now.plusDays(1), now.plusDays(1).plusHours(5)),
                TestService.createFlight(now.minusDays(6), now.minusDays(6).minusHours(1), now.minusDays(6).plusHours(2), now.minusDays(6).plusHours(6)),
                TestService.createFlight(now.minusDays(1), now.minusDays(1).plusHours(8)));
        List<Flight> out5 = Arrays.asList(
                TestService.createFlight(now.plusDays(1), now.plusDays(1).plusHours(5)));

        return Arrays.asList(new Object[][]{
                {in1, out1},
                {in2, out2},
                {in3, out3},
                {in4, out4},
                {in5, out5},
        });
    }

    @Before
    public void initTest() {
        exc = new ExcFlBeforeDateTime(now);
    }

    @Test
    public void testExcFlBeforeDateTime() {
        Assert.assertArrayEquals(listOut.toArray(), (exc.filterFlights(listIn)).toArray());
    }
}
