package cn.itcast.a_helloworld;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {

	private static SessionFactory sessionFactory;

	static {
		// 读取配置文件并生成Session工厂对象
		Configuration cfg = new Configuration();
		// cfg.configure("hibernate.cfg.xml"); // 加载指定的配置文件
		// cfg.configure(); // 读取默认的配置文件（hibernate.cfg.xml）
		// cfg.addResource("cn/itcast/a_helloworld/User.hbm.xml");
		// cfg.addClass(User.class); //
		// sessionFactory = cfg.buildSessionFactory();

		sessionFactory = new Configuration()//
				.configure()//
				.addClass(User.class)// 加载指定类对应的映射文件（以类名为前缀，后缀为.hbm.xml的同一个包下的文件）
				.buildSessionFactory();
	}

	// 保存对象到数据库
	@Test
	public void testSave() throws Exception {
		// 准备对象
		User user = new User();
		user.setName("张三");

		// 保存到数据库中
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user); // 保存
		tx.commit();
		session.close();
	}

	// 从数据库中获取一条数据
	@Test
	public void testGet() throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user = (User) session.get(User.class, 1); // 从数据库中获取id为1的User数据
		tx.commit();
		session.close();

		System.out.println(user); // 显示信息
	}
}
