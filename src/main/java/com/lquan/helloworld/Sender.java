package com.lquan.helloworld;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

	public static void main(String[] args) throws Exception {
		 
//		 1，需要填写用户名，密码，以及要连接的地址，均使用默认即可，默认端口为：tcp：//localhost：61616
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					ActiveMQConnectionFactory.DEFAULT_USER,
					ActiveMQConnectionFactory.DEFAULT_PASSWORD, 
					"tcp://192.168.1.112:61616");
			// 第二步：connfaction 工厂对象创建一个connection连接，并且调用Connection的Start方法开启连接，conection默认是关闭的
			 Connection connection =connectionFactory.createConnection();
			 connection.start();
			 // 第三步
			 // 通过connection对象创建session回话（上线文环境对象），用于接受消息，参数配置1为是否使用饿是事务，参数诶值2为简约配置希望小朋
			 Session session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			 
			 //第四部：通过Session创建Destination对象，指的是一个客户端用来制定生产消费目标和消费来源的对象，PTP模式Destion被称作为、
			 Destination destination = session.createQueue("queueLQuan");
			// 第五部：我们需要通过session创建
			 MessageProducer messageProducer = session.createProducer(destination);
			 // 第6个
			 messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			// ..第7步
			 for(int i=0;i<=5;i++){
				 TextMessage texMassage = session.createTextMessage();
				 texMassage.setText("我的消息内容，id为"+(i+100));
				 messageProducer.send(texMassage);
			 }
			 connection.close();
		} 


}
