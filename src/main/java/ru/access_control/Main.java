package ru.access_control;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    private static String url = "jdbc:postgresql://";
    private static String port = "5432";
    private static String host = "localhost";
    private static String pwd = "563453";
    private static String user = "postgres";
    private static String db  = "access_control_db";

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-config.xml");

        CheckPoint checkPoint = (CheckPoint) context.getBean("checkPoint");

        BasicDataSource basicDataSource = (BasicDataSource) context.getBean("dataSource");

        for (int i = 0; i < args.length; i++) {
            switch (args[i]){
                case "-p":
                    port = args[++i];
                    break;
                case "-h":
                    host = args[++i];
                    break;
                case "-pwd":
                    pwd = args[++i];
                    break;
                case "-user":
                    user = args[++i];
                    break;
                case "-db":
                    db = args[++i];
                    break;
            }
        }

        System.out.println(url+host + ":" + port + "/" + db);
        System.out.println(user + "     " + pwd);

        basicDataSource.setUrl(url+host + ":" + port + "/" + db);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(pwd);


        checkPoint.run();
    }
}
