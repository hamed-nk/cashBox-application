package com.demisco.cashbox.service;

import java.util.Optional;

public interface DoaService {

    <T extends CodeEnabled> Optional<T> findByCode(Class<T> tClass, String code);
}
