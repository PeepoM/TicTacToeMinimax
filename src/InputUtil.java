import java.util.Scanner;

public final class InputUtil {
    private static final Scanner sc = new Scanner(System.in);

    private InputUtil() {
    }

    public static int readIntFromUser() {
        do {
            if (sc.hasNextInt()) {
                int input = sc.nextInt();
                sc.nextLine();

                return input;
            }

            System.out.print("Please enter an integer: ");

            sc.nextLine();
        } while (true);
    }

    public static char readCharFromUser() {
        do {
            if (sc.hasNextLine()) {
                String input = sc.nextLine();

                if (input.length() == 1)
                    return input.charAt(0);
            }

            System.out.print("Please enter a single character: ");
        } while (true);
    }

    public static String readStringFromUser() {
        return sc.nextLine();
    }
}