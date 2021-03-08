package write;

import com.data.dataTranslator.wriite.WriteData;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class TestWriteData {

    @Test
    public void runSuccessfully() throws InterruptedException {
        LinkedBlockingQueue<List<String>> linkedBlockingQueue = new LinkedBlockingQueue<>();
        String rowData = "OURID" + "\n" + "OURID2";
        List<String> finalData = new LinkedList<>();
        finalData.add(rowData);
        linkedBlockingQueue.put(finalData);
        List<String> stringList = new ArrayList<>();
        stringList.add("break");
        linkedBlockingQueue.put(stringList);
        WriteData writeData = new WriteData(linkedBlockingQueue);
        writeData.run();
    }

    @Test(expected = NullPointerException.class)
    public void runUnsuccessfully() {
        LinkedBlockingQueue<List<String>> linkedBlockingQueue = null;
        WriteData writeData = new WriteData(linkedBlockingQueue);
        writeData.run();
    }



}
