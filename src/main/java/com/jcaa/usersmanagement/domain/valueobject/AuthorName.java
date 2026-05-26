package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidAuthorFieldException;
import java.util.Objects;

public record AuthorName(String value) {
    public AuthorName {
        final String normalized = Objects.requireNonNull(value, "AuthorName cannot be null").trim();
        if (normalized.isEmpty()) {
            throw InvalidAuthorFieldException.becauseEmpty("author name");
        }
        value = normalized;
    }
    @Override public String toString() { return value; }
}