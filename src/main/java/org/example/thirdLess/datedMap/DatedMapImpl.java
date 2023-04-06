package org.example.thirdLess.datedMap;

import java.util.*;

public class DatedMapImpl implements DatedMap{

    private final Map<String, Value> datedmap;

    public DatedMapImpl() {
        this.datedmap = new HashMap<>();
    }

    @Override
    public void put(String key, String value) {
        datedmap.put(key, new Value(value, new Date(System.currentTimeMillis())));
    }

    @Override
    public String get(String key) {
        Value value = datedmap.get(key);
        if (value!= null) {
            return value.getValue();
        }
        return null;
    }

    @Override
    public boolean containsKey(String key) {
        return datedmap.containsKey(key);
    }

    @Override
    public void remove(String key) {
       if (datedmap.containsKey(key)) {
           datedmap.remove(key, datedmap.get(key));
       }
    }

    @Override
    public Set<String> keySet() {
       return datedmap.keySet();
    }

    @Override
    public Date getKeyLastInsertionDate(String key) {
        Value value = datedmap.get(key);
        return value.getDate();
    }

    private static class Value {
        String value;
        Date date;

        public Value(String value, Date date) {
            this.value = value;
            this.date = date;
        }

        public String getValue() {
            return value;
        }

        public Date getDate() {
            return date;
        }

    }
}
