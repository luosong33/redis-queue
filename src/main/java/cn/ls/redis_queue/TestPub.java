package cn.ls.redis_queue;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import redis.clients.jedis.Jedis;

/**
 * redis 队列
 * @author Administrator
 *
 */
public class TestPub {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1");
		try {
			Bean bean = new Bean();
			bean.setName("1012");
			// 使用ObjectOutputStream和ByteArrayOutputStream将对象转换成字节流
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(bean);
			String msg1 = baos.toString("ISO-8859-1");// 指定字符集将字节流解码成字符串，否则在订阅时，转换会有问题。
			// msg1 = URLEncoder.encode(msg1, "UTF-8");
			jedis.publish("foo", msg1);
		} catch (Exception e) {

		}
	}
}