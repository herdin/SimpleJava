package com.harm.unit.pattern.validate.custom002;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ValidatePatternStudy002 implements Unit {
    private Logger logger = LoggerFactory.getLogger(ValidatePatternStudy002.class);
    @Override
    public Object execute(Object[] obj) throws Exception {
        Map<String, String> mapStrStr = new HashMap<>();
        mapStrStr.put("textTarget01", "some string 01");
        mapStrStr.put("numberTarget01", "1");

        MapValidator mapValidatorStrStr = new MapValidator.Builder<String, String>()
                .target(mapStrStr)
                .strategy("textTarget01", new NotNull(), new NotEmpty(), new In<>("some string 00", "some string 01"))
                .strategy("numberTarget01", new NotNull(), new NotEmpty())
                .build()
                ;
        mapValidatorStrStr.validate();
        MapValidator.ValidateResult validateResult = mapValidatorStrStr.getValidateResult();
        logger.debug("validate result has error? -> {}", validateResult.hasErrors());
        Map<String, ArrayList<ValidateStrategy<String>>> errors = validateResult.getErrors();
        Iterator<String> iter = errors.keySet().iterator();
        while(iter.hasNext()) {
            String key = iter.next();
            ArrayList<ValidateStrategy<String>> errorlist = errors.get(key);
            for(ValidateStrategy<String> validateStrategy : errorlist) {
                logger.debug("{} -> {}", key, validateStrategy.getName());
            }
        }

        Map<String, Integer> mapStrInt = new HashMap<>();
        mapStrInt.put("numberTarget01", 1);
        mapStrInt.put("numberTarget02", 6);

        MapValidator<String, Integer> mapValidatorStrInt = new MapValidator.Builder<String, Integer>()
                .target(mapStrInt)
                .strategy("numberTarget01", new Min(0), new Max(10))
                .strategy("numberTarget02", new Min(0), new Max(3))
                .build()
            ;

        mapValidatorStrInt.validate();
        validateResult = mapValidatorStrInt.getValidateResult();
        logger.debug("validate result has error? -> {}", validateResult.hasErrors());
        errors = validateResult.getErrors();
        iter = errors.keySet().iterator();
        while(iter.hasNext()) {
            String key = iter.next();
            ArrayList<ValidateStrategy<String>> errorlist = errors.get(key);
            for(ValidateStrategy<String> validateStrategy : errorlist) {
                logger.debug("{} -> {}", key, validateStrategy.getName());
            }
        }
        return null;
    }

}