package com.example.todoapp.common.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityExceptionSuppliers {

    public static final Supplier<RuntimeException> userNotFound = () -> {
        throw new IllegalArgumentException("User is not found.");
    };

    public static final Supplier<RuntimeException> todoNotFound = () -> {
        throw new IllegalArgumentException("Todo is not found.");
    };

    public static final Supplier<RuntimeException> emailAlreadyExist = () -> {
        throw new IllegalArgumentException("Email is already exist.");
    };

    public static final Supplier<RuntimeException> incorrectPassword = () -> {
        throw new IllegalArgumentException("Passwords do not match.");
    };
}
