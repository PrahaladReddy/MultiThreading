public class DepositThread extends Thread {
    private BankAccount account;
    private int depositAmount;
    private int iterations;

    public DepositThread(BankAccount account, int depositAmount, int iterations) {
        this.account = account;
        this.depositAmount = depositAmount;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for(int i = 0; i < iterations; i++) {
            account.deposit(depositAmount);
            try{
                Thread.sleep(100);
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
