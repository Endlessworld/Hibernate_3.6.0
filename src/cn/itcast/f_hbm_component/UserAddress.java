package cn.itcast.f_hbm_component;

public class UserAddress {

	private String address;
	private String code;
	private String phone;

	public UserAddress() {
	}

	public UserAddress(String address, String code, String phone) {
		this.address = address;
		this.code = code;
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
