package cn.itcast.i_hbm_manyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {

	private static SessionFactory sessionFactory = new Configuration()//
			.configure()//
			.addClass(Teacher.class)//
			.addClass(Student.class)//
			.buildSessionFactory();

	// 保存（关联起来）
	@Test
	public void testSave() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		// 创建对象
		Teacher teacher1 = new Teacher();
		teacher1.setName("赵老师");

		Teacher teacher2 = new Teacher();
		teacher2.setName("冯老师");

		Student student1 = new Student();
		student1.setName("张三");

		Student student2 = new Student();
		student2.setName("李四");

		// 关联起来
		teacher1.getStudents().add(student1);
		teacher1.getStudents().add(student2);
		teacher2.getStudents().add(student1);
		teacher2.getStudents().add(student2);

		student1.getTeachers().add(teacher1);
		student1.getTeachers().add(teacher2);
		student2.getTeachers().add(teacher1);
		student2.getTeachers().add(teacher2);

		// 保存
		session.save(teacher1);
		session.save(teacher2);
		session.save(student1);
		session.save(student2);

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
		Student student = (Student) session.get(Student.class, 3);
		System.out.println("学生的姓名：" + student.getName());
		System.out.println("此学生的老师：" + student.getTeachers());

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}

	// 移除关联关系
	@Test
	public void testRemoveRelation() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		// 获取一个Student，并移除与所有老师的关联
		Student student = (Student) session.get(Student.class, 5);
		student.getTeachers().clear();

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

		// 删除一个Student，如果有关联的老师，则：
		// 如果inverse="false"，表示可以维护关联关系，这时会先删除关系，再删除自己。
		// 如果inverse="true"，表示不可以维护关联关系，这时会直接删除自己，会有异常。
		Student student = (Student) session.get(Student.class, 5);
		session.delete(student);

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}

}
