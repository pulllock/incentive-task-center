-- expression_condition
INSERT INTO expression_condition (id, create_time, update_time, code, name, description) VALUES (1, now(), now(), 'value', '值', null);
INSERT INTO expression_condition (id, create_time, update_time, code, name, description) VALUES (2, now(), now(), 'totalTimes', '总次数', null);
INSERT INTO expression_condition (id, create_time, update_time, code, name, description) VALUES (3, now(), now(), 'totalNumber', '总数', null);

-- expression_operator
INSERT INTO expression_operator (id, create_time, update_time, code, name, description) VALUES (1, now(), now(), '==', '等于', null);
INSERT INTO expression_operator (id, create_time, update_time, code, name, description) VALUES (2, now(), now(), '>', '大于', null);
INSERT INTO expression_operator (id, create_time, update_time, code, name, description) VALUES (3, now(), now(), '>=', '大于等于', null);
INSERT INTO expression_operator (id, create_time, update_time, code, name, description) VALUES (4, now(), now(), '<', '小于', null);
INSERT INTO expression_operator (id, create_time, update_time, code, name, description) VALUES (5, now(), now(), '<=', '小于等于', null);
INSERT INTO expression_operator (id, create_time, update_time, code, name, description) VALUES (6, now(), now(), '!=', '不等于', null);

-- event
INSERT INTO event (id, create_time, update_time, code, status, name, description) VALUES (1, now(), now(), 'daily_login', 1, '每日登陆', null);
INSERT INTO event (id, create_time, update_time, code, status, name, description) VALUES (2, now(), now(), 'invite_friend', 1, '邀请用户', null);
INSERT INTO event (id, create_time, update_time, code, status, name, description) VALUES (3, now(), now(), 'daily_sign_in', 1, '每日签到', null);
INSERT INTO event (id, create_time, update_time, code, status, name, description) VALUES (4, now(), now(), 'share', 1, '分享', null);
INSERT INTO event (id, create_time, update_time, code, status, name, description) VALUES (5, now(), now(), 'buy_product', 1, '购买产品', null);
INSERT INTO event (id, create_time, update_time, code, status, name, description) VALUES (6, now(), now(), 'buy_vip', 1, '成为VIP', null);
INSERT INTO event (id, create_time, update_time, code, status, name, description) VALUES (7, now(), now(), 'first_time_pay', 1, '首次完成付费', null);
INSERT INTO event (id, create_time, update_time, code, status, name, description) VALUES (8, now(), now(), 'user_register', 1, '用户注册', null);
