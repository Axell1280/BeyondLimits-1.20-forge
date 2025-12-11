package net.axell.createbeyondlimits.event;

import net.axell.createbeyondlimits.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "createbeyondlimits")
public class AromaEffectHandler {

    private static final int RADIUS = 30;  // range of the aroma

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = player.level();

        if (level.isClientSide()) return;  // run only server side
        if (event.phase != TickEvent.Phase.START) return;

        BlockPos playerPos = player.blockPosition();

        // scan all blocks in a cube around the player
        for (BlockPos pos : BlockPos.betweenClosed(
                playerPos.offset(-RADIUS, -RADIUS, -RADIUS),
                playerPos.offset(RADIUS, RADIUS, RADIUS))) {

            var state = level.getBlockState(pos);
            var block = state.getBlock();

            MobEffectInstance effect = null;

            // SPEED
            if (block == ModBlocks.AROMA_SPEED_1.get())
                effect = new MobEffectInstance(net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED, 40, 0);
            if (block == ModBlocks.AROMA_SPEED_2.get())
                effect = new MobEffectInstance(net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED, 40, 1);

            // REGEN
            if (block == ModBlocks.AROMA_REGEN_1.get())
                effect = new MobEffectInstance(net.minecraft.world.effect.MobEffects.REGENERATION, 40, 0);
            if (block == ModBlocks.AROMA_REGEN_2.get())
                effect = new MobEffectInstance(net.minecraft.world.effect.MobEffects.REGENERATION, 40, 1);

            // STRENGTH
            if (block == ModBlocks.AROMA_STRENGTH_1.get())
                effect = new MobEffectInstance(net.minecraft.world.effect.MobEffects.DAMAGE_BOOST, 40, 0);
            if (block == ModBlocks.AROMA_STRENGTH_2.get())
                effect = new MobEffectInstance(net.minecraft.world.effect.MobEffects.DAMAGE_BOOST, 40, 1);

            if (effect != null) {
                player.addEffect(effect);

                return; // stop scanning once one aroma block is found
            }
        }
    }
}
