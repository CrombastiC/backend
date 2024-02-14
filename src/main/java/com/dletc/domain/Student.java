package com.dletc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor // 添加了全参构造
@NoArgsConstructor  // 添加了无参构造
@Data
public class Student {
    private Integer sid;
    private String sname;
    private String sex;
    private String hobby;
    private Date birthdate;
    private String phone;
    private String reamrk;
    private Integer cid; //班级编号
    private Clazz clazz;


}
