package com.hoolah.challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TransactionAnalyser {

    private final String CSV_SPLIT_BY = ",";
    private final String REVERSAL = "REVERSAL";

    public Statistics analyseTransactions(String csvFile, Instant fromDate, Instant toDate, String merchant, boolean hasHeader){
        List<Transaction> transactions = loadTransactions(csvFile, hasHeader);
        return calculateStatisticInformation(transactions, fromDate, toDate, merchant);
    }



    private List<Transaction> loadTransactions(String csvFile, boolean hasHeader){
        List<Transaction> transactionList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if(hasHeader){
                    hasHeader = false;
                    continue; //skip header to read
                }
                String[] transactionLine = line.split(CSV_SPLIT_BY);
                Transaction transaction = getTransaction(transactionLine);
                transactionList.add(transaction);
            }
        } catch (IOException e) {
            System.err.println("Something went wrong while loading transactions "+e.getMessage());
        }
        return transactionList;
    }

    private Statistics calculateStatisticInformation(List<Transaction> transactions, Instant fromDate, Instant toDate, String merchant){
        Supplier<Stream<Transaction>> validTransactionStreamSupplier =() -> transactions.parallelStream()
                .filter(item -> this.isItValidTransaction(item, fromDate, toDate, merchant));
        long numberOfTransactions = validTransactionStreamSupplier.get().count();
        OptionalDouble sumOfTransactions = validTransactionStreamSupplier.get().mapToDouble(item -> item.getAmount()).average();
        Statistics statistics = new Statistics();
        statistics.setNumberOfTransactions(numberOfTransactions);
        statistics.setAverageTransactions(sumOfTransactions.orElse(0));
        return statistics;
    }

    private Boolean isItValidTransaction(Transaction transaction, Instant fromDate, Instant toDate, String merchant){
        return transaction.getDate().isAfter(fromDate) &&
                transaction.getDate().isBefore(toDate) &&
                transaction.getMerchant().trim().equalsIgnoreCase(merchant) &&
                !transaction.getType().trim().equals(REVERSAL);
    }

    private Transaction getTransaction(String[] transactionLine) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionLine[0]);
        transaction.setDate(AppUtil.getInstant(transactionLine[1]));
        transaction.setAmount(Float.parseFloat(transactionLine[2]));
        transaction.setMerchant(transactionLine[3]);
        transaction.setType(transactionLine[4]);
        transaction.setRelatedTransaction(transactionLine.length == 5 ? "" : transactionLine[5]);
        return transaction;
    }
}
