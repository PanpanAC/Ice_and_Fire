package com.github.alexthe666.iceandfire.integration.tinkersconstruct.book;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.tconstruct.library.book.TinkerBook;

// This class was taken from EnderIO
// Source: https://github.com/SleepyTrousers/EnderIO/blob/master/enderio-integration-tic/src/main/java/crazypants/enderio/integration/tic/book/TicBook.java
// It was slightly modified (to match this mod), but it's mostly the same class.

public class TicBook {

    @SideOnly(Side.CLIENT)
    public static void registerBookPages() {
        TinkerBook.INSTANCE.addRepository(new FileRepository("iceandfire:lang/tinkers_book"));
        TinkerBook.INSTANCE.addTransformer(new CustomBookTransformer());
    }
}
