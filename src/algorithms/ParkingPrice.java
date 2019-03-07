package algorithms;

/**
 * Merge Sort is used to sort linked list in O(nlogn) time using constant space.
 */
public class ParkingPrice {

    static int findParkingPrice(String entryTime, String leavingTime) {
        String[] parsedEntryTime = entryTime.split(":", -1);
        if (parsedEntryTime[0].startsWith("0")) {
            parsedEntryTime[0] = (parsedEntryTime[0]).split("0", -1)[1];
            if (parsedEntryTime[0].isEmpty()) {
                parsedEntryTime[0] = "0";
            }
        }
        if (parsedEntryTime[1].startsWith("0")) {
            parsedEntryTime[1] = parsedEntryTime[1].split("0", -1)[1];
            if (parsedEntryTime[1].isEmpty()) {
                parsedEntryTime[1] = "0";
            }
        }

        int entryTimeHour = Integer.parseInt(parsedEntryTime[0]);
        int entryTimeMin = Integer.parseInt(parsedEntryTime[1]);

        String[] parsedLeaveTime = leavingTime.split(":", -1);
        if (parsedLeaveTime[0].startsWith("0")) {
            parsedLeaveTime[0] = parsedLeaveTime[0].split("0", -1)[1];
            if (parsedLeaveTime[0].isEmpty()) {
                parsedLeaveTime[0] = "0";
            }
        }
        if (parsedLeaveTime[1].startsWith("0")) {
            parsedLeaveTime[1] = parsedLeaveTime[1].split("0", -1)[1];
            if (parsedLeaveTime[1].isEmpty()) {
                parsedLeaveTime[1] = "0";
            }
        }
        int leaveTimeHour = Integer.parseInt(parsedLeaveTime[0]);
        int leaveTimeMin = Integer.parseInt(parsedLeaveTime[1]);

        int passedMinutes = ((leaveTimeHour * 60) + leaveTimeMin) - ((entryTimeHour * 60) + entryTimeMin);

        if (passedMinutes <= 60) {
            return 5;
        } else {
            return 2 + 3 + (4 * (int) Math.ceil((passedMinutes - 60f) / 60));
        }
    }

    public static void main(String[] args) {
        System.out.println(findParkingPrice("10:00", "16:01"));
    }

}
