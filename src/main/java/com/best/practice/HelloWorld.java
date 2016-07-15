package com.best.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yonney on 16/7/15.
 */
public class HelloWorld {
    private static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        logger.info("hello best practice");
    }
}
