package com.library;
import java.util.Objects;

// immutable value object representing a book identifier
public record ISBN(String value) {
    public ISBN{
        Objects.requireNonNull(value,"the ISBN cant be null");

        if(!value.matches("\\d{13}")){
            throw new IllegalArgumentException(
                "the ISBN must contain 13 charcter"
            );
        }
    }
}
