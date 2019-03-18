package com.github.alexthe666.iceandfire.integration.tinkersconstruct;

import net.minecraftforge.fml.common.Loader;

public class TinkersConstructCompatBridge {

    private static final String TIC_MOD_ID = "tconstruct";

    public static void loadTConstructCompat() {
        if (Loader.isModLoaded(TIC_MOD_ID)) {
            TinkersConstructCompat.preInit();
        }
    }
}
