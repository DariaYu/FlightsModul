package com.gridnine.testing;

import java.util.*;
import java.util.stream.Collectors;

public class FilterService {
    private ArrayList<Filter> filterList;
    private List<Flight> flightList;
    private List<Flight> resultFlightList;

    public FilterService(List<Flight> flightList) {
        this.flightList = flightList;
        this.resultFlightList = flightList;
        this.filterList = new ArrayList<>();
    }

    public ArrayList<Filter> getFilterList() {
        return filterList;
    }

    public List<Flight> addFilter(Filter... filters) {
        List<Filter> f = Arrays.stream(filters).distinct().collect(Collectors.toList());
        if (filterList.addAll(f))
            applyFilters();
        return resultFlightList;
    }

    public List<Flight> removeFilters(Filter... filters) {
        if (filterList.removeAll(Arrays.asList(filters)))
            applyFilters();
        return resultFlightList;
    }

    public List<Flight> removeFilters(int index) {
        if (index < 0 || index >= filterList.size())
            throw new IndexOutOfBoundsException("index is out of bounds filterList");
        if (filterList.remove(index) != null)
            applyFilters();
        return resultFlightList;
    }

    public List<Flight> removeAllFilters() {
        filterList.clear();
        applyFilters();
        return resultFlightList;
    }

    private void applyFilters() {
        resultFlightList = flightList;
        if (!filterList.isEmpty()) {
            for (Filter filter : filterList) {
                resultFlightList = filter.filterFlights(resultFlightList);
                if (resultFlightList.isEmpty())
                    return;
            }
        }
    }

    public void printListResult() {
        if (!filterList.isEmpty())
            filterList.stream().forEach(f -> System.out.println(f.getDescription()));
        if (resultFlightList.isEmpty()) {
            System.out.println("EMPTY RESULT LIST");
            return;
        }
        resultFlightList.stream().map(Objects::toString).forEach(System.out::println);
    }
}
