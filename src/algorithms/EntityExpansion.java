package algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityExpansion {

    static void entityExpansion(long l, String[] entities) {
        Map<String, Integer> count = new HashMap<>();
        Map<String, List<String>> enititesMap = new HashMap<>();

        for (String entity : entities) {

            String[] parsedValues = entity.split(" ", -1);
            boolean isInt = true;
            try {
                Integer.parseInt(parsedValues[parsedValues.length - 1].split(">", 0)[0]);
            } catch (Exception e) {
                isInt = false;
            }

            if (isInt) {
                count.put(parsedValues[1], new Integer(1));
            } else {
                String[] parsedEntities = parsedValues[parsedValues.length - 1].split(";", 0);
                parsedEntities[parsedEntities.length - 1] = parsedEntities[parsedEntities.length - 1].split(">", -1)[0];

                boolean allPresent = true;

                for (String parsedEntity : parsedEntities) {
                    if (!count.containsKey(parsedEntity)) {
                        allPresent = false;
                        break;
                    }
                }

                if (!allPresent) {
                    enititesMap.put(parsedValues[1], Arrays.asList(parsedEntities));
                } else {
                    int totalCount = 0;
                    for (String parsedEntity : parsedEntities) {
                        totalCount += count.get(parsedEntity);
                    }
                    count.put(parsedValues[1], new Integer(totalCount));
                }

            }
        }

        for (Map.Entry<String, List<String>> entry : enititesMap.entrySet()) {
            int totalCount = 0;
            for (String entity : entry.getValue()) {
                totalCount += count.get(entity);
            }
            count.put(entry.getKey(), new Integer(totalCount));
        }

        int globalCount = 0;

        for (Integer curCount : count.values()) {
            globalCount += curCount.intValue();
        }

        if (globalCount <= l) {
            System.out.println("1 " + globalCount);
        } else {
            System.out.println("0 " + globalCount);
        }

    }

    public static void main(String[] args) {
        entityExpansion(5, new String[]{"<!ENTITY a0 a1;a2>",
                "<!ENTITY a1 a2;a2>",
                "<!ENTITY a2 11888>" });
    }

}
