package com.example.money_management_be.exception.exceptionClasses.base;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final String messageId;
    private final Object[] args;

    public BaseException(String messageId, Object[] args) {
        super(messageId);
        this.args = args;
        this.messageId = messageId;
    }
    
    protected static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1) {
            throw new IllegalArgumentException("exception.illegal.argument.entries");
        }
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
            .collect(HashMap::new,
                (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                Map::putAll);
    }
}
