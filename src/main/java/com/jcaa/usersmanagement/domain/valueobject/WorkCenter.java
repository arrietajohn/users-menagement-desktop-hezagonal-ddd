package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidAuthorFieldException;
import java.util.Objects;

public record WorkCenter(String value) {
    public WorkCenter {
        final String normalized = Objects.requireNonNull(value, "WorkCenter cannot be null").trim();
        if (normalized.isEmpty()) {
            throw InvalidAuthorFieldException.becauseEmpty("work center");
        }
        value = normalized;
    }
    @Override public String toString() { return value; }
}