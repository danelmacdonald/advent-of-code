import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class DayFive {
    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    public static void partOne() {
        int count = 0;
        ArrayList<String> ranges = new ArrayList<>();

        System.out.println("DAY FIVE - PART ONE \n");
        File myObj = new File("inputs/dayFive/input.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if (data.contains("-")) {
                    ranges.add(data);
                    System.out.println("Range added: " + data);
                } else if (data.isBlank()) {
                    System.out.println("Checking ingredients\n");
                } else {
                    for (String range : ranges) {
                        String[] split = range.split("-");
                        long min = Long.parseLong(split[0]);
                        long max = Long.parseLong(split[1]);

                        long current = Long.parseLong(data);

                        if (min <= current && current <= max) {
                            count++;
                            System.out.println("Fresh: " + data);
                            break;
                        } else {
                            System.out.println("Not fresh: " + data);
                        }
                    }
                }
            }

            System.out.println("Result: " + count + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void partTwo() {
        Map<Long, Long> ranges = new java.util.HashMap<>(Map.of());

        System.out.println("DAY FIVE - PART TWO \n");
        File myObj = new File("inputs/dayFive/input.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if (data.contains("-")) {
                    String[] split = data.split("-");
                    long min = Long.parseLong(split[0]);
                    long max = Long.parseLong(split[1]);

                    Long currentMax = ranges.get(min);

                    if (currentMax != null && currentMax < max) {
                        ranges.put(min, max);
                    } else if (currentMax == null) {
                        ranges.put(min, max);
                    }
                }
            }

            do {
                print(ranges);
            } while (process(ranges) > 0);

            long sum = 0;
            for (int index = 0; index < ranges.size(); index++) {
                Long key = ranges.keySet().stream().toList().get(index);
                sum += (ranges.get(key) - key + 1);
            }

            System.out.println("Result: " + sum + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public static int process(Map<Long, Long> ranges) {
        var sortedMins = ranges.keySet().stream().sorted().toList();

        int count = 0;

        for (int index = 0; index < (sortedMins.size() - 1); index++) {
            Long min = sortedMins.get(index);
            Long max = ranges.get(min);

            int tempIndex = index - 1;
            while (max == null) {
                max = ranges.get(sortedMins.get(tempIndex));
                tempIndex--;
            }

            Long nextMin = sortedMins.get(index + 1);
            Long nextMax = ranges.get(nextMin);

            if (nextMin <= max) {
                ranges.remove(nextMin);
                if (nextMax > max) {
                    ranges.put(min, nextMax);
                }

                count++;
            }
        }

        return count;
    }

    public static void print(Map<Long, Long> ranges) {
        var sortedMins = ranges.keySet().stream().sorted().toList();

        for (int index = 0; index < (sortedMins.size() - 1); index++) {
            System.out.println(sortedMins.get(index) + " " + ranges.get(sortedMins.get(index)));
        }

        System.out.println("\n\n");
    }
}