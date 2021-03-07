package com.data.dataTranslator.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import static com.data.dataTranslator.util.DataTranslatorConstants.VENDOR_DATA_FILE;


public class ReadData implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ReadData.class);

    Map<String, Integer> headerIndexMappingMap = null;

    LinkedBlockingQueue<List<String>> linkedBlockingQueue = null;
    Map<String, String> headerMappingConfigMap = null;
    Map<String, String> rowMappingConfigMap = null;

    public ReadData(LinkedBlockingQueue<List<String>> linkedBlockingQueue,
                    Map<String, String> headerMappingConfigMap,
                    Map<String, String> rowMappingConfigMap) {
        this.linkedBlockingQueue = linkedBlockingQueue;
        this.headerMappingConfigMap = headerMappingConfigMap;
        this.rowMappingConfigMap = rowMappingConfigMap;
    }

    /**
     * +
     * Constructing header data
     *
     * @param stringList
     * @return
     */
    private List<String> handleHeaderData(List<String> stringList) {
        List<String> finalDataList = new ArrayList<>();
        headerIndexMappingMap = new LinkedHashMap<>();
        int index = 0;
        for (String header : stringList) {
            if (headerMappingConfigMap.containsKey(header)) {
                headerIndexMappingMap.put(header, index);
                finalDataList.add(headerMappingConfigMap.get(header));
            }
            index++;
        }
        return finalDataList;
    }

    /**
     * +
     * Constructing row data
     *
     * @param stringList
     * @return
     */
    private List<String> handleRowData(List<String> stringList) {
        List<String> finalDataList = new ArrayList<>();
        stringList.set(0, rowMappingConfigMap.get(stringList.get(0)));
        for (int value : headerIndexMappingMap.values()) {
            finalDataList.add(stringList.get(value));
        }
        return finalDataList;
    }

    /**
     * +
     * To read data from vendor file and process each record
     *
     * @throws FileNotFoundException
     * @throws InterruptedException
     */
    private void readFile() throws FileNotFoundException, InterruptedException {
        try (Scanner scanner = new Scanner(new FileReader(new File(VENDOR_DATA_FILE)))) {
            boolean isHeaderText = true;
            List<String> finalDataList = null;
            while (scanner.hasNextLine()) {
                List<String> stringList = Arrays.asList(scanner.nextLine().split("\t"));
                if (isHeaderText) {
                    isHeaderText = false;
                    finalDataList = handleHeaderData(stringList);
                } else if (rowMappingConfigMap.containsKey(stringList.get(0))) {
                    finalDataList = handleRowData(stringList);
                }
                if (finalDataList != null && !finalDataList.isEmpty()) {
                    linkedBlockingQueue.put(finalDataList);
                }

                finalDataList = null;
            }
        } catch (FileNotFoundException | InterruptedException exception) {
            logger.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    /**
     * +
     * To put finalData in queue
     */
    @Override
    public void run() {
        try {
            readFile();
        } catch (FileNotFoundException | InterruptedException ioe) {
            logger.error(ioe.getMessage(), ioe);
        } finally{
            List<String> stringList = new ArrayList<>();
            stringList.add("break");
            try{
                linkedBlockingQueue.put(stringList);
            }catch (InterruptedException exception){
                logger.error(exception.getMessage(),exception);
            }

        }
    }

}
