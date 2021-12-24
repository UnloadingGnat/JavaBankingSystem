/**
 * Senior Child Class
 */

public class SeniorChild extends Account {
    // Attributes
    protected static double interestRate;

    /**
     * Constructor
     */
    public SeniorChild(String accountName, int accountNum) {
        super(accountName, accountNum);
        this.accountType = 3;
    }

    /**
     * Set interest rate
     *
     * @param interestRate interest
     */
    public static void setInterestRate(double interestRate) {
        SeniorChild.interestRate = interestRate + 1;
    }

    /**
     * toString function
     *
     * @return Account name, account number and account balance
     */
    public String toString() {
        return super.toString() + " | Type: Senior/Child";
    }

    /**
     * Pay interest to senior/child account
     */
    @Override
    public void payInterest() {
        this.money = Math.round(this.money * SeniorChild.interestRate);
    }
}