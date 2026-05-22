package models;

public class CheckingAccount extends BankAccount {
    private int overdraftLimit;

    public CheckingAccount(String accountId, String ownerName, double balance, int overdraftLimit) {
        super(accountId, ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public boolean withdraw(double amount, String type, String description) {
        double available = getBalance() + overdraftLimit;
        if (amount > 0 && amount <= available) {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawn " + amount + " $. New balance: " + getBalance() + " $");
            logTransaction(type, amount, description);
            return true;
        } else {
            System.out.println("Withdrawal failed: insufficient funds (including overdraft)");
            return false;
        }
    }

    @Override
    public boolean withdraw(double amount) {
        return withdraw(amount, "Withdrawal", "Withdraw funds");
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }
}
