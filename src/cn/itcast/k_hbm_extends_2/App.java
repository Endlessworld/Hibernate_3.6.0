package cn.itcast.k_hbm_extends_2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {

	private static SessionFactory sessionFactory = new Configuration()//
			.configure()//
			.addClass(Article.class)//
			.buildSessionFactory();

	// 保存
	@Test
	public void testSave() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		// 创建对象
		Article article = new Article();
		article.setTitle("这是一个Article");

		Topic topic = new Topic();
		topic.setTitle("这是一个主题");
		topic.setType(1); // 假设1表示精华帖

		Reply reply = new Reply();
		reply.setTitle("这是一个回复");
		reply.setFloor(1); // 1楼

		// 保存
		session.save(article);
		session.save(topic);
		session.save(reply);

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}

	// 获取（可以获取到关联的对方）
	@Test
	public void testGet() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		// 根据具体的类型获取
		Article article = (Article) session.get(Article.class, 1);
		Topic topic = (Topic) session.get(Topic.class, 2);
		Reply reply = (Reply)session.get(Reply.class, 3);
		System.out.println(article);
		System.out.println(topic);
		System.out.println(reply);
		System.out.println("---");
		
		// 根据父类类型获取子类数据
		Article article1 = (Article) session.get(Article.class, 2);
		Article article2 = (Article)session.get(Article.class, 3);
		System.out.println(article1);
		System.out.println(article2);
		
		
		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}

}
