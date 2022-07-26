package com.cleanroommc.blockdelayremover.mixins;


import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP
{
    @Shadow
    private int blockHitDelay;
    @Shadow
    private GameType currentGameType;

    @Inject(method = "onPlayerDamageBlock", at=@At("HEAD"))
    private void onPlayerDamageBlock(BlockPos posBlock, EnumFacing directionFacing, CallbackInfoReturnable<Boolean> cir){
        if (currentGameType.isSurvivalOrAdventure()){
            blockHitDelay = 0;
        }
    }
}
