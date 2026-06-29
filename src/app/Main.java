package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner v=new Scanner(System.in);
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
                case "0"->run=false;

            }

        }






    }

}
