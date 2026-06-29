package service.impl;
import domain.Account;
import service.BankService;

import java.util.UUID;

public class BankServiceImpl implements BankService {

    @Override
    public String openAccount(String name, String email, String accountType) {
        String customerId= UUID.randomUUID().toString();

        // CHANGE LATER --> 10+1=AC11
        String accountNumber=UUID.randomUUID().toString();
        Account a=new Account(customerId,accountNumber,0.0,accountType);

        //Save the account data



        return null;
    }
}
