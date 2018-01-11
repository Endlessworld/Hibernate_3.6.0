package cn.itcast.g_hbm_collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class User {
	private Integer id;
	private String name;

	private Set<String> addressSet;
	private List<String> addressList = new ArrayList<String>();
	private Map<String, String> addressMap = new HashMap<String, String>();
	private String[] addressArray;
	private Collection<String> addressBag = new ArrayList<String>(); // 使用Bag时，可以声明为Collection或是List

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

	public Set<String> getAddressSet() {
		return addressSet;
	}

	public void setAddressSet(Set<String> addressSet) {
		this.addressSet = addressSet;
	}

	public List<String> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}

	public Map<String, String> getAddressMap() {
		return addressMap;
	}

	public void setAddressMap(Map<String, String> addressMap) {
		this.addressMap = addressMap;
	}

	public String[] getAddressArray() {
		return addressArray;
	}

	public void setAddressArray(String[] addressArray) {
		this.addressArray = addressArray;
	}

	public Collection<String> getAddressBag() {
		return addressBag;
	}

	public void setAddressBag(Collection<String> addressBag) {
		this.addressBag = addressBag;
	}

}
