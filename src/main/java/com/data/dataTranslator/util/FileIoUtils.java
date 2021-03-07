package com.data.dataTranslator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileIoUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileIoUtils.class);

    /**
     * +
     * Read the data from config data extractors and construct map
     *
     * @param fileName
     * @return
     */
    public static Map<String, String> readData(String fileName) {
        Map<String, String> map = null;
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<String> streamsList = stream.collect(Collectors.toList());
            map = streamsList
                    .stream()
                    .collect(Collectors.toMap(data -> data.split("=")[0], data -> data.split("=")[1]));
        } catch (IOException exception) {
            logger.warn(exception.getMessage(), exception);
        }
        return map;
    }
}
