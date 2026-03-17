package net.axell.createbeyondlimits.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class AromaRegenModel extends GeoModel<AromaBlockEntity> {

    public AromaRegenModel() {}

    @Override
    public RenderType getRenderType(AromaBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }

    @Override
    public ResourceLocation getModelResource(AromaBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath("createbeyondlimits", "geo/aroma_regen.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AromaBlockEntity animatable) {
        // For SpeedModel, change this to aroma_speed.png, etc.
        return ResourceLocation.fromNamespaceAndPath("createbeyondlimits", "textures/block/aroma_regen.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AromaBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath("createbeyondlimits", "animations/aroma_regen.animation.json");
    }

    @Override
    public void setCustomAnimations(AromaBlockEntity animatable, long instanceId, AnimationState<AromaBlockEntity> animationState) {
        CoreGeoBone heart = getAnimationProcessor().getBone("heart");

        if (heart != null && animatable.getLevel() != null) {
            Player player = animatable.getLevel().getNearestPlayer(
                    animatable.getBlockPos().getX() + 0.5,
                    animatable.getBlockPos().getY() + 0.5,
                    animatable.getBlockPos().getZ() + 0.5,
                    8.0, false);

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
                heart.setRotY((animatable.getLevel().getGameTime() + animationState.getPartialTick()) * 0.05f);
            }
        }
    }
}