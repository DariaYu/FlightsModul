package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExcFlBeforeDateTime extends Filter {
    private LocalDateTime dateTime;

    public ExcFlBeforeDateTime(LocalDateTime dateTime) {
        super("Exclude flights with departure before " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
        this.dateTime = dateTime;
    }

    @Override
    public List<Flight> filterFlights(List<Flight> flightList) {
        return flightList.stream()
                .filter(f -> !(f.getSegments().get(0).getDepartureDate().isBefore(dateTime)))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        return dateTime.equals(((ExcFlBeforeDateTime) obj).dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime);
    }
}
