package com.github.alexthe666.iceandfire.integration.tinkersconstruct;

import net.minecraftforge.fml.common.Loader;

public class TinkersConstructCompatBridge {

    private static final boolean IS_TIC_LOADED = Loader.isModLoaded("tconstruct");

    public static void preInitTinkersConstructCompat() {
        if (IS_TIC_LOADED) {
            TinkersConstructCompat.preInit();
        }
    }

    public static void initTinkersConstructCompat() {
        if (IS_TIC_LOADED) {
            TinkersConstructCompat.init();
        }
    }

    public static void postInitTinkersConstructCompat() {
        if (IS_TIC_LOADED) {
            TinkersConstructCompat.postInit();
        }
    }
}
