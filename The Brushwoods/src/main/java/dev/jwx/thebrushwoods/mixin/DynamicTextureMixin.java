package dev.jwx.thebrushwoods.mixin;

/*
 * This file is part of True Darkness and is licensed to the project under
 * terms that are compatible with the GNU Lesser General Public License.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership and licensing.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import dev.jwx.thebrushwoods.util.Darkness;
import dev.jwx.thebrushwoods.util.TextureAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.platform.NativeImage;

import net.minecraft.client.renderer.texture.DynamicTexture;

import static dev.jwx.thebrushwoods.util.Darkness.enabled;

@Mixin(DynamicTexture.class)
public class DynamicTextureMixin implements TextureAccess {
    @Shadow
    NativeImage pixels;

    private boolean enableHook = false;

    @Inject(method = "upload", at = @At(value = "HEAD"))
    private void onUpload(CallbackInfo ci) {
        if (enableHook && enabled && pixels != null) {
            final NativeImage img = pixels;

            for (int b = 0; b < 16; b++) {
                for (int s = 0; s < 16; s++) {
                    final int color = Darkness.darken(img.getPixelRGBA(b, s), b, s);
                    img.setPixelRGBA(b, s, color);
                }
            }
        }
    }

    @Override
    public void darkness_enableUploadHook() {
        enableHook = true;
    }
}