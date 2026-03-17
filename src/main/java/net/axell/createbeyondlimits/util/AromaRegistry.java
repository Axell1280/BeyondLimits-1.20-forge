package net.axell.createbeyondlimits.util;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AromaRegistry {

    // Map dimension key -> set of BlockPos
    private static final Map<ResourceKey<Level>, Set<BlockPos>> POSITIONS = new ConcurrentHashMap<>();

    public static void add(ResourceKey<Level> dim, BlockPos pos) {
        POSITIONS.computeIfAbsent(dim, k -> ConcurrentHashMap.newKeySet()).add(pos.immutable());
    }

    public static void remove(ResourceKey<Level> dim, BlockPos pos) {
        Set<BlockPos> set = POSITIONS.get(dim);
        if (set != null) {
            set.remove(pos);
            if (set.isEmpty()) POSITIONS.remove(dim);
        }
    }

    public static Set<BlockPos> getPositions(ResourceKey<Level> dim) {
        return POSITIONS.getOrDefault(dim, Collections.emptySet());
    }

    // Optional: clear all (useful for world reloads)
    public static void clear() {
        POSITIONS.clear();
    }
}
