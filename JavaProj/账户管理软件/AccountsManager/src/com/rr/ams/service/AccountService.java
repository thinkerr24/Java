package com.rr.ams.service;

import java.util.List;

import com.rr.ams.dao.AccountDao;
import com.rr.ams.domain.Account;

/*
 *  ҵ���
 *  ������һ��(���Ʋ�controller)����
 *  �������㣬���ݸ�dao�㣬�������ݿ�
 *  ���Աλ��:����dao���е��࣬����Dao�����
 */
public class AccountService {
	private AccountDao dao = new AccountDao();
	
	/*
	 * �ɿ��Ʋ���ã��������dao�㷽��
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
