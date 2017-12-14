package com.gpf.testES;

public class Student {

	private String name;
	private Integer age;
	private String sex;
	private String address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	public Student() { }
	public Student(String name, Integer age, String sex, String address) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.address = address;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", sex=" + sex
				+ ", address=" + address + "]";
	}
	
	
}
