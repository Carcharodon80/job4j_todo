package ru.job4j.todo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public final class AllTimeZones {
    private AllTimeZones() {
    }

    public static List<TimeZone> findAllTimezones() {
        List<TimeZone> zones = new ArrayList<>();
        for (String timeId : TimeZone.getAvailableIDs()) {
            zones.add(TimeZone.getTimeZone(timeId));
        }
        return zones;
    }
}
