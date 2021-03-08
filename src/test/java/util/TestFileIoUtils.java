package util;

import com.data.dataTranslator.util.FileIoUtils;
import org.junit.Test;

import java.util.Map;

public class TestFileIoUtils {
    @Test
    public void readFilesSuccessfully() {
        String config1DataFile = "./src/test/resources/headerConfigData";
        Map<String, String> data = FileIoUtils.readData(config1DataFile);
        assert (!data.isEmpty());
    }

}
