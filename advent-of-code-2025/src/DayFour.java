import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayFour {
    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    public static void partOne() {
        int count = 0;
        ArrayList<char[]> grid = new ArrayList<>();

        System.out.println("DAY FOUR - PART ONE \n");
        File myObj = new File("inputs/dayFour/input.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                grid.add(data.toCharArray());
            }

            count += process(grid);

            System.out.println("Result: " + count + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void partTwo() {
        int count = 0;
        ArrayList<char[]> grid = new ArrayList<>();

        System.out.println("DAY FOUR - PART TWO \n");
        File myObj = new File("inputs/dayFour/input.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                grid.add(data.toCharArray());
            }

            int updates = process(grid);
            count += updates;
            System.out.println("Removed: " + updates + "\n");

            while (updates > 0) {
                updates = process(grid);
                count += updates;
                System.out.println("Removed: " + updates + "\n");
            }

            System.out.println("Result: " + count + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public static int process(ArrayList<char[]> grid) {
        int count = 0;

        for (int row = 0; row < grid.size(); row++) {
            for (int col = 0; col < grid.get(row).length; col++) {
                int space = 8;

                int left = ((col - 1) < 0) ? col : (col - 1);
                int up = ((row - 1) < 0) ? row : (row - 1);
                int right = ((col + 1) > (grid.get(row).length - 1)) ? col : (col + 1);
                int down = ((row + 1) > (grid.size() - 1)) ? row : (row + 1);

                if (grid.get(row)[col] == '@') {
                    for (int r = up; r <= down; r++) {
                        for (int c = left; c <= right; c++) {
                            if (grid.get(r)[c] == '@' || grid.get(r)[c] == 'x') {
                                if (col != c || row != r) {
                                    space--;
                                }
                            }
                        }
                    }

                    if (space >= 5) {
                        count++;
                        grid.get(row)[col] = 'x';
                    }
                }

            }
        }

        printGrid(grid);

        for (char[] chars : grid) {
            for (int col = 0; col < chars.length; col++) {
                if (chars[col] == 'x') {
                    chars[col] = '.';
                }
            }
        }

        return count;
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