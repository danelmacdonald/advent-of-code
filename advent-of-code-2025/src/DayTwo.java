import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class DayTwo {
    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    public static void partOne() {
        BigInteger count = BigInteger.ZERO;
        int duplicantCount = 0;

        System.out.println("DAY TWO - PART ONE \n");
        File myObj = new File("inputs/dayTwo/input.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                String[] ranges = data.split(",");

                for (String range : ranges) {
                    System.out.println("Range: " + range);

                    String[] amounts = range.split("-");
                    BigInteger min = new BigInteger(amounts[0]);
                    BigInteger max = new BigInteger(amounts[1]);
                    BigInteger current = min;

                    for (BigInteger index = min; (index.compareTo(max) <= 0); index = index.add(BigInteger.ONE)) {
                        String numberAsString = String.valueOf(current);
                        int midPoint = numberAsString.length() / 2;

                        if (numberAsString.length() % 2 == 0) {
                            if (numberAsString.substring(0, midPoint).equals(numberAsString.substring(midPoint))) {
                                duplicantCount++;
                                count = count.add(current);

                                System.out.println("Duplicate found: " + numberAsString);
                                System.out.println("Total count: " + count + " \n");
                            }
                        }

                        current = current.add(BigInteger.ONE);
                    }

                    System.out.println("Duplicant count: " + duplicantCount + " \n");
                    System.out.println("Total count: " + count + " \n");
                    duplicantCount = 0;
                }
            }

            System.out.println("Result: " + count + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void partTwo() {
        BigInteger count = BigInteger.ZERO;
        int duplicantCount = 0;

        System.out.println("DAY TWO - PART TWO \n");
        File myObj = new File("inputs/dayTwo/input.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                String[] ranges = data.split(",");

                for (String range : ranges) {
                    System.out.println("Range: " + range);

                    String[] amounts = range.split("-");
                    BigInteger min = new BigInteger(amounts[0]);
                    BigInteger max = new BigInteger(amounts[1]);
                    BigInteger current = min;

                    for (BigInteger index = min; (index.compareTo(max) <= 0); index = index.add(BigInteger.ONE)) {
                        String numberAsString = String.valueOf(current);
                        int groupSize = (int) Math.ceil(numberAsString.length() / 2.0);

                        while (groupSize > 0) {
                            if (isEqual(splitBySize(numberAsString, groupSize))) {
                                duplicantCount++;
                                count = count.add(current);

                                System.out.println("Duplicate found: " + numberAsString);
                                System.out.println("Total count: " + count + " \n");
                                break;
                            }

                            groupSize--;
                        }

                        current = current.add(BigInteger.ONE);
                    }

                    System.out.println("Duplicant count: " + duplicantCount + " \n");
                    duplicantCount = 0;
                }
            }

            System.out.println("Result: " + count + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    static List<String> splitBySize(String s, int size) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i += size) {
            result.add(s.substring(i, Math.min(i + size, s.length())));
        }
        return result;
    }

    static Boolean isEqual(List<String> groups) {
        if (groups.size() < 2) return false;

        String first = groups.get(0);
        return groups.stream().allMatch(first::equals);
    }
}