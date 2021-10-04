package com.dept.java.demo.application;

public interface UseCase<T, M> {

    public T execute(M requestModel) throws ValidationFailed;

    public void validate(M requestModel) throws ValidationFailed;
}
