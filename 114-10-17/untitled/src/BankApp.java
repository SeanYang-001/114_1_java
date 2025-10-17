

// java


import java.util.*;

public class BankApp {
    static final Scanner scanner = new Scanner(System.in);
    static final String USERNAME = "user";
    static final String PASSWORD = "1234";

    static final Map<String, Double> toTWD = Map.of(
            "JPY", 0.23,
            "TWD", 1.0,
            "USD", 30.5,
            "EUR", 33.0
    );

    static final Map<String, Double> balances = new HashMap<>();

    public static void main(String[] args) {
        balances.put("TWD", 0.0);
        balances.put("JPY", 0.0);
        balances.put("USD", 0.0);
        balances.put("EUR", 0.0);

        if (!login()) {
            System.out.println("登入失敗！");
            return;
        }

        while (true) {
            showMenu();
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            switch (line) {
                case "1" -> deposit();
                case "2" -> withdraw();
                case "3" -> convertCurrency();
                case "4" -> showBalances();
                case "0" -> {
                    System.out.println("已登出，謝謝使用！");
                    return;
                }
                default -> System.out.println("無效選項");
            }
        }
    }

    static boolean login() {
        System.out.print("請輸入帳號: ");
        String user = scanner.nextLine().trim();
        System.out.print("請輸入密碼: ");
        String pass = scanner.nextLine().trim();
        return user.equals(USERNAME) && pass.equals(PASSWORD);
    }

    static void showMenu() {
        System.out.println("\n=== 銀行系統選單 ===");
        System.out.println("1. 存錢");
        System.out.println("2. 領錢");
        System.out.println("3. 貨幣換算");
        System.out.println("4. 查詢餘額");
        System.out.println("0. 登出");
        System.out.print("請選擇操作: ");
    }

    static void deposit() {
        String cur = chooseCurrency();
        System.out.print("請輸入存款金額: ");
        try {
            double amt = Double.parseDouble(scanner.nextLine().trim());
            if (amt > 0) {
                balances.put(cur, balances.get(cur) + amt);
                System.out.printf("已成功存入 %.2f %s%n", amt, cur);
            } else {
                System.out.println("金額需大於 0");
            }
        } catch (NumberFormatException e) {
            System.out.println("金額格式錯誤");
        }
    }

    static void withdraw() {
        String cur = chooseCurrency();
        System.out.print("請輸入提款金額: ");
        try {
            double amt = Double.parseDouble(scanner.nextLine().trim());
            if (amt > 0 && amt <= balances.get(cur)) {
                balances.put(cur, balances.get(cur) - amt);
                System.out.printf("已成功提取 %.2f %s%n", amt, cur);
            } else {
                System.out.println("金額不足或輸入錯誤");
            }
        } catch (NumberFormatException e) {
            System.out.println("金額格式錯誤");
        }
    }

    static void convertCurrency() {
        System.out.println("請選擇來源貨幣:");
        String from = chooseCurrency();
        System.out.println("請選擇目標貨幣:");
        String to = chooseCurrency();
        System.out.print("請輸入換算金額: ");
        try {
            double amt = Double.parseDouble(scanner.nextLine().trim());
            if (amt <= 0 || amt > balances.get(from)) {
                System.out.println("金額無效或餘額不足！");
                return;
            }
            // from -> TWD -> to  等價於 amt * toTWD[from] / toTWD[to]
            double converted = amt * toTWD.get(from) / toTWD.get(to);
            balances.put(from, balances.get(from) - amt);
            balances.put(to, balances.get(to) + converted);
            System.out.printf("成功將 %.2f %s 轉換為 %.2f %s%n", amt, from, converted, to);
        } catch (NumberFormatException e) {
            System.out.println("金額格式錯誤");
        }
    }

    static void showBalances() {
        System.out.println("\n--- 帳戶餘額 ---");
        for (var e : balances.entrySet()) {
            System.out.printf("%s: %.2f%n", e.getKey(), e.getValue());
        }
    }

    // java
    public class Account {
        private final String accountNumber;
        private double balance;

        public Account(String accountNumber, double balance) {
            if (accountNumber == null || accountNumber.trim().isEmpty()) {
                throw new IllegalArgumentException("帳號不可為空");
            }
            this.accountNumber = accountNumber;
            this.balance = Math.max(0.0, balance);
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public double getBalance() {
            return balance;
        }

    }

