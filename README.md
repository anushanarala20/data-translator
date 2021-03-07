# data-translator

> Data Translator will perform ETL on the given data.

## How to get started:

* Please ensure that JAVA 8 installed in your system
* Please fallow below steps to clone the repository
    ```
  $ git clone https://github.com/anushanarala20/data-translator.git
  $ cd data-translator
* Import the project into intelliJ or eclipse or any java based IDE as a gradle project.
* run the `DataTranslatorExample.java`.
* Final out put will be written and stored in `transnslatedData.txt`.

## How it works

* Run the `DataTranslatorExample.java` programs to initiate the data translator.
* `DataTranslator.java` is the facade for the ETL of the given input data.
*  Data Translator will initiate two parallel threads one to read and transforms the data and one will write the transformed data to out put text file.
*  **`ReadData`**: Will read the data line by line from `inputconfigData.txt` and transform the data based on the configuration given in `headerMappingConfigData.txt` and `RowMappingConfigData.txt`.
*  The transformed data will be placed in `LinkedBlockingQueue` by ReadData. 
*  **`WriteData`**: Reads data placed in `LinkedBlockingQueue` and writes the data into `translatedData.txt` file.
*  ReadData acts as producer and WriteData act as consumer for blocking queue, Both works parallel and perform ETl on given data.
*  The File paths and names were externalized to `DataTranslatorConstants.java`, if we need to update paths or file names we can update in DataTranslatorConstants class.

## Files Used:

*   `inputconfigData.txt` - This is the Input file which contains the data to be transformed.
*   `headerMappingConfigData.txt` - This contains the data which specifies the columns needs to be fetched and transformed to expected columns.
*   `RowMappingConfigData` - This contains the data which specifies the required rows needs to be fetched and transformed to the expected identifiers.
*   `translatedData.txt` - This file contains output data which we write finalData.

### Build Details

Gradle is used as a build tool in this application, and we can generate a jar or war.
 * ```build.gradle``` - This file contains the application configuration
 * ```gradle clean build``` - To build the project and generate the war
 * ```gradle translatorJar``` - To generate jar
 * ```gradle war``` - to generate war 




