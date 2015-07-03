package dao;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonDAO {

    public void insert(Person person) {
        Transaction transaction = null;
        Session session     = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void update(Person person) {
        Transaction transaction = null;
        Session session     = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(person);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void delete(Person person) {
        Transaction transaction = null;
        Session session     = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(person);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public List<Person> findAll() {
        List<Person>    persons     = new ArrayList<Person>();
        Transaction     transaction = null;
        Session         session     = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            persons = session.createQuery("from Person").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return persons;
    }

    public Person findById(Integer id) {
        Person        person        = null;
        Transaction transaction = null;
        Session     session     = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            String queryString = "from Person where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            person = (Person) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return person;
    }
}
