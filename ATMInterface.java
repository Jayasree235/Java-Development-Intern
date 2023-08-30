import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String pin;
    private double balance;

    public BankAccount(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean validatePin(String inputPin) {
        return pin.equals(inputPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Updated balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Updated balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, BankAccount> accounts = new HashMap<>();

        // Manually enter account details
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();

        accounts.put(accountNumber, new BankAccount(accountNumber, pin, balance));

        System.out.println("Account successfully created.");

        while (true) {
            System.out.print("Enter account number: ");
            String inputAccountNumber = scanner.next();

            System.out.print("Enter PIN: ");
            String inputPin = scanner.next();

            BankAccount account = accounts.getOrDefault(inputAccountNumber, null);

            if (account != null && account.validatePin(inputPin)) {
                System.out.println("Authentication successful.");
                while (true) {
                    System.out.println("1. Check Balance");
                    System.out.println("2. Deposit");
                    System.out.println("3. Withdraw");
                    System.out.println("4. Exit");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.println("Your balance: " + account.getBalance());
                            break;
                        case 2:
                            System.out.print("Enter deposit amount: ");
                            double depositAmount = scanner.nextDouble();
                            account.deposit(depositAmount);
                            break;
                        case 3:
                            System.out.print("Enter withdrawal amount: ");
                            double withdrawalAmount = scanner.nextDouble();
                            account.withdraw(withdrawalAmount);
                            break;
                        case 4:
                            System.out.println("Exiting...");
                            scanner.close();
                            return;
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                }
            } else {
                System.out.println("Authentication failed. Please check your account number and PIN.");
            }
        }
    }
}
