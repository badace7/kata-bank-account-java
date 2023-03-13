import java.time.LocalDate;

public class Account {
    private int balance;
    private boolean authorization;
    private int overdraft;
    private String lastOperation;

    public Account(int balance, boolean authorization, int overdraft) {
        this.balance = balance;
        this.authorization = authorization;
        this.overdraft = overdraft;
    }

    public void deposit(int amount) {
        this.balance += amount;
        this.lastOperation = "+" + amount;
    }

    public void withdraw(int amount) throws Exception {
        if (isNotAuthorizedToWithdraw(amount)) {
            throw new Exception("Cannot withdraw, balance insufficient");
        }
        this.balance -= amount;
        this.lastOperation = "-" + amount;
    }

    public int getBalance() {
        return this.balance;
    }

    public String printStatement() {
        return LocalDate.now() + " " + this.lastOperation + " " + this.getBalance();
    }

    private boolean isNotAuthorizedToWithdraw(int amount) {
        return cannotWithdrawWithoutOverdraft(amount)
                || cannotWithdrawWithOverdraft(amount);
    }

    private boolean cannotWithdrawWithoutOverdraft(int amount) {
        return (amount > this.balance && !this.authorization);
    }

    private boolean cannotWithdrawWithOverdraft(int amount) {
        return amount > (this.balance + this.overdraft);
    }
}
