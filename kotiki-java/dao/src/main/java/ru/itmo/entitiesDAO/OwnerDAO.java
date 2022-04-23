package ru.itmo.entitiesDAO;

import ru.itmo.entities.Owner;
import ru.itmo.interfaces.DAO;
import ru.itmo.tools.DaoException;
import ru.itmo.tools.HibernateUtilUpdateTable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

public class OwnerDAO implements DAO<Owner> {

    public OwnerDAO(){
    }

    @Override
    public Owner get(Integer id) throws DaoException {
        Owner owner = new Owner();
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            owner = session.get(Owner.class, id);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return owner;
    }

    @Override
    public List<Owner> getAll() throws DaoException {
        List<Owner> ownerList = new ArrayList<>();
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            ownerList = session.createQuery("SELECT a FROM Owner a ORDER BY a.id", Owner.class).getResultList();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return ownerList;
    }

    @Override
    public boolean add(Owner owner) throws DaoException {
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.save(owner);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return true;
    }

    @Override
    public boolean update(Owner owner) throws DaoException {
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.update(owner);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return true;
    }

    @Override
    public boolean delete(Owner owner) throws DaoException {
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.delete(owner);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return true;
    }
}
