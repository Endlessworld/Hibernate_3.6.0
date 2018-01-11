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
		// 读取配置文件并生成Session工厂对象
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addClass(User.class);
		sessionFactory = cfg.buildSessionFactory();
	}

	/**
	 * 保存用户
	 * 
	 * @param user
	 */
	public void save(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user); // 保存
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
	 * 更新用户
	 * 
	 * @param user
	 */
	public void update(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(user); // 更新
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	/**
	 * 删除指定id的用户
	 * 
	 * @param id
	 */
	public void delete(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// -------------------------------------
			Object user = session.get(User.class, id); // 先从数据库获取对象
			session.delete(user); // 删除的是实体对象
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
	 * 获取指定id的对象
	 * 
	 * @param id
	 * @return
	 */
	public User getById(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, id); // 获取
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
	 * 查询所有
	 * 
	 * @return
	 */
	public List<User> findAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// HQL的方式
			// List<User> list = session.createQuery("FROM User").list(); // 使用HQL查询
			// QBC的方式
			Criteria criteria = session.createCriteria(User.class);
			// criteria.add(Restrictions.eq("name", "张三"));
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
	 * 分页的查询
	 * 
	 * @param firstResult
	 *            开始获取的记录的索引
	 * @param maxResults
	 *            最多获取多少条数据
	 * @return 总记录数 + 一段数据
	 */
	public QueryResult<User> findAll(int firstResult, int maxResults) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// -------------------------------------
			// 1，查询总记录数
			Long count = (Long) session.createQuery("SELECT COUNT(*) FROM User").uniqueResult(); // 执行查询

			// 2，查询一段数据
			Query query = session.createQuery("FROM User");
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResults);
			List<User> list = query.list(); // 执行查询

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
