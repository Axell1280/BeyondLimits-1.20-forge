package net.axell.createbeyondlimits.client;

import net.axell.createbeyondlimits.block.entity.AromaBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class AromaSuperchargedModel extends GeoModel<AromaBlockEntity> {

    @Override
    public ResourceLocation getModelResource(AromaBlockEntity animatable) {
        // Pointing to your new supercharge_aroma geo file
        return ResourceLocation.fromNamespaceAndPath("createbeyondlimits", "geo/supercharged_aroma.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AromaBlockEntity animatable) {
        // Using the 'blls' texture you mentioned for the supercharged version
        return ResourceLocation.fromNamespaceAndPath("createbeyondlimits", "textures/block/supercharge.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AromaBlockEntity animatable) {
        // We can reuse the same animations since the bone names (impact/heart) are compatible
        return ResourceLocation.fromNamespaceAndPath("createbeyondlimits", "animations/aroma_regen.animation.json");
    }

    @Override
    public RenderType getRenderType(AromaBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }

    @Override
    public void setCustomAnimations(AromaBlockEntity animatable, long instanceId, AnimationState<AromaBlockEntity> animationState) {
        // Note: In your geo.json, the floating part is named "impact"
        CoreGeoBone heart = getAnimationProcessor().getBone("heart");

        if (heart != null && animatable.getLevel() != null) {
            Player player = animatable.getLevel().getNearestPlayer(
                    animatable.getBlockPos().getX() + 0.5,
                    animatable.getBlockPos().getY() + 0.5,
                    animatable.getBlockPos().getZ() + 0.5,
                    12.0, // Increased range to 12 blocks because it's "Supercharged"
                    false);

            if (player != null) {
                double dX = player.getX() - (animatable.getBlockPos().getX() + 0.5);
                double dY = player.getEyeY() - (animatable.getBlockPos().getY() + 0.4);
                double dZ = player.getZ() - (animatable.getBlockPos().getZ() + 0.5);

                float yaw = (float) (Math.atan2(dZ, dX) * (180 / Math.PI)) - 90.0F;
                float dist = (float) Math.sqrt(dX * dX + dZ * dZ);
                float pitch = (float) (-(Math.atan2(dY, dist) * (180 / Math.PI)));

                heart.setRotY(-yaw * ((float)Math.PI / 180f));
                heart.setRotX(pitch * ((float)Math.PI / 180f));
            } else {
                // Faster spin when idle compared to normal ones
                heart.setRotY((animatable.getLevel().getGameTime() + animationState.getPartialTick()) * 0.1f);
            }
        }
    }
}