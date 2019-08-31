package test;

import cn.util.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JpqlTest {
    /**
     * 使用 jpql 查询全部用户信息
     *  jpql: from cn.entity.Customer
     *  sql: select * from 表名
     */
    @Test
    public void testFindAll(){
        /**获取EntityManager对象*/
        EntityManager manager = JpaUtils.getEntityManager();
        /**开启事务*/
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        /**查询全部*/
        String jpql = "from cn.entity.Customer";
        /**创建Query对象，query才是执行jpql的实际对象*/
        Query query = manager.createQuery(jpql);
        /**发送查询，并封装成结果集*/
        List list = query.getResultList();
        list.forEach(i -> System.out.println(i));
        transaction.commit();
        manager.close();
        JpaUtils.closeFactory();
    }

    /**倒序查询所有用户*/
    @Test
    public void testOrdeByDesc(){
        /**获取EntityManager对象*/
        EntityManager manager = JpaUtils.getEntityManager();
        /**开启事务*/
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        /**查询全部*/
        String jpql = "from cn.entity.Customer order by id desc ";
        /**创建Query对象，query才是执行jpql的实际对象*/
        Query query = manager.createQuery(jpql);
        /**发送查询，并封装成结果集*/
        List list = query.getResultList();
        list.forEach(i -> System.out.println(i));
        transaction.commit();
        manager.close();
        JpaUtils.closeFactory();
    }

    /**查询数据库表中用户数量*/
    @Test
    public void testCount(){
        /**获取EntityManager对象*/
        EntityManager manager = JpaUtils.getEntityManager();
        /**开启事务*/
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        /**查询全部*/
        String jpql = "select count(id) from cn.entity.Customer";
        /**创建Query对象，query才是执行jpql的实际对象*/
        Query query = manager.createQuery(jpql);
        /**发送查询，并获取到唯一的结果集*/
        Long result = (Long)query.getSingleResult();
        System.out.println(result);
        transaction.commit();
        manager.close();
        JpaUtils.closeFactory();
    }
    /**实现分页查询*/
    @Test
    public void testPaging(){
        /**获取EntityManager对象*/
        EntityManager manager = JpaUtils.getEntityManager();
        /**开启事务*/
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        /**查询全部*/
        String jpql = "from cn.entity.Customer";
        /**创建Query对象，query才是执行jpql的实际对象*/
        Query query = manager.createQuery(jpql);
        /**
         * 分页查询对参数赋值
         *     1.启始索引
         *     2.每页查询的条数
         */
        /**1.启始索引 从0 开始，不包含 0*/
        query.setFirstResult(0);
        /**每页查询的条数，每次查询两条数据*/
        query.setMaxResults(2);
        /**发送查询，并获取到结果集*/
        List list = query.getResultList();
        list.forEach(i -> System.out.println(i));
        transaction.commit();
        manager.close();
        JpaUtils.closeFactory();
    }

    /**条件查询 如：查询用户名称以 li 开头的用户*/
    @Test
    public void testCondition(){
        /**获取EntityManager对象*/
        EntityManager manager = JpaUtils.getEntityManager();
        /**开启事务*/
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        /**查询全部*/
        String jpql = "from cn.entity.Customer where name like ?";
        /**创建Query对象，query才是执行jpql的实际对象*/
        Query query = manager.createQuery(jpql);
        /**
         * 对参数赋值 --- 占位符参数
         * 第一个参数，占位符的索引位置，从1开始
         * 第二个参数，取值
         */
        query.setParameter(1,"li%");
        List list = query.getResultList();
        list.forEach(i -> System.out.println(i));
        transaction.commit();
        manager.close();
        JpaUtils.closeFactory();
    }
}
