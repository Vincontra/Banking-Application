package app;

import domain.Account;
import domain.Transaction;
import service.BankService;
import service.impl.BankServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner v=new Scanner(System.in);
        BankService bankService=new BankServiceImpl();
        System.out.println("Welcome");
        boolean run=true;
        while (run) {
            System.out.println("""
                1) Open Account
                2) Deposit
                3) Withdraw
                4) Transfer
                5) Account Statement
                6) List Accounts
                7) Search Accounts by Customer Name
                0) Exit
            """
            );

            // preformated """ used just to print to console as i want

            System.out.print("CHOOSE: ");
            String choice = v.nextLine().trim();
            System.out.println("CHOICE: " + choice);

            switch (choice){
                case "1"->openAccount(v,bankService);
                case "2"->deposit(v,bankService);
                case "3"->withdraw(v,bankService);
                case "4"->transfer(v,bankService);
                case "5"->statements(v,bankService);
                case "6"->listAccounts(v,bankService);
                case "7"->searchAccounts(v,bankService);
                case "0"->run=false;

            }

        }






    }

    private static void openAccount(Scanner v,BankService bankService) {
        System.out.println("Customer Name");
        String name=v.nextLine().trim();
        System.out.println("Customer email");
        String email=v.nextLine().trim();
        System.out.println("Account Type (SAVINGS/CURRENT): ");
        String type=v.nextLine().trim();
        System.out.println("Initial Deposit (It is Optional)");
        String amountStr=v.nextLine().trim();
        if (amountStr.isBlank()){
            amountStr="0";
        }
        Double initial= Double.valueOf(amountStr);

        String accountNumber=bankService.openAccount(name,email,type);
        System.out.println("Account opened :"+accountNumber);
        if (initial>0){
            bankService.deposit(accountNumber,initial,"Initial Deposit");
        }
        // service provides main functionality


    }

    private static void deposit(Scanner v,BankService bankService) {
        System.out.println("Tell me your Account Number");
        String accountNumber=v.nextLine().trim();
        System.out.println("Tell me the Amount You want to deposit");
        Double amount=Double.valueOf(v.nextLine().trim());
        bankService.deposit(accountNumber,amount,"Deposit");
        System.out.println("Money Deposited");



    }

    private static void withdraw(Scanner v,BankService bankService) {
        System.out.println("Tell me your Account Number");
        String accountNumber=v.nextLine().trim();
        System.out.println("Tell me the Amount You want to withdraw");
        Double amount=Double.valueOf(v.nextLine().trim());
        bankService.withdraw(accountNumber,amount,"Withdrawl");
        System.out.println("Money Withdrawn");
    }

    private static void transfer(Scanner v,BankService bankService) {
        System.out.println("From Account Number");
        String FromAccountNumber=v.nextLine().trim();
        System.out.println("To Account Number");
        String ToAccountNumber=v.nextLine().trim();
        System.out.println("Amount You want to Transfer");
        Double amount=Double.valueOf(v.nextLine().trim());
        bankService.transfer(FromAccountNumber,ToAccountNumber,amount,"Transfer");
    }

    private static void statements(Scanner v,BankService bankService) {
        System.out.println("Account Number");
        String Account=v.nextLine().trim();
        List<Transaction>list=bankService.getStatement(Account);
        for (Transaction t:list){
            System.out.println(t.getTimestamp()+" | "+t.getType()+" | "+t.getAmount()+" | "+t.getNote()+" | "+t.getId());
        }
    }

    private static void listAccounts(Scanner v,BankService bankService) {
        // list of accounts
        for (Account account : bankService.listAccounts()) {
            System.out.println(account.getAccountNumber()+" | "+account.getAccountType()+" | "+account.getBalance());
        }
    }
    private static void searchAccounts(Scanner v,BankService bankService) {
        // I was planning to Search Accounts by Name
        // like for particular name how many accounts are there its list basically
        System.out.println("Customer name Please");
        String name=v.nextLine().trim();
        for (Account account : bankService.searchAccountsByCustomerName(name)) {
            System.out.println(account.getAccountNumber()+" | "+account.getAccountType()+" | "+account.getBalance());
        }
    }

}
