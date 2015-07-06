package main.java.dao;

import main.java.model.Keyword;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import main.java.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class KeywordDAO {

    public void insert(Keyword keyword) {
        Transaction transaction = null;
        Session session         = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(keyword);
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

    public void update(Keyword keyword) {
        Transaction transaction = null;
        Session session         = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(keyword);
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

    public void delete(Keyword keyword) {
        Transaction transaction = null;
        Session session         = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(keyword);
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

    public List<Keyword> findAll() {
        List<Keyword> keywords  = new ArrayList<>();
        Session session         = HibernateUtil.getSessionFactory().openSession();
        try {
            keywords = session.createQuery("from Keyword").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return keywords;
    }

    public Keyword findById(Integer id) {
        Keyword keyword = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from Keyword where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            keyword = (Keyword) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return keyword;
    }

    public Keyword findByWord(String word) {
        Keyword keyword = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from Keyword where word = :word";
            Query query = session.createQuery(queryString);
            query.setString("word", word);
            keyword = (Keyword) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return keyword;
    }
}
