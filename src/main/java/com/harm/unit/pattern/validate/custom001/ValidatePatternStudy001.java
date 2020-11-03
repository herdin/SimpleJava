package com.harm.unit.pattern.validate.custom001;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class ValidatePatternStudy001 implements Unit {
    private Logger logger = LoggerFactory.getLogger(ValidatePatternStudy001.class);
    @Override
    public Object execute(Object[] obj) throws Exception {

        String validateTarget = "some string";
        /*
        * case 1.
        * if(validate(map.get("a"), NotNull, NotEmpty)) {
        *   throw new Exception(validateResult.get());
        * }
        * */
        Predicate<String> stringValidator = null;
//        stringValidator = new DefaultValidator<>(new NotEmpty, new NotNull());
//        logger.debug("validate expect true  -> {}", stringValidator.test(validateTarget));
//        logger.debug("validate expect false -> {}", stringValidator.test(validateTarget));
//        stringValidator = new DefaultValidator<>(new In<>("some string", "some string1", "some string2"));
//        logger.debug("validate expect true  -> {}", stringValidator.test(validateTarget));
//        stringValidator = new DefaultValidator<>(new In<>("some string0", "some string1", "some string2"));
//        logger.debug("validate expect false -> {}", stringValidator.test(validateTarget));
//
//        Predicate<Integer> integerValidator = null;
//        integerValidator = new DefaultValidator<>(new Min(4), new Max(10));
//        logger.debug("validate expect true  -> {}", integerValidator.test(7));
//        logger.debug("validate expect false -> {}", integerValidator.test(2));
//        logger.debug("validate expect false -> {}", integerValidator.test(11));
//        integerValidator = new DefaultValidator<>(new In<>(1, 3, 5));
//        logger.debug("validate expect false -> {}", integerValidator.test(11));

        /*
        * case 2.
        * if(validate(map.get("a")).isNull().isEmpty()) {
        *   throw new Exception(validateResult.get());
        * }
        * */

        return null;
    }
}

//replace with Predicate<T>
//interface Validator<T> {
//    boolean validate(T target);
//}

//replace with Predicate<T>
//interface ValidateStrategy<T> {
//    boolean validate(T target);
//}
