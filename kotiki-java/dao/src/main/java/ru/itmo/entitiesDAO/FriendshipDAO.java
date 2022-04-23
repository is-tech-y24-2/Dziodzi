package ru.itmo.entitiesDAO;

import ru.itmo.entities.Friendship;
import ru.itmo.interfaces.DAO;
import ru.itmo.tools.DaoException;
import ru.itmo.tools.HibernateUtilUpdateTable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FriendshipDAO implements DAO<Friendship> {

    public FriendshipDAO(){
    }
    @Override
    public Friendship get(Integer id) throws DaoException {
        Friendship friendship = new Friendship();
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            friendship = session.get(Friendship.class, id);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return friendship;
    }

    @Override
    public List<Friendship> getAll() throws DaoException {
        List<Friendship> friendshipList = new ArrayList<>();
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            friendshipList = session.createQuery("SELECT a FROM Friendship a ORDER BY a.catId", Friendship.class).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return friendshipList;
    }

    @Override
    public boolean add(Friendship friendship) throws DaoException {
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.save(friendship);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return true;
    }

    @Override
    public boolean update(Friendship friendship) throws DaoException {
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.update(friendship);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return true;
    }

    @Override
    public boolean delete(Friendship friendship) throws DaoException {
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.delete(friendship);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return true;
    }
}

