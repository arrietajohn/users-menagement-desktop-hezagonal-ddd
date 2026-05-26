package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidAuthorFieldException;
import java.util.Objects;

public record AuthorId(String value) {
    public AuthorId {
        final String normalized = Objects.requireNonNull(value, "AuthorId cannot be null").trim();
        if (normalized.isEmpty()) {
            throw InvalidAuthorFieldException.becauseEmpty("author id");
        }
        value = normalized;
    }
    @Override public String toString() { return value; }
}