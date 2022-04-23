package ru.itmo.entitiesDAO;

import ru.itmo.entities.Cat;
import ru.itmo.interfaces.DAO;
import ru.itmo.tools.DaoException;
import ru.itmo.tools.HibernateUtilUpdateTable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.List;

public class CatDAO implements DAO<Cat> {

    public CatDAO(){
    }

    @Override
    public Cat get(Integer id) throws DaoException {
        Cat cat = new Cat();
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            cat = session.get(Cat.class, id);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return cat;
    }

    @Override
    public List<Cat> getAll() throws DaoException {
        List<Cat> catList;
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            catList = session.createQuery("SELECT a FROM Cat a ORDER BY a.id", Cat.class).getResultList();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return catList;
    }

    @Override
    public boolean add(Cat cat) throws DaoException {
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.save(cat);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return true;
    }

    @Override
    public boolean update(Cat cat) throws DaoException {
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.update(cat);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return true;
    }

    @Override
    public boolean delete(Cat cat) throws DaoException {
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.delete(cat);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return true;
    }
}

