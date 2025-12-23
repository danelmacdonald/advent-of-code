import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayThree {
    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    public static void partOne() {
        System.out.println("DAY THREE - PART ONE \n");

        File myObj = new File("inputs/dayThree/input.txt");

        BigInteger output = BigInteger.ZERO;

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                int highest = 0;
                int highestIndex = 0;

                int lowest = 0;

                for (int battery = 0; battery < data.length(); battery++) {
                    if (battery == (data.length() - 1)) {
                        data = data.substring(highestIndex + 1);
                        break;
                    }

                    if (highest < Integer.parseInt(String.valueOf(data.charAt(battery)))) {
                        highestIndex = battery;
                        highest = Integer.parseInt(String.valueOf(data.charAt(battery)));
                    }
                }

                for (int battery = 0; battery < data.length(); battery++) {
                    if (lowest < Integer.parseInt(String.valueOf(data.charAt(battery)))) {
                        lowest = Integer.parseInt(String.valueOf(data.charAt(battery)));
                    }
                }

                System.out.println("Joltage: " + BigInteger.valueOf(highest * 10L + lowest) + "\n");

                output = output.add(BigInteger.valueOf(highest * 10L + lowest));
            }

            System.out.println("Result: " + output + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void partTwo() {
        System.out.println("DAY THREE - PART TWO \n");

        File myObj = new File("inputs/dayThree/input.txt");

        BigInteger output = BigInteger.ZERO;
        int bank = 1;

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                int batteryNumber = 12;
                List<Integer> batteries = new ArrayList<>();

                System.out.println("Bank " + bank);

                while (batteryNumber > 0) {
                    while (batteries.size() < 12) {
                        int highest = 0;
                        int highestIndex = 0;

                        for (int index = 0; index < data.length(); index++) {
                            if ((12 - batteries.size() > (data.length() - index))) {
                                data = data.substring(highestIndex + 1);
                                break;
                            }

                            if (Integer.parseInt(String.valueOf(data.charAt(index))) > highest) {
                                highest = Integer.parseInt(String.valueOf(data.charAt(index)));
                                highestIndex = index;
                            }

                            if (index == (data.length() - 1)) {
                                data = data.substring(highestIndex + 1);
                                break;
                            }
                        }

                        batteries.add(highest);
                    }

                    batteryNumber--;
                }

                System.out.println("Selected batteries: " + batteries);

                BigInteger sum = BigInteger.ZERO;

                for (int index = 0; index < batteries.size(); index++) {
                    sum = sum.add(BigInteger.valueOf((long) (batteries.get(index) * (Math.pow(10, batteries.size() - index - 1)))));
                }

                System.out.println("Bank sum: " + sum + "\n");

                output = output.add(sum);
                bank++;
            }

            System.out.println("Joltage: " + output + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }
}