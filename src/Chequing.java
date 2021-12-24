/**
 * Chequing Class
 */

public class Chequing extends Account {
    // Attributes
    protected static double interestRate;

    /**
     * Constructor
     */
    public Chequing(String accountName, int accountNum) {
        super(accountName, accountNum);
        this.accountType = 1;
    }

    /**
     * Set interest rate
     *
     * @param interestRate rate of interest
     */
    public static void setInterestRate(double interestRate) {
        Chequing.interestRate = interestRate + 1;
    }

    /**
     * toString function
     *
     * @return Account name, account number and account balance
     */
    public String toString() {
        return super.toString() + " | Type: Chequing";
    }

    /**
     * Check if a withdrawl can occur, if so, withdraw and apply applicable fees
     *
     * @param withdrawAmount of money to withdraw from account
     * @return can money be withdrawn
     */
    @Override
    public boolean withdraw(long withdrawAmount) {
        boolean transactionResult = false;
        this.transactionFee = 0;
        if (this.money < 100000) {
            this.transactionFee = 150;
        }
        if (this.money - (withdrawAmount + this.transactionFee) >= 0) {
            this.money = this.money - (withdrawAmount + this.transactionFee);
            this.numOfTransactions++;
            transactionResult = true;
        }
        return transactionResult;
    }

    /**
     * Deposit money into account
     *
     * @param depositAmount amount
     */
    @Override
    public void deposit(long depositAmount) {
        this.transactionFee = 0;
        if (this.money < 100000) {
            this.transactionFee = 150;
        }
        this.money = (this.money - this.transactionFee) + depositAmount;
        this.numOfTransactions++;
    }

    /**
     * Pay interest to chequing account
     */
    @Override
    public void payInterest() {
        this.money = Math.round(this.money * Chequing.interestRate);
    }
}