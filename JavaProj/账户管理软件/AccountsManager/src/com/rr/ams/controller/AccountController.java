package com.rr.ams.controller;

import java.util.List;

import com.rr.ams.domain.Account;
import com.rr.ams.service.AccountService;

/*
 *  ��������
 *  ������ͼ�����ݣ����ݴ���service��
 *  ��Ա����λ��Ҫ����service����
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
	 * 	��ѯ���е���������
	 */
	 public List<Account> selectAll() {
		 return service.selectAll();
	 }
	 
	 /*
	  *  ��������ѯ�������������ڵ��ַ���
	  */
	 public List<Account> select(String startDate, String endDate) {
		return service.select(startDate, endDate);
	 }
}
