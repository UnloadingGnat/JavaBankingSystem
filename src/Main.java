import java.util.ArrayList;
import java.util.Scanner;

/**
 * Summary of program: System starts with main menu, asking user whether they would like to: create account, find account, generate reports or quit.
 * <p>
 * [1] Create Account: Create a specified type(Checking, Saving, Senior/Child) of account with a designated account name and number<br>
 * [2] Find Account: Find any current account by either account name or account number<br>
 * [3] Generate Reports: Produce reports for total money invested and the number of each type of account<br>
 * [4] Monthly Routines: Simulate next month, set and pay interest and charge other appropriate fees<br>
 * [q] Quit: Quit the program<br>
 * </p>
 *`
 * @author UnlaodingGnat
 * @course ICS4U
 * @date 2021/12/08
 */


public class Main {

    // Set up text colours
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_TEXT = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";


    public static void main(String[] args) throws Exception {
        // Set up user input
        Scanner sc = new Scanner(System.in);

        // Create array list for all the accounts
        ArrayList<Account> accounts = new ArrayList<>();

        // Clear screen path and flush
        System.out.print("\u001b[2J");
        System.out.flush();

        // Output tagline before menu
        System.out.print("========================================\n" + ANSI_CYAN + "Unloading Gnat Banking System - Your potential. Our passion.\n" + ANSI_RESET + "========================================\n");
        System.out.println("Please enter the following information:\n────────────────────────────────────────");

        // Initialize variables
        double interestRateChequing;
        double interestRateSavings;
        double interestRateChildSenior;
        // Ask user for interest rate of each type of account
        System.out.print("\nEnter Interest Rate of Checking Accounts: \n%");
        interestRateChequing = sc.nextDouble() / 100;
        Chequing.setInterestRate(interestRateChequing);
        System.out.print("\nEnter Interest Rate of Savings Accounts: \n%");
        interestRateSavings = sc.nextDouble() / 100;
        Savings.setInterestRate(interestRateSavings);
        System.out.print("\nEnter Interest Rate of Child/Senior Accounts: \n%");
        interestRateChildSenior = sc.nextDouble() / 100;
        SeniorChild.setInterestRate(interestRateChildSenior);

        // Loading bar
        Loading.main();

        // Loop until user quits
        int month = 1;
        char choice = '0';
        while (choice != 'q') {
            // Output main menu and current month to user
            System.out.println("\n==========================================\n" + ANSI_GREEN_TEXT +
                    "Welcome to UnloadingGnat™ Banking System" + ANSI_RESET +
                    "\n==========================================");
            System.out.println("Month: " + month + "\n──────────────────────────────────────────");
            System.out.println("[1] Create Account\n[2] Find Account\n[3] Generate Reports\n[4] Routines for Next Month\n" + ANSI_RED + "[q] QUIT" + ANSI_RESET);
            choice = sc.next().toLowerCase().charAt(0);
            // Ask user what action they would like to do in the system
            if (choice == '1') {
                // Create a new account
                System.out.println("\n==============================\n" + ANSI_BLUE + "Welcome to the Account Creator" + ANSI_RESET + "\n==============================");
                System.out.println("\nWhat is the name of the account holder? (No Spaces)");
                String accountHolderName = sc.next().toUpperCase();
                // Specify account type
                System.out.println("\nWhat type of account would you like to open?");
                System.out.println("[1] Chequing\n[2] Savings\n[3] Senior/Child");
                int accountType = sc.nextInt();
                // Create new chequing account
                if (accountType == 1) {
                    accounts.add(new Chequing(accountHolderName, accounts.size()));
                }
                // Create new savings account
                else if (accountType == 2) {
                    accounts.add(new Savings(accountHolderName, accounts.size()));
                }
                // Create new senior/child account
                else {
                    accounts.add(new SeniorChild(accountHolderName, accounts.size()));
                }
            } else if (choice == '2') {
                // Find an account and choose how
                System.out.println("\n=============================\n" + ANSI_BLUE + "Welcome to the Account Finder" + ANSI_RESET + "\n=============================");
                System.out.println("\nHow would you like to find the account?");
                System.out.println("[1] Account Holder Name\n[2] Account Number");
                int searchType = sc.nextInt();
                int accountIndex = 0;
                int foundIndex = 0;
                boolean found = false;
                // Find account by account holder name
                if (searchType == 1) {
                    System.out.println("\nWhat is the account holder name?");
                    String search = sc.next().toUpperCase();
                    // Loop through all accounts to find specified account
                    for (Account nameSearch : accounts) {
                        accountIndex++;
                        if (nameSearch.getName().equals(search)) {
                            foundIndex = accountIndex;
                            found = true;
                        }
                    }
                }
                // Find account by account number
                if (searchType == 2) {
                    System.out.println("\nWhat is the account number?");
                    int accountNumber = sc.nextInt();
                    // Loop through all accounts to find specified account
                    for (Account numSearch : accounts) {
                        accountIndex++;
                        if (numSearch.getAccountNumber() == accountNumber) {
                            foundIndex = accountIndex;
                            found = true;
                        }
                    }
                }
                // Output if searched account is found
                if (found) {
                    Account currentAccount = accounts.get(foundIndex - 1);
                    // Output account details
                    System.out.println("\nAccount found:");
                    System.out.println(currentAccount.toString());
                    // Ask user what they would like to do with the account
                    System.out.println("\nWhat would you like to make?\n[1] Withdrawal\n[2] Deposit");
                    int findChoice = sc.nextInt();
                    // Make a withdrawal
                    if (findChoice == 1) {
                        // Output current balance and ask user how much to withdrawal
                        System.out.println("\nCurrent Balance: $" + currentAccount.convert(currentAccount.getMoney()));
                        System.out.print("How much money would you like to withdraw?\n$");
                        double withdraw = sc.nextDouble();
                        long withdrawAmount = currentAccount.convert(withdraw);
                        // Check if withdrawal is possible and update funds
                        if (currentAccount.withdraw(withdrawAmount)) {
                            // If there is a transaction fee, output it to user
                            if (currentAccount.getTransactionFee() > 0) {
                                System.out.print("\nA transaction fee of $" + String.format("%.2f", (double) currentAccount.getTransactionFee() / 100) + " has been charged");
                            }
                            System.out.println("\nNew Balance: $" + currentAccount.convert(currentAccount.getMoney()));
                        } else {
                            System.out.println("Not enough funds to withdraw");
                        }
                    }
                    // Make a deposit
                    else if (findChoice == 2) {
                        // Ask user how much to deposit
                        System.out.print("\nHow much would you like to deposit?\n$");
                        // Make deposit and output previous and new balance
                        double deposit = sc.nextDouble();
                        long depositAmount = currentAccount.convert(deposit);
                        System.out.println("\nPrevious Balance: $" + currentAccount.convert(currentAccount.getMoney()));
                        currentAccount.deposit(depositAmount);
                        // If there is a transaction fee, output it to user
                        if (currentAccount.getTransactionFee() > 0) {
                            System.out.print("A transaction fee of $" + String.format("%.2f", (double) currentAccount.getTransactionFee() / 100) + " has been charged\n");
                        }
                        System.out.println("New Balance: $" + currentAccount.convert(currentAccount.getMoney()));
                    }
                }
                // No accounts were found, return to main menu
                else {
                    System.out.println("No accounts found");
                }
            }
            // Produce reports
            else if (choice == '3') {
                System.out.println("\n===============================\n" + ANSI_BLUE + "Welcome to the Report Generator" + ANSI_RESET + "\n===============================");
                // Ask what report they would like to generate
                System.out.println("\nWhat reports would you like to generate?\n[1] Total Money Invested\n[2] Number of Each Account");
                int findReport = sc.nextInt();
                long totalMoneyInvested = 0;
                // Find total money invested
                if (findReport == 1) {
                    // Loop through all accounts, calculate total money
                    for (Account account : accounts) {
                        totalMoneyInvested = totalMoneyInvested + account.getMoney();

                    }
                    // Output total money invested
                    System.out.println("\nTotal Money Invested: $" + String.format("%.2f", (double) totalMoneyInvested / 100));
                }
                // FInd number of each account
                else if (findReport == 2) {
                    // Initialize variables
                    int totalChequing = 0;
                    int totalSavings = 0;
                    int totalSeniorChild = 0;
                    // Loop through all accounts, increment total amount for each account type
                    for (Account account : accounts) {
                        if (account.getAccountType() == 1) {
                            totalChequing++;
                        } else if (account.getAccountType() == 2) {
                            totalSavings++;
                        } else {
                            totalSeniorChild++;
                        }
                    }
                    // Output number of each type accounts
                    System.out.println("\nNumber of Accounts Found: " + (totalChequing + totalSavings + totalSeniorChild));
                    System.out.println("\nChecking: " + totalChequing + "\nSavings: " + totalSavings + "\nSenior/Child: " + totalSeniorChild);
                }
            }
            // Monthly routines for next month
            else if (choice == '4') {
                System.out.print("\nMonthly routines for month " + month + " has been complete\n");
                // Pay interest to all accounts
                for (Account account : accounts) {
                    account.payInterest();
                    account.resetNumOfTransactions();
                }
                // Increment month by one
                month++;
            }
        }
        // Close scanner
        sc.close();
    }
}
