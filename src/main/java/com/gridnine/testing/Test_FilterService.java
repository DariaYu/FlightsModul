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
public class Test_FilterService {
    private FilterService service;
    private Filter[] listIn;
    private List<Flight> listOut;

    public Test_FilterService(Filter[] listIn, List<Flight> listOut) {
        this.listIn = listIn;
        this.listOut = listOut;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        LocalDateTime threeDaysFromNow = TestService.threeDaysFromNow;

        Filter[] in1 = {new ExcFlBeforeDateTime(TestService.now)};
        List<Flight> out1 = Arrays.asList(
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)),
                TestService.createFlight(threeDaysFromNow.minusHours(2), threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(4), threeDaysFromNow.plusHours(5)),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(7)));

        Filter[] in2 = {new ExcFlArriveBeforeDepart()};
        List<Flight> out2 =   Arrays.asList(
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                TestService.createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)),
                TestService.createFlight(threeDaysFromNow.minusDays(3).minusHours(1), threeDaysFromNow.minusDays(3).plusHours(2)),
                TestService.createFlight(threeDaysFromNow.minusHours(2), threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(4), threeDaysFromNow.plusHours(5)));

        Filter[] in3 ={new ExcFlGroundTimeMoreHours(2)};
        List<Flight> out3 = Arrays.asList(
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                TestService.createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
                TestService.createFlight(threeDaysFromNow.minusDays(3).minusHours(1), threeDaysFromNow.minusDays(3).plusHours(2)),
                TestService.createFlight(threeDaysFromNow.minusHours(2), threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(4), threeDaysFromNow.plusHours(5)),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(7)));

        Filter[] in4 ={new ExcFlBeforeDateTime(TestService.now),new ExcFlGroundTimeMoreHours(2),new ExcFlArriveBeforeDepart()};
        List<Flight> out4 = Arrays.asList(
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                TestService.createFlight(threeDaysFromNow.minusHours(2), threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(4), threeDaysFromNow.plusHours(5)));

        Filter[] in5 ={new ExcFlBeforeDateTime(TestService.now),new ExcFlGroundTimeMoreHours(0)};
        List<Flight> out5 = Arrays.asList(
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)));

        Filter[] in6 ={};
        List<Flight> out6 = Arrays.asList(
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                TestService.createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)),
                TestService.createFlight(threeDaysFromNow.minusDays(3).minusHours(1), threeDaysFromNow.minusDays(3).plusHours(2)),
                TestService.createFlight(threeDaysFromNow.minusHours(2), threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(4), threeDaysFromNow.plusHours(5)),
                TestService.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(7)));

        Filter[] in7 ={new ExcFlBeforeDateTime(TestService.now),new ExcFlGroundTimeMoreHours(0),
                new ExcFlArriveBeforeDepart(),new ExcFlBeforeDateTime(TestService.threeDaysFromNow.plusHours(1))};
        List<Flight> out7 = Arrays.asList();

        return Arrays.asList(new Object[][]{
                {in1, out1},
                {in2, out2},
                {in3, out3},
                {in4, out4},
                {in5, out5},
                {in6, out6},
                {in7, out7}
        });
    }

    @Before
    public void initTest() {
        service = new FilterService(TestService.createFlights());
    }

    @Test
    public void testFilterService() {
        Assert.assertArrayEquals(listOut.toArray(), (service.addFilter(listIn)).toArray());
    }
}
