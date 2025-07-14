package com.accompany.stickyrice.service;

import java.util.List;
import java.util.Optional;

public interface BaseService <T, ID> {
    //add item
    T create (T dto);

    //edit item
    T update (ID id, T dto);

    //find item with id
    Optional<T> findById (ID id);

    //list all
    List<T> findAll();

    //delete item
    void deleteById (ID id);

    //item exists
    Boolean existsById (ID id);
}
