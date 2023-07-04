

package com.zhiyu.common.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("active_web_socket_user")
public class ActiveWebSocketUser extends BaseEntity{
	@TableId(type = IdType.UUID)
	private String id;
	private String username;
	private Date connectionTime;

}
