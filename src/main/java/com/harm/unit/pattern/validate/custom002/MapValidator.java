package com.harm.unit.pattern.validate.custom002;

import java.util.*;

public class MapValidator<K, V> {

    private Map<K, V> target;
    private Map<K, ArrayList<ValidateStrategy<V>>> validateStrategiesMap;
    private boolean checkAll = false;
    private ValidateResult<K, V> validateResult;

    private MapValidator(Map<K, V> target, Map<K, ArrayList<ValidateStrategy<V>>> validateStrategiesMap, boolean checkAll) {
        this.target = target;
        this.validateStrategiesMap = validateStrategiesMap;
        this.checkAll = checkAll;
        this.validateResult = new ValidateResult<>();
    }

    public void validate() {
        Iterator<K> iter = target.keySet().iterator();
        while(iter.hasNext()) {
            K key = iter.next();
            ArrayList<ValidateStrategy<V>> validateStrategies = validateStrategiesMap.get(key);
            if(validateStrategies != null) {
                for(ValidateStrategy<V> validateStrategy : validateStrategies) {
                    if(!validateStrategy.test(target.get(key))) {
                        validateResult.addError(key, validateStrategy);
                        if(!checkAll) {
                            break;
                        }
                    }
                }
            }
        }
    }

    public ValidateResult<K, V> getValidateResult() {
        return validateResult;
    }

    public static final class Builder<K, V> {
        private Map<K, V> target;
        private Map<K, ArrayList<ValidateStrategy<V>>> validateStrategiesMap;
        private boolean checkAll = false;
        public Builder<K, V> target(Map<K, V> target) {
            this.target = target;
            this.validateStrategiesMap = new HashMap<>();
            return this;
        }
        public Builder<K, V> strategy(K key, ValidateStrategy<V>... validateStrategy) {
            ArrayList<ValidateStrategy<V>> validateStrategies = validateStrategiesMap.get(key);
            if(validateStrategies == null) {
                validateStrategies = new ArrayList<>();
                validateStrategiesMap.put(key, validateStrategies);
            }
            Collections.addAll(validateStrategies, validateStrategy);
            return this;
        }
        public Builder<K, V> checkAll(boolean checkAll) {
            this.checkAll = checkAll;
            return this;
        }
        public MapValidator<K, V> build() {
            return new MapValidator<K, V>(target, validateStrategiesMap, checkAll);
        }
    }

    public static final class ValidateResult<K, V> {
        private Map<K, ArrayList<ValidateStrategy<V>>> errors;
        public ValidateResult() {
            errors = new HashMap<>();
        }
        public boolean hasErrors() {
            return !errors.isEmpty();
        }
        public void addError(K key, ValidateStrategy<V> validateStrategy) {
            ArrayList<ValidateStrategy<V>> erroredStrategies = errors.get(key);
            if(erroredStrategies == null) {
                erroredStrategies = new ArrayList<>();
                errors.put(key, erroredStrategies);
            }
            erroredStrategies.add(validateStrategy);
        }

        public Map<K, ArrayList<ValidateStrategy<V>>> getErrors() {
            return errors;
        }
    }
}
