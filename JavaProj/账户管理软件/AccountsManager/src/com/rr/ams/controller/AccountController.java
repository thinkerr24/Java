package com.rr.ams.controller;

import java.util.List;

import com.rr.ams.domain.Account;
import com.rr.ams.service.AccountService;

/*
 *  控制器层
 *  接收视图层数据，数据传给service层
 *  成员变量位置要创建service对象
 */
public class AccountController {
	private AccountService service = new AccountService();
	
	public void addAccount(Account ac) {
		service.addAccount(ac);
	}
	
	public void editAccount(Account ac) {
		service.editAccount(ac);
	}
	
	public void deleteAccount(int zwid) {
		service.deleteAccount(zwid); 
	}
	/*
	 * 	查询所有的账务数据
	 */
	 public List<Account> selectAll() {
		 return service.selectAll();
	 }
	 
	 /*
	  *  按条件查询，传递两个日期的字符串
	  */
	 public List<Account> select(String startDate, String endDate) {
		return service.select(startDate, endDate);
	 }
}
