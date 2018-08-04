package com.rr.ams.view;

import java.util.List;
import java.util.Scanner;

import com.rr.ams.controller.AccountController;
import com.rr.ams.domain.Account;

/*
 *  视图层，用户可见操作界面
 *  数据传递给controller层实现
 *  成员位置:创建controller对象
 */
public class MainView {
	private AccountController controller = new AccountController();
	
	/*
	 *  实现界面效果
	 *  接收用户输入
	 *  接收数据，调用不同的功能方法
	 */
	public void run() {
		// 创建Scanner对象，反复键盘输入
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out.println("---------------账务管理软件---------------");
			System.out.println("1.添加账务　2.编辑账务　3.删除账务　4.查询账务　5.退出系统");
			System.out.println("请输入要操作的功能序号[1-5]:");
			int op = scanner.nextInt();
			switch (op) {
			
			case 1:
				// 添加账务，调用添加账务的方法
				addAccount();
				break;
			
			case 2:
				// 编辑账务，调用编辑账务的方法
				editAccount();
				break;
			case 3:
				// 删除账务，调用删除账务的方法
				deleteAccount();
				break;
			case 4:
				// 查询账务，调用查询账务的方法
				selectAccount();
				break;
			case 5:
				System.out.println("再见,感谢您的使用!");
				flag = false;
				break;
			default:
				System.out.println("输入错误！");
			}
		}
		
	}
	
	/*
	 *  定义方法addAccount()
	 *  添加账务，选菜单项1时调用
	 *  接收用户输入的项，调用controller方法
	 */
	public void addAccount() {
		System.out.println("选择了添加账务的选项，请依次输入以下内容");
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入分类名称");
		String flname = scanner.next();
		System.out.println("输入金额");
		double money = scanner.nextDouble();
		System.out.println("输入账户");
		String zhanghu = scanner.next();
		System.out.println("输入日期:xxxx-xx-xx");
		String createtime = scanner.next();
		System.out.println("输入描述");
		String description = scanner.next();
		// 将接收到的数据，传到controller层
		// 将用户输入的参数封装成一个对象
		Account ac = new Account(0, flname, money, zhanghu, createtime, description);
		controller.addAccount(ac);
		System.out.println("添加成功!");
	}
	
	/*
	 *  定义方法editAccount()
	 *  添加账务，选菜单项2时调用
	 *  接收用户输入的项，调用controller方法
	 */
	public void editAccount() {
		// 调用查询所有账务的功能，显示出来
		selectAll();
		System.out.println("选择的是编辑功能，请输入账务项数据");
		Scanner scanner = new Scanner(System.in);
		System.out.println("输入ID:");
		int zwid = scanner.nextInt();
		System.out.println("请输入分类名称");
		String flname = scanner.next();
		System.out.println("输入金额");
		double money = scanner.nextDouble();
		System.out.println("输入账户");
		String zhanghu = scanner.next();
		System.out.println("输入日期:xxxx-xx-xx");
		String createtime = scanner.next();
		System.out.println("输入描述");
		String description = scanner.next();
		// 将用户输入的参数封装成一个对象
		Account ac = new Account(zwid, flname, money, zhanghu, createtime, description);
		// 调用controller层方法实现编辑
		controller.editAccount(ac);
		System.out.println("账务编辑成功!");
	}
	
	/*
	 * 选项3，根据ID号删除某个账务项
	 */
	public void deleteAccount() {
		selectAll();
		System.out.println("选择的是删除功能，输入序号");
		int zwid = new Scanner(System.in).nextInt();
		// 调用控制层方法，传递主键id即可
		controller.deleteAccount(zwid);
		System.out.println("删除账务成功!");
	}
	
	/*
	 *  定义方法selectAccount()
	 *  显示查询方法 1.所有查询 2.条件查询
	 *  接收用户的选择
	 */
	public void selectAccount() {
		System.out.println("1.查询所有 2.按条件查询 ");
		Scanner scanner = new Scanner(System.in);
		int selectChooser = scanner.nextInt();
		// 根据用户的选择，调用不同的功能
		if (selectChooser == 1) {
			selectAll();
		} 
		else if (selectChooser == 2) {
			select();
		}
		else {
			System.out.println("输入不合格");
		}
	}
	
	/*
	 *  定义方法selectAll，查询所有
	 */
	public void selectAll() {
		 List<Account> list = controller.selectAll();
		 printAccount(list);
	}

	
	/*
	 *  定义方法select，按条件查询
	 *  用户输入启止日期
	 *  2个日期，传递给controller层
	 *  调用controller方法，返回结果集
	 */
	public void select() {
		System.out.println("按日期条件查询，格式为xxxx-xx-xx");
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入开始日期");
		String startDate = scanner.nextLine();
		System.out.println("请输入结束日期");
		String endDate = scanner.nextLine();
		
		// 调用controller方法返回结果集
		List<Account> list = controller.select(startDate, endDate);
		 printAccount(list);
	}

	// 输出账务数据表项
	private void printAccount(List<Account> list) {
		if (list.size() != 0) {
			// 输出表头
			 System.out.println("ID\t\t类别\t\t账户\t\t金额\t\t时间\t\t描述");
			 // 输出集合，结果输出控制台
			 for (Account ac: list) {
				 System.out.println(ac.getZwid() + "\t\t" + ac.getFlname() + "\t\t" +
			          ac.getZhanghu() + "\t\t" + ac.getMoney() + "\t\t" + ac.getCreatetime() + "\t" +
						 ac.getDescription());
			 }
		}
		else
			System.out.println("没有查询到相关数据");
	}
}