package com.data.dataTranslator;

import com.data.dataTranslator.application.DataTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataTranslatorExample {
    private static final Logger logger = LoggerFactory.getLogger(DataTranslator.class);

    public static void main(String[] args) {
        try {
            DataTranslator dataTranslator = new DataTranslator();
            dataTranslator.processVendorFileContent();
        } catch (Exception exp) {
            logger.error(exp.getMessage(), exp);
        }
    }
}
