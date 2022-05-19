package ru.itmo.entityDAO;

import ru.itmo.entiity.Friendship;
import ru.itmo.interfaces.DAO;
import ru.itmo.tools.DaoException;
import ru.itmo.tools.HibernateUtilUpdateTable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.ArrayList;
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
    public List<Friendship> getAllByOneId(Integer id) throws DaoException {
        List<Friendship> friendshipList = new ArrayList<>();
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            friendshipList = session.createQuery("SELECT a FROM Friendship a WHERE a.catId = :id ORDER BY a.catId", Friendship.class).setParameter("id", id).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            throw new DaoException();
        }
        return friendshipList;
    }

    @Override
    public Friendship getByTwoId(Integer id1, Integer id2) throws DaoException {
        Friendship friendship = new Friendship();
        try {
            Session session = HibernateUtilUpdateTable.getSessionFactory().openSession();
            session.getTransaction().begin();
            friendship = session.createQuery("SELECT a FROM Friendship a WHERE a.catId = :id1 and a.friendId = :id2", Friendship.class).setParameter("id1", id1).setParameter("id2", id2).getSingleResult();
            session.getTransaction().commit();
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

