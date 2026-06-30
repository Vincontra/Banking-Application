package repository;

import domain.Transaction;

import java.util.*;

public class TransactionRepository {
    private final Map<String, List<Transaction>>txByAccount=new HashMap<>();
    // key account number
    // value would be  all the transaction that account has made

    public void add(Transaction transaction) {
//        txByAccount.computeIfAbsent(transaction.getAccountNumber(),);

        String key=transaction.getAccountNumber();
        if (txByAccount.containsKey(key)){
            txByAccount.get(key).add(transaction);
        }else{
            txByAccount.put(key, new ArrayList<>());
            txByAccount.get(key).add(transaction);
        }
    }

    public List<Transaction> findByAccount(String account) {
        return new ArrayList<>(txByAccount.getOrDefault(account, Collections.emptyList()));
    }
}
