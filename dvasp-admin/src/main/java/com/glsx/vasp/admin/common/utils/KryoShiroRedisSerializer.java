package com.glsx.vasp.admin.common.utils;

import com.glsx.vasp.framework.serializer.KryoRedisSerializer;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.crazycake.shiro.exception.SerializationException;
import org.crazycake.shiro.serializer.RedisSerializer;

public class KryoShiroRedisSerializer implements RedisSerializer<SimpleAuthorizationInfo> {

    KryoRedisSerializer kryoRedisSerializer = new KryoRedisSerializer(Object.class);

    @Override
    public byte[] serialize(SimpleAuthorizationInfo o) throws SerializationException {
        return kryoRedisSerializer.serialize(o);
    }

    @Override
    public SimpleAuthorizationInfo deserialize(byte[] bytes) throws SerializationException {
        return (SimpleAuthorizationInfo) kryoRedisSerializer.deserialize(bytes);
    }
}
