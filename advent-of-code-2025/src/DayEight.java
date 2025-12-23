import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class DayEight {
    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    public static void partOne() {
        System.out.println("DAY EIGHT - PART ONE \n");

        File myObj = new File("inputs/dayEight/input.txt");
        ArrayList<String[]> boxes = new ArrayList<>();
        ArrayList<ArrayList<Integer>> groups = new ArrayList<>();

        ArrayList<BoxDistance> distances = new ArrayList<>();

        try (Scanner myReader = new Scanner(myObj)) {
            int index = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().trim();
                boxes.add(data.split(","));

                ArrayList<Integer> start = new ArrayList<>();
                start.add(index);
                groups.add(start);
                index++;
            }

            for (int box = 0; box < boxes.size(); box++) {
                int next = box + 1;

                while (next < boxes.size()) {
                    BigInteger distance = getDistance(boxes, box, next);
                    distances.add(new BoxDistance(box, next, distance));
                    next++;
                }
            }

            distances.sort(Comparator.comparing(d -> d.distance));

            for (index = 0; index < 1000; index++) {
                int firstIndex = getIndex(groups, distances.get(index).boxOneIndex);
                int secondIndex = getIndex(groups, distances.get(index).boxTwoIndex);

                if (firstIndex != secondIndex) {
                    groups.get(firstIndex).addAll(groups.get(secondIndex));
                    groups.remove(secondIndex);
                }
            }

            groups.sort(Comparator.comparing(ArrayList::size));

            long count = (long) groups.get(groups.size() - 1).size() * groups.get(groups.size() - 2).size() * groups.get(groups.size() - 3).size();

            System.out.println("Result: " + count + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void partTwo() {
        System.out.println("DAY EIGHT - PART TWO \n");

        File myObj = new File("inputs/dayEight/input.txt");
        ArrayList<String[]> boxes = new ArrayList<>();
        ArrayList<ArrayList<Integer>> groups = new ArrayList<>();

        long count = 0;
        ArrayList<BoxDistance> distances = new ArrayList<>();

        try (Scanner myReader = new Scanner(myObj)) {
            int index = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().trim();
                boxes.add(data.split(","));

                ArrayList<Integer> start = new ArrayList<>();
                start.add(index);
                groups.add(start);
                index++;
            }

            for (int box = 0; box < boxes.size(); box++) {
                int next = box + 1;

                while (next < boxes.size()) {
                    BigInteger distance = getDistance(boxes, box, next);
                    distances.add(new BoxDistance(box, next, distance));
                    next++;
                }
            }

            distances.sort(Comparator.comparing(d -> d.distance));

            index = 0;
            while (groups.size() > 1 && index < distances.size()) {
                int firstIndex = getIndex(groups, distances.get(index).boxOneIndex);
                int secondIndex = getIndex(groups, distances.get(index).boxTwoIndex);

                if (firstIndex != secondIndex) {
                    groups.get(firstIndex).addAll(groups.get(secondIndex));
                    groups.remove(secondIndex);
                }

                if (groups.size() == 1) {
                    long xOne = Integer.parseInt(boxes.get(distances.get(index).boxOneIndex)[0]);
                    long xTwo = Integer.parseInt(boxes.get(distances.get(index).boxTwoIndex)[0]);

                    count = xOne * xTwo;
                }

                index++;
            }

            System.out.println("Result: " + count + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    private static int getIndex(ArrayList<ArrayList<Integer>> groups, int boxIndex) {
        for (int i = 0; i < groups.size(); i++) {
            for (int y = 0; y < groups.get(i).size(); y++) {
                if (groups.get(i).get(y) == boxIndex) {
                    return i;
                }
            }
        }

        return -1;
    }

    private static BigInteger getDistance(ArrayList<String[]> boxes, int box, int next) {
        long xOne = Integer.parseInt(boxes.get(box)[0]);
        long yOne = Integer.parseInt(boxes.get(box)[1]);
        long zOne = Integer.parseInt(boxes.get(box)[2]);

        long xTwo = Integer.parseInt(boxes.get(next)[0]);
        long yTwo = Integer.parseInt(boxes.get(next)[1]);
        long zTwo = Integer.parseInt(boxes.get(next)[2]);

        long xDistance = (long) Math.pow(xOne - xTwo, 2);
        long yDistance = (long) Math.pow(yOne - yTwo, 2);
        long zDistance = (long) Math.pow(zOne - zTwo, 2);

        return BigInteger.valueOf(xDistance).add(BigInteger.valueOf(yDistance)).add(BigInteger.valueOf(zDistance));
    }

    public static class BoxDistance {
        public int boxOneIndex;

        public int boxTwoIndex;

        public BigInteger distance;

        public BoxDistance(int one, int two, BigInteger d) {
            boxOneIndex = one;
            boxTwoIndex = two;
            distance = d;
        }
    }
}