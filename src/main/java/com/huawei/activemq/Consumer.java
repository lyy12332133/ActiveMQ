package com.huawei.activemq;

import com.sun.deploy.util.StringUtils;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


import javax.jms.*;
import javax.jms.Queue;
import java.util.*;

/**
 * Created by dllo on 17/11/28.
 */
public class Consumer {


    // 获取activeMQ默认的登录用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    // 获取activeMQ默认的登录弥漫
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    // 获取activeMQ默认的BROKER_URL
    private static final String BROKER = ActiveMQConnection.DEFAULT_BROKER_URL;

    public Consumer() {
    }

    // 接收消息的方法
    public void receiveMessage(String queueName) {

        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER);

            Connection connection = connectionFactory.createConnection();

            connection.start();

            Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            Queue queue = session.createQueue(queueName);

            // 创建消息的消费者
            MessageConsumer messageConsumer = session.createConsumer(queue);

            while (true) {
                Thread.sleep(1000);
                // 接收消息
                TextMessage textMessage = (TextMessage) messageConsumer.receive();

                if (textMessage != null) {
                    // 告诉ActiveMQ, 消息处理完毕
                    textMessage.acknowledge();
                    System.out.println("处理消息: ===>" + textMessage.getText() + "<====");
                }
            }


        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static int a;
    public static void main(String[] args) {

        System.out.println(++a);
//        Consumer consumer = new Consumer();
//        consumer.receiveMessage("0703");
//        StringBuffer s1=new StringBuffer(10);s1.append("1234");
//        System.out.println(s1.length());
//        System.out.println(s1.capacity());
//        System.out.println(rectCover(2));
//        int[] a = {0, 3, 1, 6, 4};
//        System.out.println(minNumberInRotateArray(a));
//        reOrderArray(a);
//        replaceStr("syewwtsgprrkebafptmvicizqrsszltspftnbtkeairfpuumqzruiacrjvvvppcffjktpuctaifytzcityczvuhnekfuxfhxflldchzgzaneldaehovlwftesmthzdbsdzopkexmmfidcckjfpvuaagpeeyoeqwdzzomfumnfzmebooaaofhwvnmzikmrnhvvcoxukfdmbntszwvevzntzhmvntcrnsublqfrueygjzdeytkftppibxyhbqgwmlcpvqrm");
//        System.out.println(getLeastNumbers_Solution(a, 4));
//        findNumsAppearOnce(a, a, a);
//        System.out.println(strToInt("123"));
//        System.out.println(isContinuous(a));

    }

    public static int jumpFloor(int number) {
        if (number <= 0)
            return 0;
        else if (number == 1)
            return 1;
        else if (number == 2)
            return 2;
        else
            return jumpFloor(number - 1) + jumpFloor(number - 2);
    }

    public static String reverseSentence(String str) {
        if (str.trim().equals("")) {
            return str;
        } else {
            String[] strings = str.split(" ");
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = strings.length - 1; i >= 0; i--) {

                if (i != 0) {
                    stringBuffer.append(strings[i]).append(" ");
                } else {
                    stringBuffer.append(strings[i]);
                }
            }
            return stringBuffer.toString();
        }

    }

    public static String replaceSpace(StringBuffer str) {
        if (str.indexOf(" ") == -1) {
            return str.toString();
        }

        StringBuffer replace = str.replace(str.indexOf(" "), str.indexOf(" ") + 1, "%20");

        return replaceSpace(replace);
    }

    public static int rectCover(int target) {
        if (target == 1) {
            return 1;
        }
        return target + rectCover(--target);
    }

    public static boolean Find(int target, int[][] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (target == array[i][j]) {
                    return true;

                }
            }
        }
        return false;

    }

    public static int minNumberInRotateArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int a = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < a) {
                a = array[i];
            }
        }
        return a;
    }

    public double Power(double base, int exponent) {

        return Math.pow(base, exponent);
    }

    public static void reOrderArray(int[] array) {
        int[] array1 = new int[array.length];
        int[] array2 = new int[array.length];
        int array1Count = 0;
        int array2Count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 1) {
                array1[array1Count] = array[i];
                array1Count++;
            } else {
                array2[array2Count] = array[i];
                array2Count++;
            }
        }
        for (int i = 0; i < array1Count; i++) {
            array[i] = array1[i];
        }
        for (int i = 0; i < array2Count; i++) {
            array[array1Count + i] = array2[i];
        }
        System.out.println(Arrays.toString(array));

    }

    public static void replaceStr(String str) {
        Scanner sc = new Scanner(System.in);
        StringBuffer stringBuffer = new StringBuffer(sc.next());

        StringBuffer replace = stringBuffer.reverse();
        System.out.println(replace.toString());
    }

    public int GetNumberOfK(int[] array, int k) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == k) {
                count++;
            }
        }
        return count;
    }

    public static ArrayList<Integer> getLeastNumbers_Solution(int[] input, int k) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            list.add(input[i]);
        }
        if (k >= input.length) {
            return list;
        }
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int temp = list.get(0);
            for (int j = 0; j < list.size(); j++) {
                if (temp > list.get(j)) {
                    temp = list.get(j);
                    list.remove(j);
                }
            }
            list1.add(temp);
        }
        return list1;
    }

    public static void findNumsAppearOnce(int[] array, int num1[], int num2[]) {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer1 = new StringBuffer();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i : array) {
            stringBuffer.append(i);
            stringBuffer1.append(i);
        }
        for (int i = 0; i < array.length; i++) {
            int length1 = stringBuffer.toString().replace(String.valueOf(array[i]), "").length();
            int length = stringBuffer1.toString().length();
            int length2 = String.valueOf(array[i]).length();
            if (length == length1 + length2) {
                arrayList.add(array[i]);
            }
        }
        num1[0] = arrayList.get(0);
        num2[0] = arrayList.get(1);
        System.out.println(arrayList);
    }

    public static int strToInt(String str) {

        int result;
        try {
            result = Integer.parseInt(str);
        } catch (Exception e) {
            return 0;
        }
        return result;
    }

    public static boolean isContinuous(int[] numbers) {
        Arrays.sort(numbers);
        int result = Arrays.toString(numbers).length() - Arrays.toString(numbers).replace("0", "").length();
        if (result > 2) {
            return false;
        }
        if (numbers.length != 5) {
            return false;
        }
        if (result == 1) {
            for (int j = 2; j < numbers.length - 1; j++) {
                int i1 = numbers[j + 1] - numbers[j];
                if (i1 == 0) {
                    return false;
                }
            }
            if (numbers[4] - numbers[1] <= 4) {
                return true;
            }
        } else if (result == 2) {
            for (int j = 2; j < numbers.length - 1; j++) {
                int i1 = numbers[j + 1] - numbers[j];
                if (i1 == 0) {
                    return false;
                }
            }
            if (numbers[4] - numbers[2] <= 4) {
                return true;
            }
        } else {
            for (int j = 0; j < numbers.length - 1; j++) {

                int i1 = numbers[j + 1] - numbers[j];
                if (i1 == 0) {
                    return false;
                }
            }
            if (numbers[4] - numbers[0] == 4) {
                return true;
            }
        }
        return false;
    }
}