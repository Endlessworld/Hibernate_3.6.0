package cn.itcast.e_hbm_id_2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {

	private static SessionFactory sessionFactory = new Configuration()//
			.configure()//
			.addClass(User.class)//
			.buildSessionFactory();

	// 保存
	@Test
	public void testSave() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------
		User user = new User();
		user.setFirstName("李");
		user.setLastName("五");
		user.setGender(true);
		session.save(user); // 保存
		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}

	// 获取
	@Test
	public void testGet() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		User id = new User();
		id.setFirstName("李");
		id.setLastName("四");

		User user = (User) session.get(User.class, id);
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getGender());

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}
}
