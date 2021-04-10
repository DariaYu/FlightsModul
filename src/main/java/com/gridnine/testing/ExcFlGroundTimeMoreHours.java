package com.gridnine.testing;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExcFlGroundTimeMoreHours extends Filter {
    private long hours;

    public ExcFlGroundTimeMoreHours(long hours) {
        super("Exclude flights with more than " + hours + " hours ground time");
        this.hours = hours;
    }

    @Override
    public List<Flight> filterFlights(List<Flight> flightList) {
        List<Flight> listResult = new ArrayList<>();
        for (Flight f : flightList) {
            List<Segment> segments = f.getSegments();
            long t = 0;
            if (segments.size() > 1) {
                for (int i = 0; i < segments.size() - 1; i++)
                    t += Duration.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate()).toHours();
            }
            if (t <= hours) listResult.add(f);
        }
        return listResult;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        return hours == ((ExcFlGroundTimeMoreHours) obj).hours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours);
    }
}
