package data.structures.algorithms.oracle;

import java.util.HashMap;
import java.util.Map;

public class Logger {
    private Map<String, Integer> loggerDirectory = new HashMap<>();
    private static final int defaultIntervalToPrint = 10;

    public boolean printLoggerWithTimeStamp(String message, int currentTimeStamp) {
        int initialTimeStamp = loggerDirectory.getOrDefault(message, -10000);
        if (currentTimeStamp - initialTimeStamp >= defaultIntervalToPrint) {
            loggerDirectory.put(message, currentTimeStamp);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Logger logger = new Logger();
        Map<String, Integer> initialTimeStamp = new HashMap<>();
        initialTimeStamp.put("foo", 1);
        initialTimeStamp.put("bar", 1);
        initialTimeStamp.put("boo", 1);
        initialTimeStamp.put("koo", 1);
        initialTimeStamp.put("choo", 1);

        Map<String, Integer> currentTimeStamps = new HashMap<>();
        currentTimeStamps.put("foo", 6);  // Example: After 5 intervals
        currentTimeStamps.put("bar", 4);
        currentTimeStamps.put("boo", 20); // Example: After 9 intervals
        currentTimeStamps.put("koo", 3);
        currentTimeStamps.put("choo", 11); // Example: After 7 intervals

        for (Map.Entry<String, Integer> entry : initialTimeStamp.entrySet()) {
            System.out.println(" Print msg for " + entry.getKey() + " time: " + entry.getValue() +
                    logger.printLoggerWithTimeStamp(entry.getKey(), entry.getValue()));
            System.out.println(" Print msg for " + entry.getKey() + " time: " + entry.getValue() +
                    logger.printLoggerWithTimeStamp(entry.getKey(), currentTimeStamps.get(entry.getKey())));
            System.out.println();
        }
    }
}
