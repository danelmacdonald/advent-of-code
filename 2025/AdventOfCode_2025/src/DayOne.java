import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayOne {
    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    public static void partOne() {
        int current = 50;
        int count = 0;

        System.out.println("DAY ONE - PART ONE");
        File myObj = new File("inputs/dayOne/input.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println("Rotating " + current + " by " + data);

                if (data.charAt(0) == 'L') {
                    current -= Integer.parseInt(data.substring(1));

                    while (current < 0) {
                        current += 100;
                        count++;
                    }
                } else {
                    current += Integer.parseInt(data.substring(1));

                    while (current > 99) {
                        current -= 100;
                        count++;
                    }
                }

                System.out.println("Current position: " + current + "- Crossed 0 " + count + "times \n");

                if (current == 0) {
                    count++;
                }
            }

            System.out.println("Result: " + count + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void partTwo() {
        int current = 50;
        int count = 0;

        System.out.println("DAY ONE - PART TWO");
        File myObj = new File("inputs/dayOne/input.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                char direction = data.charAt(0);
                int amount = Integer.parseInt(data.substring(1));
                count += (int) Math.floor(amount / 100.0);

                System.out.println("Rotating " + current + " by " + data);

                if (direction == 'L') {
                    current -= amount % 100;
                    if (current == 0) {
                        count++;
                    } else if (current < 0 && (current + amount % 100) == 0) {
                        current += 100;
                    } else if (current < 0) {
                        current += 100;
                        count++;
                    }
                } else {
                    current += amount % 100;
                    if (current == 0) {
                        count++;
                    } else if (current > 99) {
                        current -= 100;
                        count++;
                    }
                }

                System.out.println("Current position: " + current + "\n");
            }

            System.out.println("Result: " + count + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }
}