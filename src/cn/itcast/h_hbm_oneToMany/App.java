package cn.itcast.h_hbm_oneToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {

	private static SessionFactory sessionFactory = new Configuration()//
			.configure()//
			.addClass(Department.class)//
			.addClass(Employee.class)//
			.buildSessionFactory();

	// 保存（关联起来）
	@Test
	public void testSave() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		// 创建对象
		Department department = new Department();
		department.setName("开发部");

		Employee employee1 = new Employee();
		employee1.setName("张三");

		Employee employee2 = new Employee();
		employee2.setName("李四");

		// 关联起来
		employee1.setDepartment(department);
		employee2.setDepartment(department);
		department.getEmployees().add(employee1);
		department.getEmployees().add(employee2);

		// 保存
		session.save(department); // 保存部门
		// session.save(employee1);
		// session.save(employee2);

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

		// 获取部门，并显示关联的员工信息
		Department department = (Department) session.get(Department.class, 3);
		System.out.println("部门名称：" + department.getName());
		System.out.println("关联的员工：" + department.getEmployees());

		// // 获取员工，并显示关联的部门信息
		// Employee employee = (Employee) session.get(Employee.class, 1);
		// System.out.println("员工姓名：" + employee.getName());
		// System.out.println("所属的部门：" + employee.getDepartment());

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

		// // 让员工不再属于原部门
		// Employee employee = (Employee) session.get(Employee.class, 1);
		// employee.setDepartment(null);

		// 让Department不与任何Employee关联
		Department department = (Department) session.get(Department.class, 8);
		department.getEmployees().clear();

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

		// // 删除员工，对关联的部门没有影响
		// Employee employee = (Employee) session.get(Employee.class, 1);
		// session.delete(employee);

		// // 删除部门，如果有关联的员工，则会：
		// 1，inverse="false"，这也是默认值，表示本方能维护关联关系（外键），这时会先把所有关联的员工都移除关系，再删除自己。
		// 2，inverse="true"，表示本方不能维护关联关系（外键），就会直接删除自己，这时会抛异常。
		// 3，如果cascade="delete"，不管inverse设为什么，都能级联删除相关的Employee信息
		Department department = (Department) session.get(Department.class, 7);
		session.delete(department);

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}

}
