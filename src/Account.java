/**
 * Account Class
 */

public class Account {
    // Attributes
    protected final String accountName;
    protected final int accountNum;
    protected long money;
    protected int accountType;
    protected int transactionFee;
    protected int numOfTransactions;

    /**
     * Constructor
     *
     * @param accountName the name of the account
     * @param accountNum  account number
     */
    public Account(String accountName, int accountNum) {
        this.accountName = accountName;
        this.accountNum = accountNum + 1;
        this.money = 0;
        this.transactionFee = 0;
    }

    /**
     * toString function
     *
     * @return Account name, account number and account balance
     */
    public String toString() {
        return "Name: " + this.accountName + " | Number: " + String.format("%06d", this.accountNum) + " | Balance: $" + String.format("%.2f", (float) this.money / 100);
    }

    /**
     * Return the name of account
     *
     * @return account name
     */
    public String getName() {
        return this.accountName;
    }

    /**
     * Return the account number
     *
     * @return account number
     */
    public int getAccountNumber() {
        return this.accountNum;
    }

    /**
     * Return the account type
     *
     * @return account type
     */
    public int getAccountType() {
        return this.accountType;
    }

    /**
     * Check if a withdrawal can occur, if so, withdraw and apply applicable fees
     *
     * @param withdrawAmount of money to withdraw from account
     * @return can money be withdrawn
     */
    public boolean withdraw(long withdrawAmount) {
        boolean transactionResult = false;
        if (this.money - withdrawAmount >= 0) {
            this.money = this.money - withdrawAmount;
            transactionResult = true;
            this.numOfTransactions++;
        }
        return transactionResult;
    }

    /**
     * Return the amount of money account has
     *
     * @return money
     */
    public long getMoney() {
        return this.money;
    }

    /**
     * Get transaction fee
     *
     * @return transaction fee
     */
    public int getTransactionFee() {
        return this.transactionFee;
    }

    /**
     * Deposit money into account
     *
     * @param depositAmount amount
     */
    public void deposit(long depositAmount) {
        this.money = this.money + depositAmount;
        this.numOfTransactions++;
    }

    /**
     * Reset number of transactions
     */
    public void resetNumOfTransactions() {
        this.numOfTransactions = 0;
    }

    /**
     * Pay interest declared, but an empty overrideable method
     */
    public void payInterest() {
    }

    /**
     * Convert money into dollars
     *
     * @param moneyInCents in cents
     * @return money in dollars
     */
    public String convert(long moneyInCents) {
        return String.format("%.2f", (double) moneyInCents / 100);
    }

    /**
     * Convert money into cents
     *
     * @param moneyInDollars in dollars
     * @return money in cents
     */
    public long convert(double moneyInDollars) {
        moneyInDollars = moneyInDollars * 100;
        moneyInDollars = Math.round(moneyInDollars);
        return (long) moneyInDollars;
    }
}