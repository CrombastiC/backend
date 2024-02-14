package com.dletc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // 添加了全参构造
@NoArgsConstructor  // 添加了无参构造
@Data
public class User {
    private Integer uid;
    private String ukey;
    private String pwd;
    private String realname;
    private Integer type;


}
