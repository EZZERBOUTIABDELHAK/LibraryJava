package com.library;
import java.util.Objects;

public record ISBN(String value) {
    public ISBN{
        Objects.requireNonNull(value,"the IBSM cant be null");

        if(!value.matches("\\d{13}")){
            throw new IllegalArgumentException(
                "the ISBN must contain 13 charcter"
            );
        }
    }
}
