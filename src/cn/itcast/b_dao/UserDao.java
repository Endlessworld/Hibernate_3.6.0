package cn.itcast.b_dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cn.itcast.a_helloworld.User;

public class UserDao {

	private static SessionFactory sessionFactory;

	static {
		// ��ȡ�����ļ�������Session��������
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addClass(User.class);
		sessionFactory = cfg.buildSessionFactory();
	}

	/**
	 * �����û�
	 * 
	 * @param user
	 */
	public void save(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user); // ����
			tx.commit();
		} catch (RuntimeException e) {
			// tx.rollback();
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	/**
	 * �����û�
	 * 
	 * @param user
	 */
	public void update(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(user); // ����
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	/**
	 * ɾ��ָ��id���û�
	 * 
	 * @param id
	 */
	public void delete(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// -------------------------------------
			Object user = session.get(User.class, id); // �ȴ����ݿ��ȡ����
			session.delete(user); // ɾ������ʵ�����
			// -------------------------------------
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	/**
	 * ��ȡָ��id�Ķ���
	 * 
	 * @param id
	 * @return
	 */
	public User getById(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, id); // ��ȡ
			tx.commit();
			return user;
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	/**
	 * ��ѯ����
	 * 
	 * @return
	 */
	public List<User> findAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// HQL�ķ�ʽ
			// List<User> list = session.createQuery("FROM User").list(); // ʹ��HQL��ѯ
			// QBC�ķ�ʽ
			Criteria criteria = session.createCriteria(User.class);
			// criteria.add(Restrictions.eq("name", "����"));
			// criteria.add(Restrictions.eq("password", "1234"));
			List<User> list = criteria.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	/**
	 * ��ҳ�Ĳ�ѯ
	 * 
	 * @param firstResult
	 *            ��ʼ��ȡ�ļ�¼������
	 * @param maxResults
	 *            ����ȡ����������
	 * @return �ܼ�¼�� + һ������
	 */
	public QueryResult<User> findAll(int firstResult, int maxResults) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// -------------------------------------
			// 1����ѯ�ܼ�¼��
			Long count = (Long) session.createQuery("SELECT COUNT(*) FROM User").uniqueResult(); // ִ�в�ѯ

			// 2����ѯһ������
			Query query = session.createQuery("FROM User");
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResults);
			List<User> list = query.list(); // ִ�в�ѯ

			// -------------------------------------
			tx.commit();
			return new QueryResult<User>(list, count);
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}
