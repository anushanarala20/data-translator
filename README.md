# data-translator



**Specifications**:
Read input file from vendorData.txt and manipulate the content using config1DataExtractor.txt and config2DataExtractor.txt,
and write final data to translator.txt file.

**Execution** :
The main class DataTranslatorExample.java must be executed which picks all the specified input files and process the
transformation of content, and final output are going to be generated.

This application is capable of generating both jar and war.
 1. Generate a jar/war files: Open the terminal or command prompt and execute below gradle
    command
    ``` gradle translatorJar ```
    ```gradle war```
     

**Configuration** :

1. Once the DataTranslatorExample executes, the below java files performs required tasks.
   DataTranslator.java - Read the content from VendorData.text, process each record.
    ReadData.java - Read data from configuration files and process the data and generate final data
    WriteData.java - Write the final data to the output file
    FileIoUtils.java - This file contains the logic to read configuration files.
   
2. The below configuration files contains the input and output data.
   vendorData.txt - This is the Input file which contains the data to be transformed.
   config1DataExtractor.txt - This contains the data which specifies the columns needs to be fetched and transformed to expected columns.
   config2DataExtractor.txt - This contains the data which specifies the required rows needs to be fetched and transformed to the expected identifiers.
   translator.txt - This file contains output data which we write finalData.

**Notable points** :
1. Multi Threading is implemented to process very big data files with big number of rows and columns.



