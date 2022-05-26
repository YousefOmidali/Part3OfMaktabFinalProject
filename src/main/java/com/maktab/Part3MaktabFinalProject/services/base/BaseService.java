package com.maktab.Part3MaktabFinalProject.services.base;

import java.util.List;

public interface BaseService<T, ID> {
    T saveOrUpdate(T t);

    List<T> findAll();

    T findById(ID id);

    void deleteById(ID id);

}
