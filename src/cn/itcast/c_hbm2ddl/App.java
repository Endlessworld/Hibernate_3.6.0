package cn.itcast.c_hbm2ddl;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import cn.itcast.a_helloworld.User;

public class App {

	
	@Test
	public void testHbm2ddl() throws Exception {
		Configuration cfg = new Configuration()//
				.configure()//
				.addClass(User.class);
		// 自动建表或删除表的工具类SchemaExport
		SchemaExport schemaExport = new SchemaExport(cfg);
		// 第1个参数script的作用： print the DDL to the console
		// 第2个参数export的作用： export the script to the database
		schemaExport.create(true, true); // 自动建表
	}
}
