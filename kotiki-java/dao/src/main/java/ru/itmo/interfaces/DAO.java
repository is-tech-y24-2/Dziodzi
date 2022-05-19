package ru.itmo.interfaces;

import ru.itmo.tools.DaoException;

import java.util.List;

public interface DAO<T> {
    T get(Integer id) throws DaoException;

    List<T> getAllByOneId(Integer id) throws DaoException;

    T getByTwoId(Integer id1, Integer id2) throws DaoException;
    List<T> getAll() throws DaoException;
    boolean add(T object) throws DaoException;
    boolean update(T object) throws DaoException;
    boolean delete(T object) throws DaoException;
}

