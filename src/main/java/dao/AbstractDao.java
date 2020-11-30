package dao;

import entities.Activity;
import entities.Building;
import entities.Report;
import entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.MyHibernateSessionFactoryUtil;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class AbstractDao<T> {




    public List<T> performFetchingListInPersistenceContext(Function<Session, List<T>> function) {
        Session session = MyHibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<T> entities = function.apply(session);
        session.close();
        return entities;
    }

    public T performFetchingInPersistenceContext(Function<Session, T> function) {
        Session session = MyHibernateSessionFactoryUtil.getSessionFactory().openSession();
        T entity = function.apply(session);
        session.close();
        return entity;
    }
    public void performUpdatesInPersistenceContext(Consumer<Session> sessionConsumer) {

        SessionFactory se = MyHibernateSessionFactoryUtil.getSessionFactory();
        Session session = se.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            sessionConsumer.accept(session);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new HibernateException(ex);
        } finally {
            session.close();
        }
    }
}
