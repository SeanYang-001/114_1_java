// java
public class AccountTest {
    private static int customersCount = 0;
    public static void main(String[] args) {
        Account[] customers = new Account[10];
        Account acc1 = new Account("A001", 5000.0);
        customers[0] = acc1;
        Account acc2 = new Account("A002", -1000.0);
        customers[1] = acc2;
        Account acc3 = new Account("A003", 3000.0);
        customers[2] = acc3;

        System.out.println("\n所有客戶資訊")
    }

    public static void addCustomers(Account[] customers, Account newAccount) {
        if (customersCount < customers.length) {
            customers[customersCount] = newAccount;
            customersCount++;
            System.out.println("Account added successfully: " + newAccount.getAccountNumber());
            return;
        }
        System.out.println("No available slot to add new account.");
    }

    public static void printCustomers(Account[] customers) {
        for (int i = 0; i < customersCount; i++) {
            Account acc = customers[i];
            System.out.println("Account Number: " + acc.getAccountNumber() + ", Balance: " + acc.getBalance());
        }
    }