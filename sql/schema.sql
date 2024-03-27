CREATE TABLE expression_condition (
  id                   bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time          datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time          datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  code                 varchar(32)                        NOT NULL COMMENT '唯一编号',
  name                 varchar(100)                       NOT NULL COMMENT '名称',
  description          varchar(255)                                DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (id),
  UNIQUE KEY uniq_code (code)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '表达式条件';

CREATE TABLE expression_operator (
  id                   bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time          datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time          datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  code                 varchar(32)                        NOT NULL COMMENT '唯一编号',
  name                 varchar(100)                       NOT NULL COMMENT '名称',
  description          varchar(255)                                DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (id),
  UNIQUE KEY uniq_code (code)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '表达式运算符';

CREATE TABLE event (
  id                   bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time          datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time          datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  code                 varchar(32)                        NOT NULL COMMENT '唯一编号',
  status               smallint(6)                        NOT NULL DEFAULT 0 COMMENT '状态，取值：0-无效 1-有效',
  name                 varchar(100)                       NOT NULL COMMENT '名称',
  description          varchar(255)                                DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (id),
  UNIQUE KEY uniq_code (code)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '事件';

CREATE TABLE task (
  id                   bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time          datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time          datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  status               smallint(6)                        NOT NULL DEFAULT 0 COMMENT '状态，取值：0-无效 1-有效',
  name                 varchar(100)                       NOT NULL COMMENT '名称',
  description          varchar(255)                                DEFAULT NULL COMMENT '描述',
  type                 smallint(6)                        NOT NULL DEFAULT 1 COMMENT '类型，取值：1-普通任务 2-可下发任务',
  sequence_weight      int(10)                            NOT NULL DEFAULT 0 COMMENT '顺序权重，权重越小排序越靠前',
  events               text                               NOT NULL COMMENT '关联的事件（JSON数组）',
  complete_rule        text                               NOT NULL COMMENT '完成规则（JSON格式）',
  complete_limit_rule  text                               NOT NULL COMMENT '完成次数限制规则（JSON格式）',
  complete_type        smallint(6)                        NOT NULL DEFAULT 1 COMMENT '完成类型，取值：1-手动领取 2-自动完成',
  after_complete_type  smallint(6)                        NOT NULL DEFAULT 0 COMMENT '完成后的后续操作类型，取值：0-无操作 1-积分 2-勋章 3-发送MQ消息',
  after_complete_rule  text                                        DEFAULT NULL COMMENT '完成后的后续规则（JSON格式）',
  complete_engage_way  smallint(6)                                 DEFAULT NULL COMMENT '完成后的触达方式，取值：1-PUSH 2-弹窗',
  complete_engage_rule text                                        DEFAULT NULL COMMENT '完成后的触达规则（JSON格式）',
  button_config        text                               NOT NULL COMMENT '按钮配置（JSON格式）',
  redirect_link_config text                               NOT NULL COMMENT '跳转链接配置（JSON格式）',
  PRIMARY KEY (id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '任务';

CREATE TABLE user_task (
  id                   bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time          datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time          datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  task_id              bigint(20)                         NOT NULL COMMENT '任务ID',
  status               smallint(6)                        NOT NULL DEFAULT 1 COMMENT '状态，取值：1-未完成 2-待领取 3-已完成',
  PRIMARY KEY (id),
  KEY idx_task_id (task_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '用户任务';

CREATE TABLE trigger_log (
  id                   bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time          datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time          datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  user_id              bigint(20)                         NOT NULL COMMENT '用户ID',
  event_code           varchar(32)                        NOT NULL COMMENT '事件编码',
  status               smallint(6)                        NOT NULL DEFAULT 1 COMMENT '状态，取值：1-处理中 2-失败 3-成功',
  process_result       text                                        DEFAULT NULL COMMENT '处理结果（JSON格式）',
  source               varchar(20)                        NOT NULL COMMENT '业务来源',
  unique_source_id     varchar(64)                        NOT NULL UNIQUE COMMENT '来源的唯一ID，实现幂等',
  PRIMARY KEY (id),
  KEY idx_user_id (user_id),
  KEY idx_event_code (event_code),
  UNIQUE KEY uniq_user_source_id (user_id, source, unique_source_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '任务触发日志';

CREATE TABLE complete_record (
  id                   bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time          datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time          datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  user_id              bigint(20)                         NOT NULL COMMENT '用户ID',
  task_id              bigint(20)                         NOT NULL COMMENT '任务ID',
  status               smallint(6)                        NOT NULL DEFAULT 1 COMMENT '状态，取值：1-待领取 2-已完成',
  complete_date        date                                        DEFAULT NULL COMMENT '完成日期',
  complete_time        time                                        DEFAULT NULL COMMENT '完成时间',
  source               varchar(20)                        NOT NULL COMMENT '业务来源',
  unique_source_id     varchar(64)                        NOT NULL COMMENT '来源的唯一ID，实现幂等',
  after_complete_type  smallint(6)                        NOT NULL COMMENT '完成后的后续操作类型，取值：0-无操作 1-积分 2-勋章 3-发送MQ消息',
  after_complete_rule  text COMMENT '完成后的后续规则（JSON格式）',
  PRIMARY KEY (id),
  KEY idx_user_id (user_id),
  KEY idx_task_id (task_id),
  UNIQUE KEY uniq_user_task_source_id (user_id, task_id, source, unique_source_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '任务完成记录';