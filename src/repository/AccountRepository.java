package repository;

import domain.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountRepository {
    // account details will be saved by AccountRepo file means this file

    private final Map<String, Account>accountByNumber=new HashMap<>();
    public  void save(Account account) {
        accountByNumber.put(account.getAccountNumber(),account);
    }
    public List<Account> findAll(){
        return new ArrayList<>(accountByNumber.values());
    }
}
