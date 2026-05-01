package net.axell.createbeyondlimits.event;

import net.axell.createbeyondlimits.BeyondLimits;
import net.axell.createbeyondlimits.block.ModBlocks;
import net.axell.createbeyondlimits.block.entity.AromaBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = BeyondLimits.MOD_ID)
public class AromaLightningHandler {

    @SubscribeEvent
    public static void onLightningStrike(EntityJoinLevelEvent event) {
        // We only care about Lightning Bolts on the Server
        if (event.getEntity() instanceof LightningBolt lightning && !event.getLevel().isClientSide()) {
            ServerLevel level = (ServerLevel) event.getLevel();
            BlockPos strikePos = lightning.blockPosition();

            // Check a small 3x3x3 area around the strike for any Aroma block
            for (BlockPos pos : BlockPos.betweenClosed(strikePos.offset(-1, -1, -1), strikePos.offset(1, 1, 1))) {
                BlockState state = level.getBlockState(pos);
                Block block = state.getBlock();

                if (isNormalAroma(block)) {
                    // 1. Get the owner before changing the block
                    BlockEntity oldBE = level.getBlockEntity(pos);
                    UUID ownerUUID = null;
                    if (oldBE instanceof AromaBlockEntity aromaBE) {
                        ownerUUID = aromaBE.getOwnerUUID();
                    }

                    // 2. Transform the block
                    level.setBlockAndUpdate(pos, ModBlocks.SUPERCHARGED_AROMA_SANCTUM.get().defaultBlockState());

                    // 3. Award Advancement to the owner (if they are online)
                    if (ownerUUID != null) {
                        Player player = level.getPlayerByUUID(ownerUUID);
                        if (player instanceof ServerPlayer serverPlayer) {
                            // Trigger the advancement
                            awardAdvancement(serverPlayer, "blessed_ascent");
                        }
                    }

                    level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 0.0f, false, Level.ExplosionInteraction.NONE);
                }

            }
        }
    }

    private static boolean isNormalAroma(Block block) {
        return block == ModBlocks.AROMA_SPEED_1.get() ||
                block == ModBlocks.AROMA_SPEED_2.get() ||
                block == ModBlocks.AROMA_REGEN_1.get() ||
                block == ModBlocks.AROMA_REGEN_2.get() ||
                block == ModBlocks.AROMA_STRENGTH_1.get() ||
                block == ModBlocks.AROMA_STRENGTH_2.get();
    }

    // Helper method to grant the advancement
    private static void awardAdvancement(ServerPlayer player, String id) {
        // The "id" passed here should be "blessed_ascent"
        // The path must match your folder: advancements/aroma_blocks/
        ResourceLocation advId = ResourceLocation.fromNamespaceAndPath("createbeyondlimits", "beyond_limits/aroma_blocks/" + id);
        var advancement = player.getServer().getAdvancements().getAdvancement(advId);

        if (advancement != null) {
            player.getAdvancements().award(advancement, "code_trigger");
        } else {
            // This will show you the exact path it's failing to find in the console
            System.out.println("Could not find advancement: " + advId);
        }
    }
}