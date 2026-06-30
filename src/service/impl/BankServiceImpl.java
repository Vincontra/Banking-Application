package service.impl;
import domain.Account;
import repository.AccountRepository;
import service.BankService;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {

    AccountRepository accountRepository=new AccountRepository();

    @Override
    public String openAccount(String name, String email, String accountType) {
        String customerId= UUID.randomUUID().toString();

        // CHANGE LATER --> 10+1=AC11

        String accountNumber = getAccountNumber();
        // formating like after AC there are 6 digits
        Account account=new Account(customerId,accountNumber,0.0,accountType);
        //Save the account data using accountRepo class
        accountRepository.save(account);
        return accountNumber;
    }

    private String getAccountNumber() {
        int size=accountRepository.findAll().size()+1;
        String accountNumber=String.format("AC%06d",size);
        return accountNumber;
    }

    @Override
    public List<Account> listAccounts() {
        return accountRepository.findAll().stream()
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .collect(Collectors.toList());
    }
}
