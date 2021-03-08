package application;

import com.data.dataTranslator.application.DataTranslator;
import com.data.dataTranslator.read.ReadData;
import com.data.dataTranslator.wriite.WriteData;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class TestDataTranslator {
    static LinkedBlockingQueue<List<String>> linkedBlockingQueue = new LinkedBlockingQueue<>();
    static Map<String, String> headerMappingConfigMap = new LinkedHashMap<>();
    static Map<String, String> rowMappingConfigMap = new LinkedHashMap<>();
    DataTranslator dataTranslator = new DataTranslator();
    ReadData readData = null;
    WriteData writeData = null;

    @Test
    public void processVendorFileContent() throws InterruptedException {
        LinkedBlockingQueue<List<String>> linkedBlockingQueue = new LinkedBlockingQueue<>();
        String rowData = "OURID" + "\n" + "OURID2";
        List<String> finalData = new LinkedList<>();
        finalData.add(rowData);
        linkedBlockingQueue.put(finalData);
        List<String> stringList = new ArrayList<>();
        stringList.add("break");
        linkedBlockingQueue.put(stringList);
        readData = new ReadData(linkedBlockingQueue, headerMappingConfigMap, rowMappingConfigMap);
        writeData = new WriteData(linkedBlockingQueue);
        dataTranslator = Mockito.spy(dataTranslator);
        dataTranslator.processVendorFileContent();
        Mockito.verify(dataTranslator, Mockito.times(1)).processVendorFileContent();
    }
}
