package cn.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 解决实体管理类工厂浪费资源和耗时问题
 *    通过静态代码块的形式，当程序第一次访问此工具类时，创建一个公共的实体管理器工厂对象
 */
public class JpaUtils {
    private static EntityManagerFactory factory = null;
    static {
         factory = Persistence.createEntityManagerFactory("myJpa");
    }
    /**获取EntityManager对象*/
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
    /**关闭工厂资源*/
    public static void closeFactory(){
        factory.close();
    }
}
