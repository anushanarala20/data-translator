package com.data.dataTranslator.wriite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import static com.data.dataTranslator.util.DataTranslatorConstants.TRANSLATED_FILE;


public class WriteData implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(WriteData.class);

    LinkedBlockingQueue<List<String>> linkedBlockingQueue = null;

    public WriteData(LinkedBlockingQueue<List<String>> linkedBlockingQueue) {
        this.linkedBlockingQueue = linkedBlockingQueue;
    }

    /**
     * +
     * Final data writes to the translatedData.txt file
     *
     * @param finalDataList
     * @throws IOException
     */
    static void writeData(List<String> finalDataList) throws IOException {
        try (FileWriter writer = new FileWriter(TRANSLATED_FILE, true)) {
            StringBuffer stringBuffer = new StringBuffer();
            for (String row : finalDataList) {
                stringBuffer.append(row).append("\t");
            }
            writer.write(stringBuffer.toString());
            writer.write("\n");
            writer.flush();
        } catch (IOException exception) {
            logger.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    /**
     * +
     * Stops the execution, if VendorData file reaches the end
     */
    @Override
    public void run() {
        try {
            while (true) {
                List<String> finalDataList = linkedBlockingQueue.take();
                if (finalDataList.get(0) == "break") {
                    break;
                }
                writeData(finalDataList);
            }
        } catch (InterruptedException | IOException exp) {
            logger.warn(exp.getMessage(), exp);
        }
    }
}
