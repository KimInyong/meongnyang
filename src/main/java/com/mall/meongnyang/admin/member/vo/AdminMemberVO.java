package com.mall.meongnyang.admin.member.vo;

import java.sql.Date;

import javax.xml.crypto.Data;

public class AdminMemberVO {

	private int customerTbNo;
	private String customerTbName; 
	private String customerTbPassword; 
	private String customerTbEmail;
	private String customerTbPhone;
	private Date customerTbJoinDate;
	private int customerTbPoint; 
	private Date customerTbLastLogin; 
	private Date customerTbPwChange;
	private String customerTbJoinPath;
	private String customerTbState;
	
	public int getCustomerTbNo() {
		return customerTbNo;
	}
	public void setCustomerTbNo(int customerTbNo) {
		this.customerTbNo = customerTbNo;
	}
	public String getCustomerTbName() {
		return customerTbName;
	}
	public void setCustomerTbName(String customerTbName) {
		this.customerTbName = customerTbName;
	}
	public String getCustomerTbPassword() {
		return customerTbPassword;
	}
	public void setCustomerTbPassword(String customerTbPassword) {
		this.customerTbPassword = customerTbPassword;
	}
	public String getCustomerTbEmail() {
		return customerTbEmail;
	}
	public void setCustomerTbEmail(String customerTbEmail) {
		this.customerTbEmail = customerTbEmail;
	}
	public String getCustomerTbPhone() {
		return customerTbPhone;
	}
	public void setCustomerTbPhone(String customerTbPhone) {
		this.customerTbPhone = customerTbPhone;
	}
	public Date getCustomerTbJoinDate() {
		return customerTbJoinDate;
	}
	public void setCustomerTbJoinDate(Date customerTbJoinDate) {
		this.customerTbJoinDate = customerTbJoinDate;
	}
	public int getCustomerTbPoint() {
		return customerTbPoint;
	}
	public void setCustomerTbPoint(int customerTbPoint) {
		this.customerTbPoint = customerTbPoint;
	}
	public Date getCustomerTbLastLogin() {
		return customerTbLastLogin;
	}
	public void setCustomerTbLastLogin(Date customerTbLastLogin) {
		this.customerTbLastLogin = customerTbLastLogin;
	}
	public Date getCustomerTbPwChange() {
		return customerTbPwChange;
	}
	public void setCustomerTbPwChange(Date customerTbPwChange) {
		this.customerTbPwChange = customerTbPwChange;
	}
	public String getCustomerTbJoinPath() {
		return customerTbJoinPath;
	}
	public void setCustomerTbJoinPath(String customerTbJoinPath) {
		this.customerTbJoinPath = customerTbJoinPath;
	}
	public String getCustomerTbState() {
		return customerTbState;
	}
	public void setCustomerTbState(String customerTbState) {
		this.customerTbState = customerTbState;
	} 
	
	
	

}
