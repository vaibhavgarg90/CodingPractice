package moengage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Friend {

    private Map<Integer, Set<Integer>> friendMap;

    private Map<Integer, Integer> friendCount;

    public Friend() {
        this.friendMap = new HashMap<>();
        this.friendCount = new HashMap<>();
    }

    private void processFriendInformation(int first, int second) {
        if (!friendMap.containsKey(first)) {
            friendMap.put(first, new HashSet<>());
        }

        Set<Integer> friends = friendMap.get(first);
        friends.add(second);
        friendMap.put(first, friends);

        if (friendMap.containsKey(second)) {
            friends = friendMap.get(second);
            if (friends.contains(first)) {
                incrementCount(first, second);
            }
        }
    }

    private void incrementCount(int first, int second) {
        Integer count = friendCount.get(first);
        if (count == null) {
            count = 0;
        }

        friendCount.put(first, count.intValue() + 1);

        count = friendCount.get(second);
        if (count == null) {
            count = 0;
        }

        friendCount.put(second, count.intValue() + 1);
    }

    public static void main(String[] args) {

    }
}
