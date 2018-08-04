package com.rr.ams.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.rr.ams.domain.Account;
import com.rr.ams.tools.JDBCUtils;

/*
 *  ʵϰ�����ݱ�ams_rr����ɾ�Ĳ����
 *  DBUtils��������ɣ����Ա����QueryRunner����ָ������Դ
 */
public class AccountDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	// �������
	public void addAccount(Account ac) {
		String sql = "INSERT INTO ams_rr(flname, money, zhanghu, createtime, description)" +
				"VALUES(?, ?, ?, ?, ?)";
		Object[] params = {ac.getFlname(), ac.getMoney(), ac.getZhanghu(), ac.getCreatetime(), ac.getDescription()};
		try {
		qr.update(sql, params);
		} catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("�������ʧ��");
		}
	}
	
	// �༭����
	public void editAccount(Account ac) {
		// �������ݵ�SQL
		String sql = "UPDATE ams_rr SET flname = ?, money = ?, zhanghu = ?, createtime = ?, "
				+ "description = ? WHERE zwid = ?";
		Object[] params = {ac.getFlname(), ac.getMoney(), ac.getZhanghu(), ac.getCreatetime(), 
					ac.getDescription(), ac.getZwid()};
		try {
		qr.update(sql, params);
		} catch (SQLException ex) {
			System.out.println(ex);
			System.out.println("����༭ʧ��");
		}
		
	}
	
	// ɾ������
	public void deleteAccount(int zwid) {
		String sql = "DELETE FROM ams_rr WHERE zwid=?";
		try {
		qr.update(sql, zwid);
		} catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("ɾ������ʧ��!");
		}
	}
	/*
	 *  ��ѯ���ݿ⣬��ȡ���е���������
	 *  ��ҵ������
	 */
	public List<Account> selectAll() {
		try {
			// ��ѯ�����������ݵ�sql���
			String sql = "SELECT * FROM ams_rr";
			// ����qr�����query�����������BeanListHandler
			List<Account> list = qr.query(sql, new BeanListHandler<>(Account.class));
			return list;
		} catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("��ѯ������������ʧ��");
		}
	}
	
	/*
	 *  ��ѯ���ݿ⣬��ȡָ��ʱ���ڵ�bean����
	 */
	public List<Account> select(String startDate, String endDate) {
		// ƴд������ѯ��SQL���
		String sql = "SELECT * FROM ams_rr WHERE Createtime BETWEEN ? AND ?";
		// ����������飬�洢?ռλ��
		Object[] params = {startDate, endDate};
		try {
			// ����qr�����query��������ȡ�����
			return qr.query(sql, new BeanListHandler<>(Account.class), params);
		} catch(SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("������ѯʧ��");
		}
		
	}
}
