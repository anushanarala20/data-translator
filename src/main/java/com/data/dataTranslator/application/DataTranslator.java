package com.data.dataTranslator.application;

import com.data.dataTranslator.read.ReadData;
import com.data.dataTranslator.util.FileIoUtils;
import com.data.dataTranslator.wriite.WriteData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import static com.data.dataTranslator.util.DataTranslatorConstants.*;

public class DataTranslator {

    private static final Logger logger = LoggerFactory.getLogger(DataTranslator.class);

    public static Map<String, String> headerMappingConfigMap = null;
    public static Map<String, String> rowMappingConfigMap = null;

    static {
        headerMappingConfigMap = FileIoUtils.readData(HEADER_MAPPING_FILE);
        rowMappingConfigMap = FileIoUtils.readData(ROW_MAPPING_FILE);
    }

    /**
     * +
     * Always truncate the data from translatedData.txt file before starting the execution
     */
    private void resetFileContent() {
        try (FileChannel fileChannel = FileChannel.open(Paths.get(TRANSLATED_FILE), StandardOpenOption.WRITE)) {
            fileChannel.truncate(0).close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * +
     * Process the VendorFile Content
     *
     * @throws Exception
     */
    public void processVendorFileContent() throws Exception {
        try {
            resetFileContent();
            LinkedBlockingQueue<List<String>> linkedBlockingQueue = new LinkedBlockingQueue<>();
            ReadData readData = new ReadData(linkedBlockingQueue, headerMappingConfigMap, rowMappingConfigMap);
            WriteData writeData = new WriteData(linkedBlockingQueue);
            Thread t1 = new Thread(readData);
            Thread t2 = new Thread(writeData);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (Exception exp) {
            logger.error(exp.getMessage(), exp);
            throw exp;
        }
    }

}
