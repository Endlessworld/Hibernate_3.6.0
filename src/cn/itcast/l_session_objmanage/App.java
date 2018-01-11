package cn.itcast.l_session_objmanage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {

	private static SessionFactory sessionFactory = new Configuration()//
			.configure()//
			.addClass(User.class)// ����ָ�����Ӧ��ӳ���ļ���������Ϊǰ׺����׺Ϊ.hbm.xml��ͬһ�����µ��ļ���
			.buildSessionFactory();

	// save()
	// ����ʱ״̬תΪ�־û�״̬
	// ���ɲ�ִ��һ��insert���
	// ֻ�������������ݿ�����ʱ��save(obj)ʱ��sql���Ż�����ִ�У��Ի�ȡ����ֵ
	@Test
	public void testSave() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		User user = new User(); // ��ʱ״̬
		user.setName("����");

		// ����OID
		System.out.println(user.getId()); // null
		session.save(user); // �־û�״̬
		System.out.println(user.getId()); // ��ֵ

		// // ���Գ־û�״̬������޸�
		// // �޸Ķ�������ջ�ͬ�������ݿ���
		// user.setName("����");
		// System.out.println("---");

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}

	// update()
	// ������״̬תΪ�־û�״̬
	// ������Update���
	@Test
	public void testUpdate() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		User user = (User) session.get(User.class, 1);
		// user.setName("aa");

		// ��Session�����ָ��������������������״̬��
		session.evict(user);
		user.setName("bb");

		// �ٴλ�ȡ���������Ϊ֮ǰ���Ѵ�Session��������������û��ʹ�û��棬����ȥ���ݿ���ȡ��
		User user2 = (User) session.get(User.class, 1);
		System.out.println(user == user2);

		// ������״̬����ŵ�Session������
		session.update(user);
		session.flush(); // ˢ������ʾ����ִ��SQL���
		System.out.println("---");

		// ---------------------------------------
		session.getTransaction().commit(); // ���ύʱ�����Զ�����ִ��flush()
		session.close();
	}

	// ����ʱҪ�����ݿ���һ��Ҫ�ж�Ӧid�ļ�¼���������쳣
	@Test
	public void testUpdate2() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		// ģ��һ������״̬�Ķ���
		User user = new User();
		user.setId(100);
		user.setName("abcd");

		session.update(user);

		// ---------------------------------------
		session.getTransaction().commit(); // ���ύʱ�����Զ�����ִ��flush()
		session.close();
	}

	// saveOrUpdate()
	// ����ʱ������״̬תΪ�־û�״̬
	// �������ʱ״̬��������insert���
	// ���������״̬��������update���
	// ��������ʲô״̬��Ĭ���ǿ�����ֵ��Ϊnull������ʱ����0��ԭʼ����ʱ��ʱ��ʾ��ʱ״̬�������������״̬��
	@Test
	public void testSaveOrUpdate() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		User user = new User();
		user.setId(1);
		user.setName("����2");

		session.saveOrUpdate(user);

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}

	// delete()
	// �ѳ־û�������״̬תΪɾ��״̬
	// ������һ��delete���
	// Ҫ�����ݿ���һ��Ҫ�ж�Ӧ�ļ�¼���������쳣��ɾ�����ɹ�
	@Test
	public void testDelete() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		// ���س�һ�����󣬾��ǳ־û�״̬
		// User user = (User) session.get(User.class, 1);

		// ģ��һ������״̬�Ķ���
		User user = new User();
		user.setId(200);

		session.delete(user);

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}

	// get()
	// ��ѯ�����ľ��ǳ־û�״̬����Ϊ��Session�У���
	// ����ִ�в�ѯ
	// ���û�ж�Ӧ��¼���ͷ���null
	// ��������ʹ�û��棬������û�У��Ų�ѯ���ݿ�
	@Test
	public void testGet() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		User user = (User) session.get(User.class, 3);
		User user2 = (User) session.get(User.class, 3);
		User user3 = (User) session.get(User.class, 3);
		System.out.println("---");
		System.out.println(user);

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}

	// load()
	// ��ѯָ��id������
	// ��������ִ�в�ѯ�������أ��ӳټ��أ������Ƿ���һ������������෽����������Ҫ����಻����final�ģ�����������ʧЧ��
	// ���ڵ�1�ε���������Ϣʱִ�С�
	// ����ʱ������ݲ����ڣ��ͻ��쳣��org.hibernate.ObjectNotFoundException: No row with the given identifier exists: [cn.itcast.l_session_objmanage.User#300]
	// ��������ʹ�û��棬������û�У��Ų�ѯ���ݿ�
	// �������Ƿ���Чȡ�������Ƿ���final�ģ���ӳ���ļ���lazy�Ƿ�Ϊtrue��
	@Test
	public void testLoad() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ---------------------------------------

		User user = (User) session.load(User.class, 3);
		System.out.println("---");
		// System.out.println(user);
		System.out.println(user.getName());
		// System.out.println(user.getId());
		// System.out.println(user.getClass());

		session.clear();
		User user2 = (User) session.load(User.class, 3);
		System.out.println(user2.getName());

		// ---------------------------------------
		session.getTransaction().commit();
		session.close();
	}
}
