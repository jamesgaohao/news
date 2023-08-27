CREATE TABLE `user` (
   			`id` varchar(100) NOT NULL DEFAULT '' COMMENT 'id',
 			`userName` varchar(100) NOT NULL DEFAULT '' COMMENT '用户姓名',
 			`passWord` varchar(100) NOT NULL DEFAULT '' COMMENT '密码',			`phone` varchar(100) NOT NULL DEFAULT '' COMMENT '手机号码',
			`age` varchar(100) NOT NULL DEFAULT '' COMMENT '年龄',
			`address` varchar(100) NOT NULL DEFAULT '' COMMENT '地址',
			`subscribe` varchar(300) NOT NULL DEFAULT '' COMMENT '订阅',
			`create_time` datetime NOT NULL COMMENT '创建时间',
			`create_by` varchar(100) NOT NULL DEFAULT '' COMMENT '创建人',
			`update_time` datetime NOT NULL COMMENT '更新时间',
			`update_by` varchar(100) NOT NULL DEFAULT '' COMMENT '更新人',
			`delete_flg` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识', 			PRIMARY KEY (`id`),
  			UNIQUE KEY `user_userName_IDX` (`userName`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4


            CREATE TABLE `news` (
  			`id` varchar(100) NOT NULL DEFAULT '' COMMENT 'id',
  			`title1` varchar(300) NOT NULL DEFAULT '' COMMENT '标题1',
  			`title2` varchar(300) NOT NULL DEFAULT '' COMMENT '标题1',
  			`title3` varchar(300) NOT NULL DEFAULT '' COMMENT '标题1',
  			`publisher` varchar(100) NOT NULL DEFAULT '' COMMENT '发布人',
  			`content` text NOT NULL COMMENT '内容',
  			`subscribe_id` varchar(100) NOT NULL DEFAULT '' COMMENT '新闻类型',
  			`create_time` datetime NOT NULL COMMENT '创建时间',
 			`create_by` varchar(100) NOT NULL DEFAULT '' COMMENT '创建人',
  			`update_time` datetime NOT NULL COMMENT '更新时间',
  			`update_by` varchar(100) NOT NULL DEFAULT '' COMMENT '更新人',
  			`delete_flg` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='新闻'



CREATE TABLE `user_read_news_record` (
  			 `id` varchar(100) NOT NULL DEFAULT '' COMMENT 'id',
 			 `subscribe` varchar(32) NOT NULL DEFAULT '' COMMENT '新闻类型',
 			 `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户id',
  			 `create_time` datetime NOT NULL COMMENT '创建时间',
  			PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户阅读记录表'



 CREATE TABLE `subscribe_type` (
  			`id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键id',
  			`subscribe_name` varchar(100) NOT NULL DEFAULT '' COMMENT '订阅名称',
  			`create_time` datetime NOT NULL COMMENT '创建时间',
  			`create_by` varchar(100) NOT NULL DEFAULT '' COMMENT '创建人',
 		    `update_time` datetime NOT NULL COMMENT '更新时间',
  			`update_by` varchar(100) NOT NULL DEFAULT '' COMMENT '更新人',
  			`delete_flg` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订阅类型表'










