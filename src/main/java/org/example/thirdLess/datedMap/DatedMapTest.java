package org.example.thirdLess.datedMap;

public class DatedMapTest{
    public static void main(String[] args){

        DatedMap datedMap = new DatedMapImpl();
        datedMap.put("a", "");
        datedMap.put("b", null);
        datedMap.put("c", "");
        datedMap.put("d", "fhdfh");
        datedMap.put("e", "sgdg");
        datedMap.put("f", null);
        System.out.println(datedMap.containsKey("a"));
        System.out.println(datedMap.containsKey("b"));
        for (String key: datedMap.keySet()) {
            System.out.println(datedMap.get(key));
        }
        datedMap.remove("f");
        System.out.println(datedMap.get("f"));
        for (int i = 0; i < 10000; i++) {
            datedMap.put(String.valueOf(i), String.valueOf(i+1));
            System.out.println(datedMap.getKeyLastInsertionDate(String.valueOf(i)));
        }
        System.out.println(datedMap.keySet());
    }
}
