import java.util.Random;

public class TransactionTask implements Runnable {
    private BankAccount account;
    private Random random = new Random();

    public TransactionTask(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        boolean doDeposit = random.nextBoolean();
        int amount = random.nextInt(100) + 1;

        if(doDeposit) {
            account.deposit(amount);
            System.out.println(Thread.currentThread().getName() + " deposited " + amount);
        } else {
            account.withdraw(amount);
            System.out.println(Thread.currentThread().getName() + " withdrawn " + amount);
        }

        try {
            Thread.sleep(50);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
