package cn.itcast.utils;

import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;

import cn.itcast.entity.TableStatus;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 封装常用的操作
 * @author Jie.Yuan
 *
 */
public class JdbcUtils {

	// 初始化连接池
	private static DataSource dataSource;
	static {
		dataSource = new ComboPooledDataSource();
	}
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * 创建DbUtils常用工具类对象
	 */
	public static QueryRunner getQuerrRunner() {
		return new QueryRunner(dataSource);
	}
	
	public static void main(String[] args) {
		TableStatus ts = TableStatus.Free;
		System.out.println(ts.ordinal());
		//注意一下1.8版本的jar包 driver驱动为com.mysql.cj.jdbc.Driver,有.cj,但下面不带才能正常执行
//		QueryRunner run=getQuerrRunner();
//		Object[] query;
//		try {
//			query = run.query("select typeName from foodType;" , new ArrayHandler());
//			String string=Arrays.toString(query);
//		    System.out.println(string);
//		    System.out.println("数据库获取成功");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("数据库获取失败");
//		}
		
		
	}
}








