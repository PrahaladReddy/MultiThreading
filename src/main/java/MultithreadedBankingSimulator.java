import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadedBankingSimulator {
    public static void main(String[] args) {
        System.out.println("Part1: Manual Threads with wait/notify");

        BankAccount account1 = new BankAccount(100);

        DepositThread dt = new DepositThread(account1, 50, 5);
        WithdrawThread wt = new WithdrawThread(account1, 30, 10);

        dt.setName("DepositThread");
        wt.setName("WithdrawThread");

        dt.start();
        wt.start();

        try {
            dt.join();
            wt.join();
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final balance (Part 1): " + account1.getBalance());

        System.out.println("Final balance (Part 2): ExecutorService for Concurrent Transactions");
        BankAccount account2 = new BankAccount(200);

        int numTasks = 20;
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CountDownLatch latch = new CountDownLatch(numTasks);

        for(int i = 0; i < numTasks; i++) {
            executor.execute(() -> {
                try{
                    new TransactionTask(account2).run();
                } finally {
                    latch.countDown();
                }
            });
        }

        try{
            latch.await();
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        executor.shutdown();
        System.out.println("Final balance (Part 2): " + account2.getBalance());
    }
}
