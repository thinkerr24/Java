package com.rr.ams.service;

import java.util.List;

import com.rr.ams.dao.AccountDao;
import com.rr.ams.domain.Account;

/*
 *  业务层
 *  接收上一层(控制层controller)数据
 *  经过计算，传递给dao层，操作数据库
 *  类成员位置:调用dao层中的类，创建Dao类对象
 */
public class AccountService {
	private AccountDao dao = new AccountDao();
	
	/*
	 * 由控制层调用，本类调用dao层方法
	 */
	
	public void addAccount(Account ac) {
		dao.addAccount(ac);
	}
	
	public void editAccount(Account ac) {
		dao.editAccount(ac);
	}
	
	public void deleteAccount(int zwid) {
		dao.deleteAccount(zwid);
	}
	
	public List<Account> selectAll() {
		 return dao.selectAll();
	}
	
	public List<Account> select(String startDate, String endDate) {
		return dao.select(startDate, endDate);
	}

}
