package com.yonney.best.practice.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yonney on 17/8/25.
 */
public class Sl4jSourceCode {
    private static final Logger logger = LoggerFactory.getLogger(Sl4jSourceCode.class);

    public static void main(String[] args) {
        logger.info("---------logging info---------");
        logger.debug("---------logging debug---------");
    }
}
