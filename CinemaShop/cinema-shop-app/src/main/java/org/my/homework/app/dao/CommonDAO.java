package org.my.homework.app.dao;

import org.my.homework.app.entities.CommonEntity;
import org.my.homework.app.entities.Movie;

import java.util.List;


public interface CommonDAO<T extends CommonEntity> {
    T getById(Long id);
    void saveOrUpdate(CommonEntity object);
    List<T> getAll();
    List <? extends CommonEntity> getAllByClass(Class<? super CommonEntity> clazz);
    void delete(CommonEntity object);

    T getByIdByClass(Long id, Class<? super CommonEntity> clazz);
}
