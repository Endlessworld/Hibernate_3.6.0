package cn.itcast.b_dao;

import java.util.List;

public class QueryResult<T> {
	private List<T> list; // 一段数据
	private Long count; // 总记录数

	public QueryResult(List<T> list, Long count) {
		this.list = list;
		this.count = count;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
