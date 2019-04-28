package com.qfedu.entity.user;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("user")
@Data
public class User implements Serializable {

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String phone;
	private String password;
	private Integer flag;

}
