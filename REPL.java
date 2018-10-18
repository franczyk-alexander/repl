import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;
import java.lang.Math;

public class REPL {
    public static void main(String[] args) {
        System.out.println("REPL");
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scan.nextLine();
            String[] arguments = input.split(" ");

            if (arguments[0].equals("exit") || arguments[0].equals("quit")) {
                break;
            }

            if (arguments[0].equals("gcf")) {
                if (arguments.length != 3 || !isInteger(arguments[1]) || !isInteger(arguments[2])) {
                    System.out.println("usage: gcf [multiple] [multiple]");
                } else {
                    System.out.println(gcf(Integer.valueOf(arguments[1]), Integer.valueOf(arguments[2])));
                }
            }
            else if (arguments[0].equals("lcm")) {
                if (arguments.length != 3 || !isInteger(arguments[1]) || !isInteger(arguments[2])) {
                    System.out.println("usage: lcm [factor] [factor]");
                } else {
                    System.out.println(lcm(Integer.valueOf(arguments[1]), Integer.valueOf(arguments[2])));
                }
            }
            else if (arguments[0].equals("sleep")) {
                if (arguments.length != 2 || !isDouble(arguments[1])) {
                    System.out.println("usage: sleep [seconds]");
                } else {
                    try {
                        Thread.sleep((int) (Double.parseDouble(arguments[1]) * 1000));
                    } catch (InterruptedException e) {
                        //
                    }
                }
            }
            else if (arguments[0].equals("clear")) {
                clearScreen();
            }
            else {
                System.out.println("unknown operation \"" + arguments[0] + "\"");
            }
        }
    }

    private static int gcf(int x, int y) {
        if (y == 0) {
            return x;
        } else {
            return gcf(y, x % y);
        }
    }

    private static int lcm(int x, int y) {
        return Math.abs(x * y) / gcf(x, y);
    }

    private static boolean isInteger(String integer) {
        try {
            Integer.valueOf(integer);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean isDouble(String doub) {
        try {
            Double.parseDouble(doub);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static void clearScreen() {
        String os = System.getProperty("os.name");
        try {
            if (os.toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            //
        }
    }
}