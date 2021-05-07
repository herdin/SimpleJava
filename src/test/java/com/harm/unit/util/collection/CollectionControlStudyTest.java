package com.harm.unit.util.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CollectionControlStudyTest {
    Logger logger = LoggerFactory.getLogger(CollectionControlStudyTest.class);

    List<Transaction> transactionList;

    @BeforeEach
    public void initTransactionList() {
        transactionList = new ArrayList<>();
        for(int i=0; i<10; i++) {
            transactionList.add(new Transaction(i, i+1, "tag-"+i));
        }
    }

    @Test
    public void list_removeIf() {
        logger.debug("before remove if, {}", transactionList);
        transactionList.removeIf(transaction -> transaction.getAmount() > 5);
        logger.debug("after remove if, {}", transactionList);
        org.junit.jupiter.api.Assertions.assertEquals(5, transactionList.size());
    }

    @Test
    public void list_replaceAll() {
        logger.debug("before replace all, {}", transactionList);
        transactionList.replaceAll(transaction -> new Transaction(transaction.getNo(), transaction.getAmount(), "mod-" + transaction.getTag()));
        logger.debug("after replace all, {}", transactionList);
        org.junit.jupiter.api.Assertions.assertEquals("mod-tag-0", transactionList.get(0).getTag());
    }

    @Test
    public void list_sort() {
        logger.debug("before sort, {}", transactionList);
        transactionList.sort(Comparator.comparing(Transaction::getAmount).reversed());
        logger.debug("after sort, {}", transactionList);
        org.junit.jupiter.api.Assertions.assertEquals("tag-9", transactionList.get(0).getTag());
    }

}

class Transaction {
    int no;
    int amount;
    String tag;

    public Transaction(int no, int amount, String tag) {
        this.no = no;
        this.amount = amount;
        this.tag = tag;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "no=" + no +
                ", amount=" + amount +
                ", tag='" + tag + '\'' +
                '}';
    }
}