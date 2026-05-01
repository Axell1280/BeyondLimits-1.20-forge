package net.axell.createbeyondlimits.event;

import net.axell.createbeyondlimits.BeyondLimits;
import net.axell.createbeyondlimits.block.ModBlocks;
import net.axell.createbeyondlimits.effect.ModEffects;
import net.axell.createbeyondlimits.world.SoulLinkData;
import net.axell.createbeyondlimits.config.BeyondLimitsConfig; // Added Config Import
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = BeyondLimits.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void onPiglinTarget(LivingChangeTargetEvent event) {
        if (event.getNewTarget() instanceof Player player && event.getEntity() instanceof Piglin) {
            if (player.level().dimension() == Level.NETHER && !player.level().isClientSide) {
                SoulLinkData data = SoulLinkData.get((ServerLevel) player.level());
                Map<String, BlockPos> links = data.getAllLinks(player.getUUID());

                if (links.containsKey("bastion")) {
                    BlockPos pos = links.get("bastion");
                    if (player.level().getBlockState(pos).is(ModBlocks.FRAGRANCE_BASTION.get())) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBruteAttack(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player && event.getSource().getEntity() instanceof PiglinBrute) {
            if (player.level().dimension() == Level.NETHER && !player.level().isClientSide) {
                SoulLinkData data = SoulLinkData.get((ServerLevel) player.level());
                Map<String, BlockPos> links = data.getAllLinks(player.getUUID());

                if (links.containsKey("bastion")) {
                    BlockPos pos = links.get("bastion");
                    if (player.level().getBlockState(pos).is(ModBlocks.FRAGRANCE_BASTION.get())) {
                        event.setAmount(event.getAmount() * 0.5f);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        // Fire cancel for Endued effect
        if (event.getEntity().hasEffect(ModEffects.ENDUED.get())) {
            if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
                event.setCanceled(true);
            }
        }
    }

    // --- REFACTORED ANIMAL TICK ---
    @SubscribeEvent
    public static void onAnimalTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        if (!entity.level().isClientSide && entity instanceof Animal animal) {

            // Check Config instead of GameRule
            if (!BeyondLimitsConfig.ENABLE_NATURAL_BREEDING.get()) return;

            if (entity.tickCount % 400 == 0) {
                if (animal.getAge() == 0 && !animal.isInLove()) {

                    // User-friendly percentage roll from config
                    double roll = entity.level().random.nextDouble() * 100.0;
                    if (roll < BeyondLimitsConfig.BREEDING_CHANCE_PERCENT.get()) {

                        List<Animal> partners = entity.level().getEntitiesOfClass(
                                Animal.class,
                                animal.getBoundingBox().inflate(8.0D)
                        );

                        if (partners.size() >= 2 && partners.size() < 16) {
                            for (Animal partner : partners) {
                                if (partner != animal &&
                                        partner.getClass() == animal.getClass() &&
                                        partner.getAge() == 0 &&
                                        !partner.isInLove()) {

                                    animal.setInLove(null);
                                    partner.setInLove(null);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        Level level = player.level();

        if (level.dimension() != Level.NETHER) return;

        if (!level.isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            SoulLinkData data = SoulLinkData.get((ServerLevel) level);
            Map<String, BlockPos> links = data.getAllLinks(player.getUUID());

            handleLinkEffects(serverPlayer, data, links, level);
        }

        // Anchor Physics
        boolean hasAnchor = player.hasEffect(MobEffects.DAMAGE_RESISTANCE)
                && player.getEffect(MobEffects.DAMAGE_RESISTANCE).getAmplifier() >= 1;

        if (!hasAnchor) return;

        if (player.isInLava()) {
            Vec3 look = player.getLookAngle();
            double speedMult = 0.15;

            if (player.zza != 0 || player.xxa != 0) {
                Vec3 targetLeftRight = player.getLookAngle().yRot((float)Math.PI/2f).scale(player.xxa * speedMult);
                Vec3 targetForwardBack = look.scale(player.zza * speedMult);
                Vec3 finalVel = targetForwardBack.add(targetLeftRight);

                player.setDeltaMovement(finalVel.x, finalVel.y * 0.8, finalVel.z);

                if (player.getDeltaMovement().y > 0) {
                    player.setDeltaMovement(player.getDeltaMovement().add(0, 0.05, 0));
                }
                player.hurtMarked = true;
            }
        }

        // Soul Sand Speed
        BlockPos posAtFeet = BlockPos.containing(player.getX(), player.getY() - 0.1, player.getZ());
        BlockState ground = level.getBlockState(posAtFeet);

        if (ground.is(net.minecraft.world.level.block.Blocks.SOUL_SAND) || ground.is(net.minecraft.world.level.block.Blocks.SOUL_SOIL)) {
            if (player.zza != 0 || player.xxa != 0) {
                double boost = 0.08;
                Vec3 look = player.getLookAngle();
                player.setDeltaMovement(
                        player.getDeltaMovement().x + (look.x * player.zza * boost),
                        player.getDeltaMovement().y,
                        player.getDeltaMovement().z + (look.z * player.zza * boost)
                );
                player.hurtMarked = true;
            }
        }
    }

    private static void handleLinkEffects(ServerPlayer serverPlayer, SoulLinkData data, Map<String, BlockPos> links, Level level) {
        String[] types = {"malice", "cinder", "bastion", "anchor"};
        for (String type : types) {
            if (links.containsKey(type)) {
                BlockPos pos = links.get(type);
                if (!level.getBlockState(pos).is(getBlockForType(type))) {
                    data.removeLink(serverPlayer.getUUID(), type);
                    serverPlayer.displayClientMessage(Component.literal("§8The " + type + " link has been severed..."), true);
                    continue;
                }

                if (type.equals("malice")) {
                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 120, 0, true, true));
                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 120, 1, true, true));
                }
                if (type.equals("cinder")) {
                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 120, 1, true, true));
                }
                if (type.equals("bastion")) {
                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 1, true, true));
                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.LUCK, 120, 0, true, true));
                }
                if (type.equals("anchor")) {
                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 120, 1, true, true));
                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 120, 0, true, true));
                }
            }
        }
    }

    private static net.minecraft.world.level.block.Block getBlockForType(String type) {
        return switch (type) {
            case "malice" -> ModBlocks.FRAGRANCE_MALICE.get();
            case "cinder" -> ModBlocks.FRAGRANCE_CINDER.get();
            case "bastion" -> ModBlocks.FRAGRANCE_BASTION.get();
            case "anchor" -> ModBlocks.INTENSIFIED_ANCHOR.get();
            default -> net.minecraft.world.level.block.Blocks.AIR;
        };
    }
}