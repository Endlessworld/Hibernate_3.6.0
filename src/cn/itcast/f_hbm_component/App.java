package cn.itcast.f_hbm_component;

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
		user.setName("张三");
		user.setUserAddress(new UserAddress("XX科贸园", "888", "022-xxxxxx"));
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


		User user = (User) session.get(User.class, 1);
		System.out.println(user.getName());
		System.out.println(user.getUserAddress().getAddress());

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}
}
