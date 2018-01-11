package cn.itcast.m_session_cache1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {

	private static SessionFactory sessionFactory = new Configuration()//
			.configure()//
			.addClass(User.class)// 加载指定类对应的映射文件（以类名为前缀，后缀为.hbm.xml的同一个包下的文件）
			.buildSessionFactory();

	@Test
	public void testRefresh() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		// 获取对象
		User user = (User) session.get(User.class, 3);
		System.out.println(user.getName());

		// 刷新对象的状态（从数据库中再获取一次最新状态）
		session.refresh(user);
		System.out.println(user.getName());

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}

	// 大量保存数据并且没有内存溢出的异常
	@Test
	public void testBatchSave() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		for (int i = 0; i < 50; i++) {
			User user = new User();
			user.setName("test-" + i);
			session.save(user); // 保存

			// 应适时的请空缓存，以释放内存
			if (i % 10 == 0) {
				System.out.println("刷出");
				session.flush();
				session.clear();
			}
		}

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}

}
