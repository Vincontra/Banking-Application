package service.impl;
import domain.Account;
import domain.Customer;
import domain.Transaction;
import domain.Type;
import exceptions.AccountNotFoundException;
import exceptions.DuplicateAccException;
import exceptions.InsufficientBalException;
import exceptions.ValidationException;
import repository.AccountRepository;
import repository.CustomerRepository;
import repository.TransactionRepository;
import service.BankService;
import util.Validation;


import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {

    private final AccountRepository accountRepository=new AccountRepository();
    private final TransactionRepository transactionRepository=new TransactionRepository();
    private final CustomerRepository customerRepository=new CustomerRepository();

    // validation part

    private final Validation<String>validationName=new Validation<String>() {
        @Override
        public void validate(String name) throws ValidationException {
            if (name==null||name.isBlank()){
                throw new ValidationException("Name is required");
            }
        }
    };
    private final Validation<String>validationEmail=new Validation<String>() {
        @Override
        public void validate(String email) throws ValidationException {
            if (email==null||email.isBlank()||!email.contains("@")){
                throw new ValidationException("Enter Proper Email");
            }
        }
    };

    private final Validation<String>validationType=new Validation<String>() {
        @Override
        public void validate(String type) throws ValidationException {
            if (type==null||type.isBlank()||!(type.equalsIgnoreCase("SAVINGS")||type.equalsIgnoreCase("CURRENT"))){
                throw new ValidationException("Enter Proper Type of Account either SAVINGS/CURRENT");
            }

        }
    };
   private final Validation<Double>validationAmount=new Validation<Double>() {
       @Override
       public void validate(Double amt) throws ValidationException {
           if (amt==null||amt<0){
               throw new ValidationException("Amount should be Positive only");
           }

       }
   };


    @Override
    public String openAccount(String name, String email, String accountType) {
        validationName.validate(name);
        validationEmail.validate(email);
        validationType.validate(accountType);

        String customerId= UUID.randomUUID().toString();

        // We need to create new Customer as well
        Customer customer = new Customer(customerId,name,email);
        customerRepository.save(customer);
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
        validationAmount.validate(amount);
        Account account=accountRepository.
                findByNumber(accountNumber).
                orElseThrow(()->new AccountNotFoundException("Account Not Found"));

        account.setBalance(account.getBalance()+amount);

        Transaction transaction=
                new Transaction(accountNumber,amount,UUID.randomUUID().toString(),note, LocalDateTime.now(), Type.DEPOSIT);

        transactionRepository.add(transaction);

    }

    @Override
    public void withdraw(String accountNumber, Double amount, String note) {
        validationAmount.validate(amount);
        Account account=accountRepository.
                findByNumber(accountNumber).
                orElseThrow(()->new AccountNotFoundException("Account Not Found"));

        // we should check it first ki bhai is there enough money to withdraw or not

        if (account.getBalance().compareTo(amount)<0){
            throw new InsufficientBalException("Insufficient Balance");
        }

        account.setBalance(account.getBalance()-amount);

        Transaction transaction=
                new Transaction(accountNumber,amount,UUID.randomUUID().toString(),note, LocalDateTime.now(), Type.WITHDRAW);

        transactionRepository.add(transaction);

    }

    @Override
    public void transfer(String fromAccountNumber, String toAccountNumber, Double amount,String transfer) {
        validationAmount.validate(amount);
        if (fromAccountNumber.equals(toAccountNumber)){
            throw new DuplicateAccException("Can not tranfer to your own account");
        }


//        // transfer is nothing just but withdraw from fromAcc
//        // deposit to toAcc
//        // along with that we need to make sure that the Transactions should also be there
//        // since we have already wrote this Transactions in both withdraw and deposit methods
//
//        withdraw(fromAccountNumber,amount,transfer);
//        deposit(toAccountNumber,amount,transfer);
        // this is also correct but i want the type as Tranferin and out
        // as we are tranfing but i have not did that like how to pass since transactions
        // are update in these methods
        // so no worries need to do that in same way just need to write it
        // all over again here




        //withdraw()
        Account account=accountRepository.
                findByNumber(fromAccountNumber).
                orElseThrow(()->new AccountNotFoundException("Account Not Found"));
        if (account.getBalance().compareTo(amount)<0){
            throw new InsufficientBalException("Insufficient Balance");
        }
        account.setBalance(account.getBalance()-amount);
        Transaction transaction=
                new Transaction(fromAccountNumber,amount,UUID.randomUUID().toString(),transfer, LocalDateTime.now(), Type.TRANSFER_OUT);
        transactionRepository.add(transaction);

        //deposit()
        Account account1=accountRepository.
                findByNumber(toAccountNumber).
                orElseThrow(()->new AccountNotFoundException("Account Not Found"));

        account1.setBalance(account1.getBalance()+amount);

        Transaction transaction1=
                new Transaction(toAccountNumber,amount,UUID.randomUUID().toString(),transfer, LocalDateTime.now(), Type.TRANSFER_IN);

        transactionRepository.add(transaction1);
    }

    @Override
    public List<Transaction> getStatement(String account) {
        return transactionRepository.
                findByAccount(account).stream()
                .sorted(Comparator.comparing(Transaction::getTimestamp))
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> searchAccountsByCustomerName(String name) {
        if (name==null){
            return new ArrayList<>();
        }
        String query=name.toLowerCase();
        // just doing that to ensure the exact match
        // like if somebody search like ViNay
        // so this name would not be in database so
        // it is better to have it in small case

        List<Account>result=new ArrayList<>();
        for (Customer c:customerRepository.findAll()){
            if (c.getName().toLowerCase().contains(query)){
                result.addAll(accountRepository.findByCustomerId(c.getId()));
            }
        }
        result.sort(Comparator.comparing(Account::getAccountNumber));
        return result;

    }

}
