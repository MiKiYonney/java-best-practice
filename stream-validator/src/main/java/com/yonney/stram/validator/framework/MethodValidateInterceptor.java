package com.yonney.stram.validator.framework;

import lombok.val;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import static com.google.common.collect.Iterables.getFirst;

/**
 * Created by yonney on 17/8/25.
 */
@Component
public class MethodValidateInterceptor implements MethodInterceptor {
    private final Validator validator;

    public MethodValidateInterceptor() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        //获取参数，并检查是否应该验证
        Object[] arguments = methodInvocation.getArguments();
        for (Object argument : arguments) {

            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(argument);
            ConstraintViolation<Object> constraintViolation = getFirst(constraintViolations, null);
            if (constraintViolation == null) {
                continue;
            }

            throw new RuntimeException(constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage());
        }
        return methodInvocation.proceed();
    }
}
