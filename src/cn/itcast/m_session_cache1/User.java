package cn.itcast.m_session_cache1;

/**
 * 用户对象
 * 
 * @author tyg
 * 
 */
public class User {
	private Integer id;
	private String name;
	private byte[] data = new byte[1024 * 1000 * 5];

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "[User: id=" + id + ", name=" + name + "]";
	}

}
