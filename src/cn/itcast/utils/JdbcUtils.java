package cn.itcast.utils;

import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;

import cn.itcast.entity.TableStatus;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ��װ���õĲ���
 * @author Jie.Yuan
 *
 */
public class JdbcUtils {

	// ��ʼ�����ӳ�
	private static DataSource dataSource;
	static {
		dataSource = new ComboPooledDataSource();
	}
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * ����DbUtils���ù��������
	 */
	public static QueryRunner getQuerrRunner() {
		return new QueryRunner(dataSource);
	}
	
	public static void main(String[] args) {
		TableStatus ts = TableStatus.Free;
		System.out.println(ts.ordinal());
		//ע��һ��1.8�汾��jar�� driver����Ϊcom.mysql.cj.jdbc.Driver,��.cj,�����治����������ִ��
//		QueryRunner run=getQuerrRunner();
//		Object[] query;
//		try {
//			query = run.query("select typeName from foodType;" , new ArrayHandler());
//			String string=Arrays.toString(query);
//		    System.out.println(string);
//		    System.out.println("���ݿ��ȡ�ɹ�");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("���ݿ��ȡʧ��");
//		}
		
		
	}
}








