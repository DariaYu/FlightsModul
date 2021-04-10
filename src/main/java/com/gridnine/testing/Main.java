package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();
        System.out.println("Full flights list");
        flightList.stream().forEach(System.out::println);
        System.out.println();

        List<Filter> filtersList = Arrays.asList(
                new ExcFlBeforeDateTime(LocalDateTime.now()),
                new ExcFlArriveBeforeDepart(),
                new ExcFlGroundTimeMoreHours(2));

        FilterService filterService = new FilterService(flightList);
        for (Filter f : filtersList) {
            filterService.addFilter(f);
            filterService.printListResult();
            filterService.removeAllFilters();
            System.out.println();
        }
    }
}
