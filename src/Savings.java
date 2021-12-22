/**
 * Savings
 */


public class Savings extends Account {
    // Attributes
    protected static double interestRate;

    /**
     * Constructor
     */
    public Savings(String accountName, int accountNum) {
        super(accountName, accountNum);
        this.accountType = 2;
    }

    /**
     * Set interest rate
     *
     * @param interestRate rate of interest
     */
    public static void setInterestRate(double interestRate) {
        Savings.interestRate = interestRate + 1;
    }

    /**
     * toString function
     *
     * @return account name, account number and account balance
     */
    public String toString() {
        return super.toString() + " | Type: Savings";
    }

    /**
     * Check if a withdraw can occur, if so, withdraw and apply applicable fees
     *
     * @param withdrawAmount of money to withdraw from account
     * @return can money be withdrawn
     */
    public boolean withdraw(long withdrawAmount) {
        this.transactionFee = 0;
        boolean transactionResult = false;
        if (this.numOfTransactions >= 5) {
            this.transactionFee = 200;
        }
        if (this.money - (withdrawAmount + this.transactionFee) >= 0) {
            this.numOfTransactions++;
            transactionResult = true;
            this.money = this.money - (withdrawAmount + this.transactionFee);
        }
        return transactionResult;
    }

    /**
     * Deposit money into account
     *
     * @param depositAmount amount to deposit
     */
    public void deposit(long depositAmount) {
        this.transactionFee = 0;
        if (this.numOfTransactions >= 5) {
            this.transactionFee = 200;
        }
        this.money = (this.money - this.transactionFee) + depositAmount;
        this.numOfTransactions++;
    }

    /**
     * Pay interest to savings account
     */
    public void payInterest() {
        this.money = Math.round(this.money * Savings.interestRate);
    }
}