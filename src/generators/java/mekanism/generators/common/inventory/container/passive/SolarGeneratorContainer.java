package mekanism.generators.common.inventory.container.passive;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import mekanism.common.util.text.TextComponentUtil;
import mekanism.generators.common.inventory.container.GeneratorsContainerTypes;
import mekanism.generators.common.tile.TileEntitySolarGenerator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;

public class SolarGeneratorContainer extends PassiveGeneratorContainer<TileEntitySolarGenerator> {

    public SolarGeneratorContainer(int id, PlayerInventory inv, TileEntitySolarGenerator tile) {
        super(GeneratorsContainerTypes.SOLAR_GENERATOR, id, inv, tile);
    }

    public SolarGeneratorContainer(int id, PlayerInventory inv, PacketBuffer buf) {
        this(id, inv, getTileFromBuf(buf, TileEntitySolarGenerator.class));
    }

    @Nullable
    @Override
    public Container createMenu(int i, @Nonnull PlayerInventory inv, @Nonnull PlayerEntity player) {
        return new SolarGeneratorContainer(i, inv, tile);
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return TextComponentUtil.translate("mekanismgenerators.container.solar_generator");
    }
}