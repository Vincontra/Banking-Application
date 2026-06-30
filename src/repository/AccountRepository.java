package repository;

import domain.Account;
import domain.Customer;

import java.util.*;

public class AccountRepository {
    // account details will be saved by AccountRepo file means this file

    private final Map<String, Account>accountByNumber=new HashMap<>();
    public  void save(Account account) {
        accountByNumber.put(account.getAccountNumber(),account);
    }
    public List<Account> findAll(){
        return new ArrayList<>(accountByNumber.values());
    }

    public Optional<Account> findByNumber(String accountNumber) {
        return Optional.ofNullable(accountByNumber.get(accountNumber));
        // Optional as it might possible that the user may enter wrong
        // acc number so it will not be in hashmap then it may return null
        // to handle that null i used optional
    }

    public List<Account> findByCustomerId(String customerId){
        List<Account>result=new ArrayList<>();
        for (Account a:accountByNumber.values()){
            if (a.getCustomerId().equals(customerId)){
                result.add(a);
            }
        }
        return result;
    }
}
