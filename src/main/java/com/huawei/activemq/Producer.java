package com.huawei.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by dllo on 17/11/28.
 */
public class Producer {

    // 获取activeMQ默认的登录用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    // 获取activeMQ默认的登录弥漫
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    // 获取activeMQ默认的BROKER_URL
    private static final String BROKER = ActiveMQConnection.DEFAULT_BROKER_URL;


    public void sendMessage(String queueName) {

        try {
            // 1.创建一个连接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER);

            // 2.创建一个连接
            Connection connection = connectionFactory.createConnection();

            // 3.开启连接
            connection.start();

            // 4.获取事物
            Session session = connection.createSession(true, Session.SESSION_TRANSACTED);

            // 5.创建消息队列
            Queue queue = session.createQueue(queueName);

            // 6.消息生成器
            MessageProducer messageProducer = session.createProducer(queue);

            while (true) {

                Thread.sleep(500);
                // 7.创建消息
                TextMessage textMessage = session.createTextMessage("这是一条消息");


                // 8.发送消息
                messageProducer.send(textMessage);

                // 9.提交事物
                session.commit();

            }


        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        Producer producer = new Producer();
//        producer.sendMessage("0703");
//        System.out.println(LastRemaining_Solution(3, 2));
//        System.out.println(numberOf1Between1AndN_Solution(52));
//        System.out.println(Add(-3, -1));
//        System.out.println(leftRotateString("", 3));
//        int[] a = {1,2,3,2,2};
//        System.out.println(moreThanHalfNum_Solution(a));
        String str = "hello";
        System.out.println(getLastLength(str));

    }

    public static int LastRemaining_Solution(int n, int m) {
        if (n == 0) return -1;

        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = (result + m) % i;
        }
        return result;

    }

    public static int numberOf1Between1AndN_Solution(int n) {


        int count = 0;
        for (int i = 1; i <= n; i++) {
            String str = String.valueOf(i);
            if (str.contains("1")) {
                int i1 = str.length() - str.replace("1", "").length();
                count += i1;
            }
        }
        return count;
    }

    public static int Add(int num1, int num2) {
        if (num1 > 0) {
            while (num1-- != 0)
                num2++;
        } else {
            while (num1++ != 0)
                num2--;
        }
        return num2;
    }

    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int temp = 0;
        int a = 0;
        int b = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] + array[j] == sum) {
                    if (temp == 0) {
                        temp = array[i] * array[j];
                        a = array[i];
                        b = array[j];
                    } else {
                        if (temp > array[i] * array[j]) {
                            temp = array[i] * array[j];
                            a = array[i];
                            b = array[j];
                        }
                    }

                }
            }
        }
        if (temp != 0) {
            arrayList.add(a);
            arrayList.add(b);
        }
        return arrayList;
    }

    public static String leftRotateString(String str, int n) {
        if (str.length() == 0) {
            return str;
        }
        String substring = str.substring(n);
        String substring1 = str.substring(0, n);
        str = substring + substring1;
        return str;
    }

    public static int moreThanHalfNum_Solution(int [] array) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            str.append(array[i]);
        }
        for (int i = 0; i < array.length; i++) {
            int length = str.toString().replace(String.valueOf(array[i]), "").length();

            if (2*length < array.length){
                return array[i];
            }
        }

        return 0;
    }

    public static int getLastLength(String str){
        String[] strs = str.split(" ");
        return  strs[strs.length - 1].length();
    }


}
