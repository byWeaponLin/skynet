package com.weaponlin.skynet.repository.entity;

import lombok.Data;
import lombok.experimental.Accessors;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;

@Data
//@Entity
//@Table(name = "user")
@Accessors(chain = true)
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(columnDefinition = "bigint(11)  COMMENT '自增主键'")
    private Long id;

//    @Column(name = "name", columnDefinition = "varchar(255) NOT NULL COMMENT '名字'")
    private String name;

//    @Column(name = "gender", columnDefinition = "varchar(255) NOT NULL COMMENT '性别'")
    private String gender;

//    @Column(name = "age", columnDefinition = "tinyint(4) DEFAULT NULL  COMMENT '年龄'")
    private Integer age;

//    @Column(name = "del", columnDefinition = "tinyint(4) DEFAULT NULL  COMMENT '删除状态'")
    private Integer del;

}
