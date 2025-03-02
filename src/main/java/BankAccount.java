public class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " Deposited: " + amount + ", to the bank" + balance);
        notifyAll();
    }

    public synchronized void withdraw(int amount) {
        while(balance < amount) {
        System.out.println(Thread.currentThread().getName() + " waiting to Withdraw: " + amount + " balance: " + balance);
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " interrupted while waiting for deposit");
            }
        }
        balance -= amount;
        System.out.println(Thread.currentThread().getName() + " deposited: " + amount + ", to the bank" + balance);
    }

    public synchronized int getBalance() {
        return balance;
    }
}