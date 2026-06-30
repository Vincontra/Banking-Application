package util;

import exceptions.ValidationException;

@FunctionalInterface
public interface Validation<K>{
    void validate(K value) throws ValidationException;



}
