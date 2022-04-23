package ru.itmo.entitiesDAO;

import ru.itmo.entities.Ownership;
import ru.itmo.interfaces.DAO;
import ru.itmo.tools.DaoException;
import ru.itmo.tools.HibernateUtilUpdateTable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

public class OwnershipDAO implements DAO<Ownership> {

    public OwnershipDAO(){
    }

    @Override
    public Ownership get(Integer id) throws DaoException {
        Ownership ownership = new Ownership();
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            ownership = session.get(Ownership.class, id);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return ownership;
    }

    @Override
    public List<Ownership> getAll() throws DaoException {
        List<Ownership> ownershipList = new ArrayList<>();
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            ownershipList = session.createQuery("SELECT a FROM Ownership a ORDER BY a.ownerId", Ownership.class).getResultList();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return ownershipList;
    }

    @Override
    public boolean add(Ownership ownership) throws DaoException {
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.save(ownership);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return true;
    }

    @Override
    public boolean update(Ownership ownership) throws DaoException {
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.update(ownership);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return true;
    }

    @Override
    public boolean delete(Ownership ownership) throws DaoException {
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.delete(ownership);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return true;
    }
}