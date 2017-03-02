package redis.lock;

import redis.clients.jedis.Jedis;
import redis.util.RedisUtil;

public class RedisLock {
	
	public static void lock(String key){
		lock(key,60);
	}
	
	public static void lock(String key,int time){
		Jedis jedis = RedisUtil.getClient();
		try {
			for (;;) {
				//返回OK则拿到锁
				String result = jedis.set(key, "1", "NX", "EX", time);
				System.out.println(result);
				if("OK".equals(result)){
					break;
				}
				Thread.sleep(300);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jedis.close();
		}
	}
	
	public static void unLock(String key){
		Jedis jedis = RedisUtil.getClient();
		try {
			jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jedis.close();
		}
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(RedisUtil.getClient().get("seckill"));
		System.out.println(RedisUtil.getClient().del("seckill"));
		//开启100条线程去抢购1000个商品
		for (int i = 0; i < 100; i++) {
			new SeckillThread(i).start();
		}
		
		while(Thread.activeCount()>1){
			Thread.sleep(1000);
		}
		System.out.println("还剩商品数量："+SeckillServiceImpl.goodNum);
	}
}

class SeckillServiceImpl{
	
	//商品总数
	public static int goodNum = 1000;
	
	public void kill(){
		if(goodNum>0){
			goodNum--;
		}
	}
	
	public int getGoodNum(){
		return goodNum;
	}
}

class SeckillThread extends Thread{

	private int i;
	
	public SeckillThread(int i) {
		this.i = i;
	}


	@Override
	public void run() {
		try {
			SeckillServiceImpl seckillService = new SeckillServiceImpl();
			System.out.println("线程["+i+"]加锁");
			RedisLock.lock("seckill");
			seckillService.kill();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("线程["+i+"]释放锁");
			RedisLock.unLock("seckill");
		}
	}
	
}