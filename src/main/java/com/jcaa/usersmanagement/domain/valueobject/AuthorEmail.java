package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidAuthorFieldException;
import java.util.Objects;
import java.util.regex.Pattern;

public record AuthorEmail(String value) {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");

    public AuthorEmail {
        final String normalized = Objects.requireNonNull(value, "AuthorEmail cannot be null").trim().toLowerCase();
        if (normalized.isEmpty()) {
            throw InvalidAuthorFieldException.becauseEmpty("author email");
        }
        if (!EMAIL_PATTERN.matcher(normalized).matches()) {
            throw InvalidAuthorFieldException.becauseInvalidFormat("author email", normalized);
        }
        value = normalized;
    }
    @Override public String toString() { return value; }
}