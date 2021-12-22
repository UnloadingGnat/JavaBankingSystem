/**
 * Loading Bar: NOT PART OF BANKING SYSTEM
 * <p>
 * This class is merely for aesthetics and is not part of the  Banking System.
 * </p>
 */


public class Loading {
    // Set up text colours
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[42m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void main() throws InterruptedException {
        // Output title and frame of bar
        System.out.print(ANSI_YELLOW + "\nStarting Banking System...\n[                        ]" + ANSI_RESET);
        System.out.flush();
        System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
        System.out.flush();
        Thread.sleep(100);
        // Loop to create a loading bar effect
        for (int i = 0; i < 24; i++) {
            System.out.print(ANSI_GREEN + "░" + ANSI_RESET);
            System.out.flush();
            Thread.sleep(100);
        }
        // Flush screen
        System.out.println();
        System.out.flush();
    }
}