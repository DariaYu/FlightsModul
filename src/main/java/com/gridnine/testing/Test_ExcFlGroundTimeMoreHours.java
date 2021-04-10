package com.gridnine.testing;

import com.gridnine.testing.ExcFlGroundTimeMoreHours;
import com.gridnine.testing.Flight;
import com.gridnine.testing.TestService;
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
public class Test_ExcFlGroundTimeMoreHours {
    private ExcFlGroundTimeMoreHours exc;
    private List<Flight> listIn;
    private List<Flight> listOut;

    public Test_ExcFlGroundTimeMoreHours(List<Flight> listIn, List<Flight> listOut) {
        this.listIn = listIn;
        this.listOut = listOut;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        LocalDateTime now = LocalDateTime.now();

        List<Flight> in1 = Arrays.asList(
                TestService.createFlight(now, now.plusHours(2)),
                TestService.createFlight(now.minusHours(2), now.plusHours(2)),
                TestService.createFlight(now.minusDays(1), now.minusDays(1).plusHours(2), now.minusDays(1).plusHours(5), now.minusDays(1).plusHours(6)),
                TestService.createFlight(now, now.minusHours(2)));
        List<Flight> out1 = Arrays.asList(
                TestService.createFlight(now, now.plusHours(2)),
                TestService.createFlight(now.minusHours(2), now.plusHours(2)),
                TestService.createFlight(now, now.minusHours(2)));

        List<Flight> in2 = Arrays.asList(
                TestService.createFlight(now.plusDays(1), now.plusDays(1).plusHours(2)),
                TestService.createFlight(now, now.plusHours(8), now.plusHours(9), now.plusHours(10)),
                TestService.createFlight(now, now.plusHours(2), now.plusHours(3), now.plusHours(6), now.plusHours(8), now.plusHours(10)));
        List<Flight> out2 = Arrays.asList(
                TestService.createFlight(now.plusDays(1), now.plusDays(1).plusHours(2)),
                TestService.createFlight(now, now.plusHours(8), now.plusHours(9), now.plusHours(10)));

        List<Flight> in3 = Arrays.asList(
                TestService.createFlight(now, now.plusHours(8), now.plusHours(11), now.plusHours(13)),
                TestService.createFlight(now, now.plusHours(2), now.plusHours(3), now.plusHours(6), now.plusHours(8), now.plusHours(10)));
        List<Flight> out3 = Arrays.asList();

        List<Flight> in4 = Arrays.asList(
                TestService.createFlight(now.plusDays(1), now.plusDays(1).minusHours(1)),
                TestService.createFlight(now, now.plusHours(8), now.plusHours(10), now.plusHours(9)));
        List<Flight> out4 = Arrays.asList(
                TestService.createFlight(now.plusDays(1), now.plusDays(1).minusHours(1)),
                TestService.createFlight(now, now.plusHours(8), now.plusHours(10), now.plusHours(9)));

        List<Flight> in5 = Arrays.asList(
                TestService.createFlight(now, now.plusHours(2),now.plusHours(3), now.plusHours(5),now.plusHours(6), now.plusHours(7)),
                TestService.createFlight(now.minusDays(6), now.minusDays(6).minusHours(1)),
                TestService.createFlight(now, now.plusHours(2),now.plusHours(3), now.plusHours(5),now.plusHours(7), now.plusHours(8)));
        List<Flight> out5 = Arrays.asList(
                TestService.createFlight(now, now.plusHours(2),now.plusHours(3), now.plusHours(5),now.plusHours(6), now.plusHours(7)),
                TestService.createFlight(now.minusDays(6), now.minusDays(6).minusHours(1)));

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
        exc = new ExcFlGroundTimeMoreHours(2);
    }

    @Test
    public void testExcFlGroundTimeMoreHours() {
        Assert.assertArrayEquals(listOut.toArray(), (exc.filterFlights(listIn)).toArray());
    }
}
