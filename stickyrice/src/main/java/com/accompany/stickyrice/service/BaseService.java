package com.accompany.stickyrice.service;

import java.util.List;

public interface BaseService <T, ID> {
    //add item
    T create (T dto);

    //edit item
    T update (ID id, T dto);

    //find item with id
    T findById (ID id);

    //list all
    List<T> findAll();

    //delete item
    void deleteById (ID id);

    //item exists
    Boolean exitById (ID id);
}
