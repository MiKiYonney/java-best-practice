package com.yonney.stram.validator.framework;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

public class BeanValidatorTest {
 
    public static void main(String[] args) {

        ExecutableValidator executableValidator = Validation
                .buildDefaultValidatorFactory()
                .getValidator()
                .forExecutables();



        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        try {

            Validator validator = factory.getValidator();
            //验证Bean参数，并返回验证结果信息
            Car car = new Car();
            Set<ConstraintViolation<Car>> validators = validator.validate(car);
            for (ConstraintViolation<Car> constraintViolation : validators) {
                System.out.println(constraintViolation.getMessage());
            }

            // 验证方法参数
            Method method = null;
            try {
                method = Car.class.getMethod("drive", int.class);
            } catch (SecurityException e) {
            } catch (NoSuchMethodException e) {
            }
            Object[] parameterValues = { 80 };
            ExecutableValidator executableValidator = validator.forExecutables();
            Set<ConstraintViolation<Car>> methodValidators = executableValidator.validateParameters(car,
                    method, parameterValues);
            for (ConstraintViolation<Car> constraintViolation : methodValidators) {
                System.out.println(constraintViolation.getMessage());
            }
        }finally {
            factory.close();
        }
    }
 
    public static class Car {
 
        private String name;
 
        @NotNull(message = "车主不能为空")
        public String getRentalStation() {
            return name;
        }
 
        public void drive(@Max(75) int speedInMph) {
 
        }
 
    }
}