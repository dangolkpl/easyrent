package com.easyrent.core.exceptionhandler.exceptions;

import org.springframework.lang.Nullable;

public class AccountNotActivatedException extends RuntimeException {
    public AccountNotActivatedException(@Nullable String reason, @Nullable Throwable cause) {
        super(reason, cause);
    }
}
