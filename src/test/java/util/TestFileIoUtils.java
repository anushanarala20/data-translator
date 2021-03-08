package util;

import com.data.dataTranslator.util.FileIoUtils;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestFileIoUtils {
    @Test
    public void readFilesSuccessfully() {
        Map<String, String> dataValues = new LinkedHashMap<>();
        dataValues.put("COL0", "OURID");
        String config1DataFile = "./src/test/resources/headerConfigData";
        Map<String, String> data = FileIoUtils.readData(config1DataFile);
        assert !data.isEmpty();
    }

    @Test(expected = NullPointerException.class)
    public void readFilesException() {
        String VendorEmptyFile = null;
        Map<String, String> data = FileIoUtils.readData(VendorEmptyFile);
    }


}
