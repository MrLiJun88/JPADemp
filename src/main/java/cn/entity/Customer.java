package cn.entity;

import javax.persistence.*;

/**
 * 客户实体类
 *  配置映射关系：
 *   1.实体类与数据库表的关系
 *    @Entity : 声明该类是一个实体类
 *    @Table : 配置实体类与表的映射关系
 *       name: 数据库表的名称
 *
 *   2.实体类中属性与表字段的映射关系
 *      @Id : 声明主键的配置
 *
 *      @GeneratedValue(strategy = GenerationType.IDENTITY) 配置主键的生成策略
 *          IDENTITY: 代表自增，前提是底层数据库必须支持自支增长(对id自增,针对 MySQL 数据库)
 *          SEQUENCE: 代表序列，前提是底层数据库必须支持序列(针对的是 ORACLE 数据库)
 *          TABLE : JAP提供的一种机制，通过一张数据库表的形式帮助我们完成自增
 *          AUTO : 代表由程序自动的帮助我们选择主机生成策略
 *
 *      @Column(name = "cust_id") 配置属性与字段的映射，name 是表中字段的名称
 *
 */
@Entity
@Table(name = "cst_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Integer id;
    @Column(name = "cust_name")
    private String name;
    /**客户来源*/
    @Column(name = "cust_source")
    private String source;
    /**客户所属行业*/
    @Column(name = "cust_industry")
    private String industry;
    /**客户级别*/
    @Column(name = "cust_level")
    private String level;
    @Column(name = "cust_address")
    private String address;
    @Column(name = "cust_phone")
    private String phone;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", source='" + source + '\'' +
                ", industry='" + industry + '\'' +
                ", level='" + level + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
