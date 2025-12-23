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

        long count = 0;
        ArrayList<char[]> input = new ArrayList<>();

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().trim();
                input.add(data.toCharArray());
            }

            printGrid(input);

            for (int row = 0; row < input.size(); row++) {
                for (int col = 0; col < input.get(row).length; col++) {
                    switch (input.get(row)[col]) {
                        case 'S': {
                            if (row < (input.size() - 1) && input.get(row + 1)[col] == '.') {
                                input.get(row + 1)[col] = '|';
                            }

                            continue;
                        }
                        case '^': {
                            if (row > 0 && input.get(row - 1)[col] == '|') {
                                count++;

                                if (col < (input.get(row).length - 1) && input.get(row)[col + 1] == '.') {
                                    input.get(row)[col + 1] = '|';
                                }

                                if (col > 0 && input.get(row)[col - 1] == '.') {
                                    input.get(row)[col - 1] = '|';

                                    if (row < (input.size() - 1) && input.get(row + 1)[col - 1] == '.') {
                                        input.get(row + 1)[col - 1] = '|';
                                    }
                                }
                            }

                            continue;
                        }
                        case '|': {
                            if (row < (input.size() - 1) && input.get(row + 1)[col] == '.') {
                                input.get(row + 1)[col] = '|';
                            }
                        }
                    }
                }

                System.out.println("Iteration: " + row + "\n");
                printGrid(input);
                System.out.println();
            }


            System.out.println("Result: " + count + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void partTwo() {
        System.out.println("DAY SEVEN - PART TWO \n");
        File myObj = new File("inputs/daySeven/input.txt");

        BigInteger count = BigInteger.ZERO;
        ArrayList<char[]> input = new ArrayList<>();

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().trim();
                input.add(data.toCharArray());
            }

            printGrid(input);

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

    public static void printGrid(ArrayList<char[]> grid) {
        for (char[] row : grid) {
            for (char col : row) {
                System.out.print(col);
            }
            System.out.println();
        }
    }
}