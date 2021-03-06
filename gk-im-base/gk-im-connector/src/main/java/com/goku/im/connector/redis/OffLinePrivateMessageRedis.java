package com.goku.im.connector.redis;

import com.goku.im.connector.global.RedisKeyConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

/**
 * Created by moueimei on 15/11/27.
 * 离线消息,负责存储掉线用户的未读消息
 */
@Repository
public class OffLinePrivateMessageRedis {
    @Autowired
    Jedis jedis;

    public void add(int userId, long createTime, String messageId) throws Exception {
        String key = RedisKeyConst.makeKey(RedisKeyConst.OFFLINE_PRIVATE_MESSAGE_LIST_KEY_PREFIX, userId);
        jedis.zadd(key, createTime, messageId);
    }

    public void delete(int userId, String messageId) throws Exception {
        String key = RedisKeyConst.makeKey(RedisKeyConst.OFFLINE_PRIVATE_MESSAGE_LIST_KEY_PREFIX, userId);
        jedis.zrem(key, messageId);
    }
}