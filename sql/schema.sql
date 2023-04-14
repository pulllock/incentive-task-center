CREATE TABLE activity_event_group (
  id          bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  creator_id  bigint(20)                         NOT NULL COMMENT '创建人用户ID',
  modifier_id bigint(20)                         NOT NULL COMMENT '修改人用户ID',
  deleted     tinyint(1)                         NOT NULL DEFAULT 0 COMMENT '是否已删除：0-未删除 1-已删除',
  version     bigint(20)                         NOT NULL DEFAULT 1 COMMENT '版本号',
  code        varchar(255)                       NOT NULL UNIQUE COMMENT '唯一编码',
  name        varchar(255)                       NOT NULL COMMENT '名称',
  description varchar(1000)                               DEFAULT NULL COMMENT '描述',
  status      smallint(6)                        NOT NULL COMMENT '状态：1-启用 2-禁用',
  `order`     int(10)                            NOT NULL COMMENT '排序',
  PRIMARY KEY (id),
  KEY idx_creator_id (creator_id),
  KEY idx_name (name),
  KEY idx_order (`order`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '活动事件分组';

CREATE TABLE activity_event (
  id             bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time    datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time    datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  creator_id     bigint(20)                         NOT NULL COMMENT '创建人用户ID',
  modifier_id    bigint(20)                         NOT NULL COMMENT '修改人用户ID',
  deleted        tinyint(1)                         NOT NULL DEFAULT 0 COMMENT '是否已删除：0-未删除 1-已删除',
  version        bigint(20)                         NOT NULL DEFAULT 1 COMMENT '版本号',
  event_group_id bigint(20)                         NOT NULL COMMENT '活动事件组ID',
  code           varchar(255)                       NOT NULL COMMENT '编码',
  name           varchar(255)                       NOT NULL COMMENT '名称',
  description    varchar(1000)                               DEFAULT NULL COMMENT '描述',
  status         smallint(6)                        NOT NULL COMMENT '状态：1-启用 2-禁用',
  `order`        int(10)                            NOT NULL COMMENT '排序',
  PRIMARY KEY (id),
  UNIQUE KEY uniq_group_code (event_group_id, code),
  KEY idx_creator_id (creator_id),
  KEY idx_event_group_id (event_group_id),
  KEY idx_code (code),
  KEY idx_name (name),
  KEY idx_order (`order`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '活动事件';


CREATE TABLE activity_task (
  id                  bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time         datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time         datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  creator_id          bigint(20)                         NOT NULL COMMENT '创建人用户ID',
  modifier_id         bigint(20)                         NOT NULL COMMENT '修改人用户ID',
  deleted             tinyint(1)                         NOT NULL DEFAULT 0 COMMENT '是否已删除：0-未删除 1-已删除',
  version             bigint(20)                         NOT NULL DEFAULT 1 COMMENT '版本号',
  code                varchar(255)                       NOT NULL COMMENT '编码',
  event_id            bigint(20)                         NOT NULL COMMENT '任务事件ID',
  name                varchar(255)                       NOT NULL COMMENT '名称',
  description         varchar(1000)                               DEFAULT NULL COMMENT '描述',
  simple_type         tinyint(1)                         NOT NULL COMMENT '是否是简单类型任务：0-否 1-是',
  type                smallint(6)                        NOT NULL COMMENT '类型：10-简单类型 11-可设置目标的简单类型 12-设置目标的简单类型，带额外奖励 20-连续累计型 21-连续累计型带额外奖励',
  complete_type       smallint(6)                        NOT NULL COMMENT '完成类型：1-系统自动完成 2-前端手动完成',
  parent_id           bigint(20)                         NOT NULL COMMENT '父任务ID',
  status              smallint(6)                        NOT NULL COMMENT '状态：1-启用 2-禁用',
  category            smallint(6)                        NOT NULL COMMENT '分类：1-全局任务 2-可下发任务',
  valid_period_type   smallint(6)                        NOT NULL COMMENT '有效期类型：1-长期有效 2-有效期范围 3-发放后N天内有效',
  valid_time_start    datetime                                    DEFAULT NULL COMMENT '有效开始时间',
  valid_time_end      datetime                                    DEFAULT NULL COMMENT '有效结束时间',
  valid_days          bigint(20)                                  DEFAULT NULL COMMENT '有效期天数',
  rule_type           smallint(6)                        NOT NULL COMMENT '类型：10-一次性 20-每一次 30-周期性（M天N次，每次都算完成） 31-周期性（M天N次，完成最后一次才算完成）',
  period_type         smallint(6)                                 DEFAULT NULL COMMENT '周期类型：1-相对周期 2-自然周期',
  period_unit         smallint(6)                                 DEFAULT NULL COMMENT '周期单位：1-秒 2-分 3-小时 4-天 5-周 6-月 7-年',
  period_interval     bigint(20)                                  DEFAULT NULL COMMENT '周期的间隔',
  period_times        bigint(20)                                  DEFAULT NULL COMMENT '周期的次数',
  url                 varchar(255)                                DEFAULT NULL COMMENT '跳转链接',
  url_target_type     smallint(6)                                 DEFAULT NULL COMMENT '跳转链接目标类型：1-APP 2-H5',
  reward_type         int(10)                            NOT NULL COMMENT '类型：10-积分 20-金币 30-券 40-返现 50-话费 60-红包',
  reward_number       bigint(20)                         NOT NULL COMMENT '数量',
  reward_provide_mode smallint(6)                        NOT NULL COMMENT '奖励发放方式：1-自动发放 2-用户领取',
  PRIMARY KEY (id),
  UNIQUE KEY uniq_code_deleted (code, deleted),
  KEY idx_creator_id (creator_id),
  KEY idx_code (code),
  KEY idx_event_id (event_id),
  KEY idx_name (name),
  KEY idx_parent_id (parent_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '活动任务';

CREATE TABLE activity_user_task (
  id               bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time      datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time      datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  creator_id       bigint(20)                         NOT NULL COMMENT '创建人用户ID',
  modifier_id      bigint(20)                         NOT NULL COMMENT '修改人用户ID',
  deleted          tinyint(1)                         NOT NULL DEFAULT 0 COMMENT '是否已删除：0-未删除 1-已删除',
  version          bigint(20)                         NOT NULL DEFAULT 1 COMMENT '版本号',
  user_id          bigint(20)                         NOT NULL COMMENT '用户ID',
  task_id          bigint(20)                         NOT NULL COMMENT '任务ID',
  status           smallint(6)                        NOT NULL COMMENT '状态：1-待领取 2-已完成 3-已过期',
  valid_time_start datetime                           NOT NULL COMMENT '有效期开始时间',
  valid_time_end   datetime                           NOT NULL COMMENT '有效期结束时间',
  PRIMARY KEY (id),
  KEY idx_creator_id (creator_id),
  KEY idx_user_id (user_id),
  KEY idx_task_id (task_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '用户任务';

CREATE TABLE activity_task_execution_log (
  id          bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  creator_id  bigint(20)                         NOT NULL COMMENT '创建人用户ID',
  modifier_id bigint(20)                         NOT NULL COMMENT '修改人用户ID',
  deleted     tinyint(1)                         NOT NULL DEFAULT 0 COMMENT '是否已删除：0-未删除 1-已删除',
  version     bigint(20)                         NOT NULL DEFAULT 1 COMMENT '版本号',
  user_id     bigint(20)                         NOT NULL COMMENT '用户ID',
  task_id     bigint(20)                         NOT NULL COMMENT '活动任务ID',
  need_reward tinyint(1)                         NOT NULL COMMENT '是否需要发放奖励：0-否 1-是',
  source      varchar(255)                       NOT NULL COMMENT '来源',
  source_id   varchar(255)                       NOT NULL UNIQUE COMMENT '来源ID，唯一',
  PRIMARY KEY (id),
  KEY idx_creator_id (creator_id),
  KEY idx_user_id (user_id),
  KEY idx_task_id (task_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '活动任务执行日志';

CREATE TABLE activity_task_reward_log (
  id          bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  creator_id  bigint(20)                         NOT NULL COMMENT '创建人用户ID',
  modifier_id bigint(20)                         NOT NULL COMMENT '修改人用户ID',
  deleted     tinyint(1)                         NOT NULL DEFAULT 0 COMMENT '是否已删除：0-未删除 1-已删除',
  version     bigint(20)                         NOT NULL DEFAULT 1 COMMENT '版本号',
  user_id     bigint(20)                         NOT NULL COMMENT '用户ID',
  task_id     bigint(20)                         NOT NULL COMMENT '活动任务ID',
  status      smallint(6)                        NOT NULL COMMENT '状态：0-等待后续更多记录 1-已发放未成功 2-已发放成功 3-待领取 4-领取未成功 5-领取成功',
  source      varchar(255)                       NOT NULL COMMENT '来源',
  source_id   varchar(255)                       NOT NULL UNIQUE COMMENT '来源ID，唯一',
  PRIMARY KEY (id),
  KEY idx_creator_id (creator_id),
  KEY idx_user_id (user_id),
  KEY idx_task_id (task_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '活动任务奖励日志';