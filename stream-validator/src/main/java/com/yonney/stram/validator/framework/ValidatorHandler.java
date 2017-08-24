package com.yonney.stram.validator.framework;

/**
 * Created by yonney on 17/8/24.
 */
public class ValidatorHandler<T> implements Validator1<T> {

    public boolean validate(ValidatorContext context, T t) {
        return true;
    }

    public void onException(Exception e, ValidatorContext context, T t) {

    }
}
