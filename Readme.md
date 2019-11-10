This system will be Initialised with an input file in CSV format containing a list of transaction records.

Once initialised, then it will report the total number of transactions and the average transaction value for a specific merchant in a specific date range.

To Setup Project In your local :
```$xslt
   git clone https://github.com/lekkalraja/transaction-analyser.git
   import as existing maven project in any IDE(IntelliJ/Eclipse)
```

To Build a Jar    : ```mvn package```

To Run test cases : ```mvn test```

Should Provide command line arguments below :
```$xslt
  CSV File Path : /transactionanalyser/src/main/resources/transactions.csv
  From Date     : DD/MM/YYYY HH:MM:SS (ex: 20/08/2018 12:00:00)
  To Date       : DD/MM/YYYY HH:MM:SS (ex: 20/09/2018 12:00:00)
  Merchant      : Kwik-E-Mart
```

To Execute the App:

```$xslt
 java cp transactionanalyser/target/transaction-analyser-1.0.0.jar <CSV_FILE> <From_Date> <To_Date> <Merchant>
 
 ex : java cp /transactionanalyser/target/transaction-analyser-1.0.0.jar /transactionanalyser/src/main/resources/transactions.csv 20/08/2018 12:00:00 20/08/2018 13:00:00 Kwik-E-Mart
```