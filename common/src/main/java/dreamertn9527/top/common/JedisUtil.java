package dreamertn9527.top.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 类描述:
 *
 * @author:tangniannian
 * @date:2018/6/5
 * @修改描述：
 * @modifier ${tags}
 */
public class JedisUtil {

    private static JedisPool pool;

    private final static String REDIS_CONFIG = "properties/redis-config.properties";

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(PropertiesUtil.getProperties(REDIS_CONFIG, "jedis.pool.maxActive")));
        config.setMaxWaitMillis(Integer.parseInt(PropertiesUtil.getProperties(REDIS_CONFIG, "jedis.pool.maxWait")));
        config.setMaxIdle(Integer.parseInt(PropertiesUtil.getProperties(REDIS_CONFIG, "jedis.pool.maxIdle")));
        config.setTestOnBorrow(Boolean.valueOf(PropertiesUtil.getProperties(REDIS_CONFIG, "jedis.pool.testOnBorrow")));
        config.setTestOnReturn(Boolean.valueOf(PropertiesUtil.getProperties(REDIS_CONFIG, "jedis.pool.testOnReturn")));

        pool = new JedisPool(config, PropertiesUtil.getProperties(REDIS_CONFIG, "redis.ip"), Integer.parseInt(PropertiesUtil.getProperties(REDIS_CONFIG, "redis.port")));
    }

    /**
     * 获得jedis对象
     */

    public static Jedis getJedisObject() {
        return pool.getResource();
    }

    public static void main(String[] args) {
        Jedis jedis = getJedisObject();//获得jedis实例

        //获取jedis实例后可以对redis服务进行一系列的操作

        jedis.set("name", "zhuxun");

        System.out.println(jedis.get("name"));

        jedis.del("name");

        System.out.println(jedis.exists("name"));
    }


}