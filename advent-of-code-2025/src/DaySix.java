import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class DaySix {
    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    public static void partOne() {
        System.out.println("DAY SIX - PART ONE \n");

        File myObj = new File("inputs/daySix/input.txt");
        ArrayList<String[]> input = new ArrayList<>();

        long count = 0;

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().trim();
                input.add(data.split("\\s+"));
            }

            int items = input.size();
            int problems = input.get(0).length;

            for (int p = 0; p < problems; p++) {
                long sum = Long.parseLong(input.get(0)[p]);
                String operator = input.get(items - 1)[p];

                for (int i = 1; i < (items - 1); i++) {
                    if (Objects.equals(operator, "*")) {
                        sum *= Long.parseLong(input.get(i)[p]);
                    } else if (Objects.equals(operator, "+")) {
                        sum += Long.parseLong(input.get(i)[p]);
                    }
                }

                System.out.println("Problem: " + (p + 1) + " Sum: " + sum + "\n");
                count += sum;
            }

            System.out.println("Result: " + count + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void partTwo() {
        System.out.println("DAY SIX - PART TWO \n");

        File myObj = new File("inputs/daySix/input.txt");
        ArrayList<char[]> input = new ArrayList<>();

        long count = 0;

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(data.toCharArray());
            }

            ArrayList<String> problem = new ArrayList<>();

            for (int col = (input.get(0).length - 1); col >= 0; col--) {
                StringBuilder number = new StringBuilder();

                for (char[] chars : input) {
                    if (col < chars.length) {
                        if (chars[col] != ' ') {
                            if (chars[col] == '+') {
                                long amount = Long.parseLong(problem.get(0));
                                for (int no = 1; no < problem.size(); no++) {
                                    amount += Long.parseLong(problem.get(no));
                                }

                                if (!number.isEmpty()) {
                                    amount += Long.parseLong(number.toString());
                                    number = new StringBuilder();
                                }

                                System.out.println("Amount: " + amount + "\n");

                                count += amount;
                                problem.clear();
                            } else if (chars[col] == '*') {
                                long amount = Long.parseLong(problem.get(0));

                                for (int no = 1; no < problem.size(); no++) {
                                    amount *= Long.parseLong(problem.get(no));
                                }

                                if (!number.isEmpty()) {
                                    amount *= Long.parseLong(number.toString());
                                    number = new StringBuilder();
                                }

                                System.out.println("Amount: " + amount + "\n");

                                count += amount;
                                problem.clear();
                            } else {
                                number.append(chars[col]);
                            }
                        }
                    }
                }

                if (!number.isEmpty()) {
                    problem.add(number.toString());
                }
            }

            System.out.println("Result: " + count + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }
}