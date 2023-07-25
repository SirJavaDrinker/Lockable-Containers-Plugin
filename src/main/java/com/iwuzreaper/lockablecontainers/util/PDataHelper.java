package com.iwuzreaper.lockablecontainers.util;

import com.iwuzreaper.lockablecontainers.LockableContainers;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.block.TileState;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import sun.jvm.hotspot.oops.ObjArrayKlass;

public class PDataHelper {
    private static LockableContainers instance = LockableContainers.getInstance();
    
    public static void Write(Entity entity, String key, Object value, PersistentDataType type) {
        PersistentDataContainer data = entity.getPersistentDataContainer();
        data.set(new NamespacedKey(instance, key), type, value);
    }

    public static void Write(TileState tileState, String key, Object value, PersistentDataType type) {
        PersistentDataContainer data = tileState.getPersistentDataContainer();
        data.set(new NamespacedKey(instance, key), type, value);
        tileState.update();
    }

    public static void Write(Container container, String key, Object value, PersistentDataType type) {
        PersistentDataContainer data = container.getPersistentDataContainer();
        data.set(new NamespacedKey(instance, key), type, value);
        container.update();
    }

    public static Object Read(Entity entity, String key, Object fallback, PersistentDataType type) {
        PersistentDataContainer data = entity.getPersistentDataContainer();
        if (!data.has(new NamespacedKey(instance, key))) {
            Write(entity, key, fallback, type);
            return fallback;
        }
        return data.get(new NamespacedKey(instance, key), type);
    }

    public static Object Read(Container container, String key, Object fallback, PersistentDataType type) {
        PersistentDataContainer data = container.getPersistentDataContainer();
        if (!data.has(new NamespacedKey(instance, key))) {
            Write(container, key, fallback, type);
            return fallback;
        }
        return data.get(new NamespacedKey(instance, key), type);
    }

    public static Object Read(TileState tileState, String key, Object fallback, PersistentDataType type) {
        PersistentDataContainer data = tileState.getPersistentDataContainer();
        if (!data.has(new NamespacedKey(instance, key))) {
            Write(tileState, key, fallback, type);
            return fallback;
        }
        return data.get(new NamespacedKey(instance, key), type);
    }



}
