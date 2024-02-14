package com.dletc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // 添加了全参构造
@NoArgsConstructor  // 添加了无参构造
@Data
public class Clazz {
    private Integer cid;
    private String cname;
    private String cteacher;
    private String remark;


}
