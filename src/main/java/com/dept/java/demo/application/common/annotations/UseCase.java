package com.dept.java.demo.application.common.annotations;

import com.dept.java.demo.application.common.exceptions.ValidationFailed;

public interface UseCase<T, M> {

    T execute(M requestModel) throws ValidationFailed;

    default void validate(M requestModel) throws ValidationFailed {
        // no need for validation
    }
}
