import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DaySeven {
    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    public static void partOne() {
        System.out.println("DAY SEVEN - PART ONE \n");

        File myObj = new File("inputs/daySeven/input.txt");
        ArrayList<char[]> input = new ArrayList<>();

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().trim();
                input.add(data.toCharArray());
            }

            int count = 0;
            ArrayList<BigInteger> counts = new ArrayList<>(Collections.nCopies(input.get(0).length, BigInteger.ZERO));

            for (char[] chars : input) {
                for (int col = 0; col < chars.length; col++) {
                    switch (chars[col]) {
                        case 'S': {
                            counts.set(col, counts.get(col).add(BigInteger.ONE));
                            continue;
                        }
                        case '^': {
                            if(counts.get(col).equals(BigInteger.ONE)) {
                                if (col > 0) {
                                    counts.set(col - 1, BigInteger.ONE);
                                }

                                if (col < (chars.length - 1)) {
                                    counts.set(col + 1, BigInteger.ONE);
                                }

                                counts.set(col, BigInteger.ZERO);
                                count++;
                            }
                        }
                    }
                }

                System.out.println(counts);
            }

            System.out.println("Result: " + count + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void partTwo() {
        System.out.println("DAY SEVEN - PART TWO \n");

        File myObj = new File("inputs/daySeven/input.txt");
        ArrayList<char[]> input = new ArrayList<>();

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().trim();
                input.add(data.toCharArray());
            }

            BigInteger count = BigInteger.ZERO;
            ArrayList<BigInteger> counts = new ArrayList<>(Collections.nCopies(input.get(0).length, BigInteger.ZERO));

            for (char[] chars : input) {
                for (int col = 0; col < chars.length; col++) {
                    switch (chars[col]) {
                        case 'S': {
                            counts.set(col, counts.get(col).add(BigInteger.ONE));
                            continue;
                        }
                        case '^': {
                            if (col > 0) {
                                counts.set(col - 1, counts.get(col - 1).add(counts.get(col)));
                            }

                            if (col < (chars.length - 1)) {
                                counts.set(col + 1, counts.get(col + 1).add(counts.get(col)));
                            }

                            counts.set(col, BigInteger.ZERO);
                        }
                    }
                }

                System.out.println(counts);
            }

            for (BigInteger c : counts) {
                count = count.add(c);
            }

            System.out.println("Result: " + count + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }
}