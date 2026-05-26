package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.valueobject.AuthorEmail;
import com.jcaa.usersmanagement.domain.valueobject.AuthorId;
import com.jcaa.usersmanagement.domain.valueobject.AuthorName;
import com.jcaa.usersmanagement.domain.valueobject.WorkCenter;
import lombok.Value;

@Value
public class AuthorModel {
    AuthorId id;
    AuthorName fullName;
    WorkCenter workCenter;
    AuthorEmail email;

    public static AuthorModel create(
            final AuthorId id,
            final AuthorName fullName,
            final WorkCenter workCenter,
            final AuthorEmail email) {
        return new AuthorModel(id, fullName, workCenter, email);
    }
}