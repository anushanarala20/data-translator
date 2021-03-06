package read;

import com.data.dataTranslator.read.ReadData;
import com.data.dataTranslator.wriite.WriteData;
import org.junit.After;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class TestReadData {
    static LinkedBlockingQueue<List<String>> linkedBlockingQueue = new LinkedBlockingQueue<>();
    static Map<String, String> headerMappingConfigMap = new LinkedHashMap<>();
    static Map<String, String> rowMappingConfigMap = new LinkedHashMap<>();

    @Test
    public void runSuccessfully() {
        headerMappingConfigMap.put("COL0", "OURID");
        rowMappingConfigMap.put("ID2", "OURID2");
        ReadData readData = new ReadData(linkedBlockingQueue, headerMappingConfigMap, rowMappingConfigMap);
        readData = Mockito.spy(readData);
        readData.run();
        Mockito.verify(readData, Mockito.times(1));
    }

    @Test(expected = NullPointerException.class)
    public void runUnsuccessfulForHeaders() {
        headerMappingConfigMap = null;
        ReadData readData = new ReadData(linkedBlockingQueue, headerMappingConfigMap, rowMappingConfigMap);
        readData.run();
    }



}
