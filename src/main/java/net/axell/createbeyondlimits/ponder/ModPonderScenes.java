package net.axell.createbeyondlimits.ponder;

import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.Vec3;

public class ModPonderScenes {

    public static void cinderScene(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("cinder", "The Ember of the Nether");
        scene.configureBasePlate(0, 0, 9);
        // Updated to match your NBT: [4, 1, 4] is the center fragrance
        BlockPos centerPos = new BlockPos(4, 1, 4);

        scene.world().showSection(util.select().layer(0), Direction.UP);
        scene.idle(10);

        scene.addKeyframe();
        scene.world().showSection(util.select().position(centerPos), Direction.DOWN);
        scene.idle(15);
        scene.overlay().showText(40).text("createbeyondlimits.ponder.cinder.text_1").independent(40);
        renderPulse(scene, util, centerPos);

        scene.addKeyframe();
        // Reduced duration from 80 to 50 so it disappears before the conflict phase
        scene.overlay().showText(50).text("createbeyondlimits.ponder.cinder.text_2")
                .colored(PonderPalette.GREEN).pointAt(util.vector().blockSurface(centerPos, Direction.UP));
        renderParticles(scene, util, centerPos, ParticleTypes.SOUL_FIRE_FLAME);

        renderSymmetricalOverbind(scene, util, centerPos, "cinder");
    }

    public static void maliceScene(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("malice", "The Vigor of Hatred");
        scene.configureBasePlate(0, 0, 9);
        BlockPos centerPos = new BlockPos(4, 1, 4);

        scene.world().showSection(util.select().layer(0), Direction.UP);
        scene.idle(10);

        scene.addKeyframe();
        scene.world().showSection(util.select().position(centerPos), Direction.DOWN);
        scene.idle(15);
        scene.overlay().showText(40).text("createbeyondlimits.ponder.malice.text_1").independent(40);
        renderPulse(scene, util, centerPos);

        scene.addKeyframe();
        scene.overlay().showText(50).text("createbeyondlimits.ponder.malice.text_2")
                .colored(PonderPalette.RED).pointAt(util.vector().blockSurface(centerPos, Direction.UP));
        renderParticles(scene, util, centerPos, ParticleTypes.ANGRY_VILLAGER);

        renderSymmetricalOverbind(scene, util, centerPos, "malice");
    }

    public static void bastionScene(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("bastion", "The Shield of the Bastion");
        scene.configureBasePlate(0, 0, 9);
        BlockPos centerPos = new BlockPos(4, 1, 4);

        scene.world().showSection(util.select().layer(0), Direction.UP);
        scene.idle(10);

        scene.addKeyframe();
        scene.world().showSection(util.select().position(centerPos), Direction.DOWN);
        scene.idle(15);
        scene.overlay().showText(40).text("createbeyondlimits.ponder.bastion.text_1").independent(40);
        renderPulse(scene, util, centerPos);

        scene.addKeyframe();
        // Fixed duration to prevent overlapping with Phase 3
        scene.overlay().showText(50).text("createbeyondlimits.ponder.bastion.text_2")
                .colored(PonderPalette.GREEN).pointAt(util.vector().blockSurface(centerPos, Direction.UP));
        renderParticles(scene, util, centerPos, ParticleTypes.HAPPY_VILLAGER);

        renderSymmetricalOverbind(scene, util, centerPos, "bastion");
    }

    private static void renderPulse(SceneBuilder scene, SceneBuildingUtil util, BlockPos pos) {
        for(int i = 0; i < 12; i++) {
            double angle = i * (Math.PI * 2 / 12);
            scene.effects().emitParticles(util.vector().centerOf(pos),
                    scene.effects().simpleParticleEmitter(ParticleTypes.PORTAL, new Vec3(Math.cos(angle) * 0.2, 0, Math.sin(angle) * 0.2)), 1, 1);
            if(i % 3 == 0) scene.idle(2);
        }
        scene.idle(10);
    }

    private static void renderParticles(SceneBuilder scene, SceneBuildingUtil util, BlockPos pos, net.minecraft.core.particles.ParticleOptions type) {
        for(int i = 0; i < 4; i++) {
            scene.effects().emitParticles(util.vector().centerOf(pos).add(0, 0.5, 0),
                    scene.effects().simpleParticleEmitter(type, Vec3.ZERO), 4, 1);
            scene.idle(10);
        }
    }

    private static void renderSymmetricalOverbind(SceneBuilder scene, SceneBuildingUtil util, BlockPos centerPos, String id) {
        scene.addKeyframe();
        // Updated coordinates to match NBT: 1 and 7 on the X axis
        BlockPos leftPos = new BlockPos(1, 1, 4);
        BlockPos rightPos = new BlockPos(7, 1, 4);

        Selection centerSel = util.select().position(centerPos);
        Selection leftSel = util.select().position(leftPos);
        Selection rightSel = util.select().position(rightPos);

        // 1. Conflict Warning
        scene.world().showSection(leftSel.add(rightSel), Direction.DOWN);
        scene.idle(15);
        scene.overlay().showOutline(PonderPalette.RED, leftPos, leftSel, 30);
        scene.overlay().showOutline(PonderPalette.RED, rightPos, rightSel, 30);
        scene.overlay().showText(40).text("createbeyondlimits.ponder." + id + ".text_3")
                .colored(PonderPalette.RED).placeNearTarget();
        scene.idle(25);

        // 2. Damage Indicator
        scene.overlay().showText(40).text("createbeyondlimits.ponder." + id + ".text_4")
                .pointAt(util.vector().blockSurface(rightPos, Direction.UP));
        scene.effects().emitParticles(util.vector().centerOf(rightPos),
                scene.effects().simpleParticleEmitter(ParticleTypes.DAMAGE_INDICATOR, Vec3.ZERO), 4, 1);
        scene.idle(35);

        // 3. Highlight Original for destruction
        scene.addKeyframe();
        scene.overlay().showOutline(PonderPalette.WHITE, centerPos, centerSel, 30);
        scene.overlay().showText(40).text("createbeyondlimits.ponder." + id + ".text_5")
                .pointAt(util.vector().blockSurface(centerPos, Direction.UP));
        scene.idle(25);

        // 4. Destroy Center (Text and Block disappear together)
        scene.world().hideSection(centerSel, Direction.UP);
        scene.effects().emitParticles(util.vector().centerOf(centerPos),
                scene.effects().simpleParticleEmitter(ParticleTypes.LARGE_SMOKE, Vec3.ZERO), 10, 1);
        scene.idle(15);

        // 5. Claim New
        scene.addKeyframe();
        scene.overlay().showText(40).text("createbeyondlimits.ponder." + id + ".text_6")
                .colored(PonderPalette.GREEN).pointAt(util.vector().blockSurface(leftPos, Direction.UP));
        renderPulse(scene, util, leftPos);
        scene.idle(25);

        // 6. Lock demonstration
        scene.overlay().showOutline(PonderPalette.RED, rightPos, rightSel, 40);
        scene.overlay().showText(50).text("createbeyondlimits.ponder." + id + ".text_7")
                .colored(PonderPalette.RED).pointAt(util.vector().blockSurface(rightPos, Direction.WEST));
        scene.effects().emitParticles(util.vector().centerOf(rightPos),
                scene.effects().simpleParticleEmitter(ParticleTypes.DAMAGE_INDICATOR, Vec3.ZERO), 2, 1);
        scene.idle(20);
    }
}