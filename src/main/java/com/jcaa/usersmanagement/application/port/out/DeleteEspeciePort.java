// DeleteEspeciePort.java
package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.EspecieId;

public interface DeleteEspeciePort {
    void delete(EspecieId id);
}