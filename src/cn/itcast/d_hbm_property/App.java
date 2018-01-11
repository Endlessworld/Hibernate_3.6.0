package cn.itcast.d_hbm_property;

import java.io.FileInputStream;
import java.util.Date;

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

		// 读一个图片文件
		FileInputStream in = new FileInputStream("c:/myPhoto.png");
		byte[] photo = new byte[in.available()];
		in.read(photo);
		in.close();

		// 准备对象
		User user = new User();
		user.setName("李四");
		user.setGender(true);
		user.setBirthday(new Date());
		user.setDesc("一大段的说明，此处省略1000字...");
		user.setPhoto(photo);

		// 保存
		session.save(user);

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
		System.out.println(user.getGender());
		System.out.println(user.getBirthday());
		System.out.println(user.getDesc());
		System.out.println(user.getPhoto());

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}
}
