package tk.mybatis.springboot.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Aidon on 17/9/25.
 */
public class ThreadImport extends Thread {
    private String url = "jdbc:mysql://127.0.0.1/test";
    private String user = "root";
    private String password = "root";

    public Connection getConnect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public void multiThreadImport(final int ThreadNum) {
        final CountDownLatch cdl = new CountDownLatch(ThreadNum);
        long starttime = System.currentTimeMillis();
        for (int k = 1; k <= ThreadNum; k++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Connection con = getConnect();
                    try {
                        Statement st = con.createStatement();
                        for (int i = 1; i <= 80000 / ThreadNum; i++) {
                            String uuid = UUID.randomUUID().toString();
                            st.addBatch("insert into test.test(id,name) " +
                                    "values('" + uuid + "','" + i + "')");
                            if (i % 500 == 0) {
                                st.executeBatch();
                            }
                        }

                    } catch (Exception e) {
                    } finally {
                        cdl.countDown();
                        try {
                            con.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        try {
            cdl.await();
            long spendtime = System.currentTimeMillis() - starttime;
            System.out.println(ThreadNum + "个线程花费时间:" + spendtime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadImport ti = new ThreadImport();
        ti.multiThreadImport(10);
        System.out.println("笔记本CPU数:" + Runtime.getRuntime().availableProcessors());
    }


}
