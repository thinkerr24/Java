package com.rr.ams.view;

import java.util.List;
import java.util.Scanner;

import com.rr.ams.controller.AccountController;
import com.rr.ams.domain.Account;

/*
 *  ��ͼ�㣬�û��ɼ���������
 *  ���ݴ��ݸ�controller��ʵ��
 *  ��Աλ��:����controller����
 */
public class MainView {
	private AccountController controller = new AccountController();
	
	/*
	 *  ʵ�ֽ���Ч��
	 *  �����û�����
	 *  �������ݣ����ò�ͬ�Ĺ��ܷ���
	 */
	public void run() {
		// ����Scanner���󣬷�����������
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out.println("---------------����������---------------");
			System.out.println("1.�������2.�༭����3.ɾ������4.��ѯ����5.�˳�ϵͳ");
			System.out.println("������Ҫ�����Ĺ������[1-5]:");
			int op = scanner.nextInt();
			switch (op) {
			
			case 1:
				// ������񣬵����������ķ���
				addAccount();
				break;
			
			case 2:
				// �༭���񣬵��ñ༭����ķ���
				editAccount();
				break;
			case 3:
				// ɾ�����񣬵���ɾ������ķ���
				deleteAccount();
				break;
			case 4:
				// ��ѯ���񣬵��ò�ѯ����ķ���
				selectAccount();
				break;
			case 5:
				System.out.println("�ټ�,��л����ʹ��!");
				flag = false;
				break;
			default:
				System.out.println("�������");
			}
		}
		
	}
	
	/*
	 *  ���巽��addAccount()
	 *  �������ѡ�˵���1ʱ����
	 *  �����û�����������controller����
	 */
	public void addAccount() {
		System.out.println("ѡ������������ѡ�������������������");
		Scanner scanner = new Scanner(System.in);
		System.out.println("�������������");
		String flname = scanner.next();
		System.out.println("������");
		double money = scanner.nextDouble();
		System.out.println("�����˻�");
		String zhanghu = scanner.next();
		System.out.println("��������:xxxx-xx-xx");
		String createtime = scanner.next();
		System.out.println("��������");
		String description = scanner.next();
		// �����յ������ݣ�����controller��
		// ���û�����Ĳ�����װ��һ������
		Account ac = new Account(0, flname, money, zhanghu, createtime, description);
		controller.addAccount(ac);
		System.out.println("��ӳɹ�!");
	}
	
	/*
	 *  ���巽��editAccount()
	 *  �������ѡ�˵���2ʱ����
	 *  �����û�����������controller����
	 */
	public void editAccount() {
		// ���ò�ѯ��������Ĺ��ܣ���ʾ����
		selectAll();
		System.out.println("ѡ����Ǳ༭���ܣ�����������������");
		Scanner scanner = new Scanner(System.in);
		System.out.println("����ID:");
		int zwid = scanner.nextInt();
		System.out.println("�������������");
		String flname = scanner.next();
		System.out.println("������");
		double money = scanner.nextDouble();
		System.out.println("�����˻�");
		String zhanghu = scanner.next();
		System.out.println("��������:xxxx-xx-xx");
		String createtime = scanner.next();
		System.out.println("��������");
		String description = scanner.next();
		// ���û�����Ĳ�����װ��һ������
		Account ac = new Account(zwid, flname, money, zhanghu, createtime, description);
		// ����controller�㷽��ʵ�ֱ༭
		controller.editAccount(ac);
		System.out.println("����༭�ɹ�!");
	}
	
	/*
	 * ѡ��3������ID��ɾ��ĳ��������
	 */
	public void deleteAccount() {
		selectAll();
		System.out.println("ѡ�����ɾ�����ܣ��������");
		int zwid = new Scanner(System.in).nextInt();
		// ���ÿ��Ʋ㷽������������id����
		controller.deleteAccount(zwid);
		System.out.println("ɾ������ɹ�!");
	}
	
	/*
	 *  ���巽��selectAccount()
	 *  ��ʾ��ѯ���� 1.���в�ѯ 2.������ѯ
	 *  �����û���ѡ��
	 */
	public void selectAccount() {
		System.out.println("1.��ѯ���� 2.��������ѯ ");
		Scanner scanner = new Scanner(System.in);
		int selectChooser = scanner.nextInt();
		// �����û���ѡ�񣬵��ò�ͬ�Ĺ���
		if (selectChooser == 1) {
			selectAll();
		} 
		else if (selectChooser == 2) {
			select();
		}
		else {
			System.out.println("���벻�ϸ�");
		}
	}
	
	/*
	 *  ���巽��selectAll����ѯ����
	 */
	public void selectAll() {
		 List<Account> list = controller.selectAll();
		 printAccount(list);
	}

	
	/*
	 *  ���巽��select����������ѯ
	 *  �û�������ֹ����
	 *  2�����ڣ����ݸ�controller��
	 *  ����controller���������ؽ����
	 */
	public void select() {
		System.out.println("������������ѯ����ʽΪxxxx-xx-xx");
		Scanner scanner = new Scanner(System.in);
		System.out.println("�����뿪ʼ����");
		String startDate = scanner.nextLine();
		System.out.println("�������������");
		String endDate = scanner.nextLine();
		
		// ����controller�������ؽ����
		List<Account> list = controller.select(startDate, endDate);
		 printAccount(list);
	}

	// ����������ݱ���
	private void printAccount(List<Account> list) {
		if (list.size() != 0) {
			// �����ͷ
			 System.out.println("ID\t\t���\t\t�˻�\t\t���\t\tʱ��\t\t����");
			 // ������ϣ�����������̨
			 for (Account ac: list) {
				 System.out.println(ac.getZwid() + "\t\t" + ac.getFlname() + "\t\t" +
			          ac.getZhanghu() + "\t\t" + ac.getMoney() + "\t\t" + ac.getCreatetime() + "\t" +
						 ac.getDescription());
			 }
		}
		else
			System.out.println("û�в�ѯ���������");
	}
}