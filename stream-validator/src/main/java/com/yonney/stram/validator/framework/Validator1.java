package com.yonney.stram.validator.framework;

/**
 * Created by yonney on 17/8/24.
 */
public interface Validator1<T> {

    boolean validate(ValidatorContext context, T t);

    void onException(Exception e, ValidatorContext context, T t);
}
