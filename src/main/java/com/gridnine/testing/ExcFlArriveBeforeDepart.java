package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

public class ExcFlArriveBeforeDepart extends Filter {
    public ExcFlArriveBeforeDepart() {
        super("Exclude flights that has segments with arrival before departure");
    }

    @Override
    public List<Flight> filterFlights(List<Flight> flightList) {
        return flightList.stream()
                .filter(f -> f.getSegments().stream()
                        .noneMatch(s -> s.getArrivalDate().isBefore(s.getDepartureDate())))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
