package data.structures.algorithms.oracle;

public class NumberOfRounds {
    public static int numberOfRounds(String loginTime, String logoutTime) {
        // Convert loginTime and logoutTime to minutes since 00:00
        int loginMinutes = convertToMinutes(loginTime);
        int logoutMinutes = convertToMinutes(logoutTime);
        System.out.println("loginMinutes : " + loginMinutes);
        System.out.println("logoutMinutes : " + logoutMinutes);
        // If logoutTime is earlier than loginTime, we split the calculation into two parts
        int roundsPlayed = 0;
        if (logoutMinutes < loginMinutes) {
            // From loginTime to midnight (23:59)
            roundsPlayed += (1440 - loginMinutes);
            System.out.println("loginMinutes : " + roundsPlayed);
            roundsPlayed /= 15;
            // From 00:00 to logoutTime
            roundsPlayed += logoutMinutes;
            System.out.println("Total Minutes: " + roundsPlayed);
            roundsPlayed /= 15;
        } else {
            // From loginTime to logoutTime
            roundsPlayed += (logoutMinutes - loginMinutes);
            roundsPlayed /= 15;
        }
        return roundsPlayed;
    }

    // Helper function to convert time in "HH:mm" format to minutes since 00:00
    public static int convertToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    public static void main(String[] args) {
        System.out.println("quarter Count : " + numberOfRounds("09:31", "12:30"));
        System.out.println("quarter Count : " + numberOfRounds("09:31", "10:14"));
        System.out.println("quarter Count : " + numberOfRounds("21:30", "03:00"));
    }
}

/*
1904. The Number of Full Rounds You Have Played
You are participating in an online chess tournament.
There is a chess round that starts every 15 minutes. The first round of the day starts at 00:00,
and after every 15 minutes, a new round starts.

For example, the second round starts at 00:15, the fourth round starts at 00:45, and the seventh round
starts at 01:30.
You are given two strings loginTime and logoutTime where:

loginTime is the time you will login to the game, and
logoutTime is the time you will logout from the game.
If logoutTime is earlier than loginTime, this means you have played from loginTime to midnight and from
midnight to logoutTime.

Return the number of full chess rounds you have played in the tournament.

Note: All the given times follow the 24-hour clock. That means the first round of the day starts at 00:00
and the last round of the day starts at 23:45.
* */
