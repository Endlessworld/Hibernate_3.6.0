package cn.itcast.j_hbm_oneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {

	private static SessionFactory sessionFactory = new Configuration()//
			.configure()//
			.addClass(Person.class)//
			.addClass(IdCard.class)//
			.buildSessionFactory();

	// 保存（关联起来）
	@Test
	public void testSave() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		// 创建对象
		Person person = new Person();
		person.setName("张三");

		IdCard idCard = new IdCard();
		idCard.setNumber("01111222");

		// 关联起来
		// 使用基于外键的一对一时：Person是无外键方，不可以维护关联关系
		// 使用基于外键的一对一时：IdCard是有外键方，可以维护关联关系
		// 使用基于主键的一对一时：也是只有有外键方可以保存关联关系
		// person.setIdCard(idCard);
		idCard.setPerson(person);

		// 保存
		session.save(idCard);
		session.save(person);

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

		// 获取一个Student，并显示相关的Teacher信息
		Person person = (Person) session.get(Person.class, 1);
		System.out.println("姓名：" + person.getName());
		System.out.println("身份证信息：" + person.getIdCard());

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}

	// 移除关联关系
	// 使用基于主键的一对一时，双方都不可以移除关联关系
	@Test
	public void testRemoveRelation() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		// // 获取一个Person，并移除与IdCard的关联
		// 使用基于外键的一对一时：Person是无外键方，不可以维护关联关系
		// Person person = (Person) session.get(Person.class, 1);
		// person.setIdCard(null);

		// 获取一个IdCard，并移除与的Person关联
		// 使用基于外键的一对一时：IdCard是有外键方，可以维护关联关系
		IdCard idCard = (IdCard) session.get(IdCard.class, 1);
		idCard.setPerson(null);

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}
 
	// 删除（测试对关联对象的影响）
	@Test
	public void testDelete() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		// 因为Person是无外键言，不可以维护关联关系，所以删除Person时，如果有关联的IdCard，就会抛异常
		Person person = (Person) session.get(Person.class, 3);
		session.delete(person);

		// // 删除IdCard
		// IdCard idCard = (IdCard) session.get(IdCard.class, 2);
		// session.delete(idCard);

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}

}
