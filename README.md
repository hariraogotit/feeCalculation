# feeCalculation

## Highlevel overview
1. Open feeCalculation-applicationOverview.xml in https://www.draw.io/ to get an overview class diagram
2. LoadFeeCalculator.java is the entry point which takes in input (all input transaction files are dropped in to this directory),
   error (erroneous records are wrtten to this directory) and summaryReport (summary reports are written to this directory) directories. 
3. TransactionProcessFactoryHandler.java is called which returns the csv handler. This factory should be altered to take in other 
   handlers like json, xml etc if this app needs be extended to support json, xml etc file types.
4. All common code that are not specific to file format but that are responsible for processing records are in AbstractTransactionProcessHandler.java
5. TransactionProcessCsvHandlerImpl reads from CSV file. They are then converted to RawTransaction which is then validated. If there are any errors then RawTransaction is written to error directory else they are converted to Transaction object. 
6. FeeCalculatorServiceImpl.java is used to apply the intra day charge fee and nominal charge fee to the Transaction Object.
7. Summary report is then written to summaryReport directory 

## How to run the jar
1. Run mvn clean install. 
2. Run the uber jar
    2.1 Navigate to feeCalculator/target
    2.2 Create three directories input, error and summaryReport
    2.3 Run :- java -jar calculator-1.0-SNAPSHOT.jar input/ error/ summaryReport/
3. To check the log navigate to feeCalculator/target/log

## Improvements if given more time
1. Write more Junit tests to have good code coverage. Use Mockito for mocking the services / objects
2. Modify the code to run handlers in parallel there by reducing the process time. 
3. Add more logging that will help production support team.
4. Present a better intuitive class diagram. 

## Assumptions
1. Validated only Transaction type and Priority flag as I know the valid values. I would like to validate all the fields if I know the possible range / allowed values for them. 
2. If there is bad data I write that record to the error directory. There is a possibility that it has a pair and needs $10 to be charged as part of intraday transactions but I assume I do not have to write it's pair as well to error directory. 

## Supporting other file types
1. Create a new handler and extend AbstractTransactionProcessHandler
2. Re-use validate and calulate fee code in AbstractTransactionProcessHandler
3. Add new file type in FileType.java
4. Amend TransactionProcessFactoryHandler to return the handler for the new file type.
5. Code used to read the records from new file type, writing to erroneous and summary report file should be in the new handler. 
