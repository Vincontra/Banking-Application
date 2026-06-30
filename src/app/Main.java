package app;

import domain.Account;
import service.BankService;
import service.impl.BankServiceImpl;

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
                case "2"->deposit(v);
                case "3"->withdraw(v);
                case "4"->transfer(v);
                case "5"->statements(v);
                case "6"->listAccounts(v,bankService);
                case "7"->searchAccounts(v);
                case "0"->run=false;

            }

        }






    }

    private static void openAccount(Scanner v,BankService bankService) {
        System.out.println("Custmer Name");
        String name=v.nextLine().trim();
        System.out.println("Custmer email");
        String email=v.nextLine().trim();
        System.out.println("Account Type (SAVINGS/CURRENT): ");
        String type=v.nextLine().trim();
        System.out.println("Initial Deposit (It is Optional)");
        String amountStr=v.nextLine().trim();
        Double initial= Double.valueOf(amountStr);

        bankService.openAccount(name,email,type);

        // service provides main functionality







    }

    private static void deposit(Scanner v) {
    }

    private static void withdraw(Scanner v) {
    }

    private static void transfer(Scanner v) {
    }

    private static void statements(Scanner v) {
    }

    private static void listAccounts(Scanner v,BankService bankService) {
        // list of accounts
        for (Account account : bankService.listAccounts()) {
            System.out.println(account.getAccountNumber()+" | "+account.getAccountType()+" | "+account.getBalance());
        }
    }
    private static void searchAccounts(Scanner v) {
    }

}
