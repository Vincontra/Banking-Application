package domain;

public class Account {
    private String customerId;
    private String accountNumber;
    private Double balance;
    private String accountType;

    public Account(String customerId, String accountNumber, Double balance, String accountType) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }
}
