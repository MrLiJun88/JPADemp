package test;

import cn.entity.Customer;
import cn.util.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JpaTest {
    /**保存一个客户信息到数据库中*/
    /**
     * JAP的操作步骤：
     *      1：加载配置文件，创建工厂对象
     *      2：通过工厂对象获取一个实体管理器
     *      3：获取事务对象，开启事务
     *      4：完成CRUD操作
     *      5：提交事务(回滚事务)
     *      6：释放资源
     */
    @Test
    public void testSave(){
        /**获取EntityManager对象*/
        EntityManager manager = JpaUtils.getEntityManager();
        /**获取事务对象*/
        EntityTransaction tx = manager.getTransaction();
        /**开启事务*/
        tx.begin();
        Customer customer = new Customer();
        customer.setName("lisi");
        customer.setIndustry("C++");
        customer.setPhone("11548962");
        /**保存*/
        manager.persist(customer);
        /**提交事务(回滚事务)*/
        tx.commit();
        /**释放资源*/
        manager.close();
        /**关闭工厂资源*/
        JpaUtils.closeFactory();
    }

    @Test
    public void testFind(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        /**
         * find : 根据id查询客户(立即加载，得到的是对象本身)
         *  1.查询的对象就是当前客户对象本身
         *  2.在调用find()方法的时候，就会发送sql语句查询数据库
         *
         *      class ：查询数据的结果需要包装的实体类类型的字节码
         *      id ：查询的主键的取值
         */
        Customer customer = manager.find(Customer.class,3);
        System.out.println(customer);
        transaction.commit();
        manager.close();
        JpaUtils.closeFactory();
    }
    @Test
    public void testFindByLazy(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        /**
         *  getReference : 根据id查询客户(延迟加载数据,得到的是一个动态代理对象)
         *    1.获取的对象是一个动态代理对象
         *    2.调用getReference()方法不会立即发送sql语句查询数据库
         *    3.当调用查询结果对象的时候，才会发送sql语句，什么时候用，就什么时候发送sql语句查询数据库
         *
         *      class ：查询数据的结果需要包装的实体类类型的字节码
         *      id ：查询的主键的取值
         */
        Customer customer = manager.getReference(Customer.class,2);
        System.out.println(customer);
        transaction.commit();
        manager.close();
        JpaUtils.closeFactory();
    }

    /**根据id删除用户*/
    @Test
    public void testRemove(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        /**
         * 1.先根据id查询出用户
         * 2.再删除
         */
        Customer customer = manager.getReference(Customer.class,3);
        /**删除用户*/
        manager.remove(customer);
        transaction.commit();
        manager.close();
        JpaUtils.closeFactory();
    }

    @Test
    public void testUpdate(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        /**
         * 1.先查询出用户
         * 2.再更新该用户信息
         */
        Customer customer = manager.getReference(Customer.class,4);
        customer.setName("wmy");
        manager.merge(customer);
        transaction.commit();
        manager.close();
        JpaUtils.closeFactory();
    }
}
