package service.impl;
import domain.Account;
import domain.Transaction;
import domain.Type;
import repository.AccountRepository;
import repository.TransactionRepository;
import service.BankService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {

    private final AccountRepository accountRepository=new AccountRepository();
    private final TransactionRepository transactionRepository=new TransactionRepository();


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
    @Override
    public void deposit(String accountNumber, Double amount,String note) {
        Account account=accountRepository.
                findByNumber(accountNumber).
                orElseThrow(()->new RuntimeException("Account Not Found"));

        account.setBalance(account.getBalance()+amount);

        Transaction transaction=
                new Transaction(accountNumber,amount,UUID.randomUUID().toString(),note, LocalDateTime.now(), Type.DEPOSIT);

        transactionRepository.add(transaction);

    }
}
