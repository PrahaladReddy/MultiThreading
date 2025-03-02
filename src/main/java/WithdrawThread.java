public class WithdrawThread extends Thread {
    private BankAccount account;
    private int withdrawAmount;
    private int iterations;

    public WithdrawThread(BankAccount account, int withdrawAmount, int iterations) {
        this.account = account;
        this.withdrawAmount = withdrawAmount;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for(int i = 0; i < iterations; i++) {
            account.withdraw(withdrawAmount);
            try{
                Thread.sleep(150);
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
