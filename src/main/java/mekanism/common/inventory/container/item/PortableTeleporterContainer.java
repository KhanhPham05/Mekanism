package mekanism.common.inventory.container.item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import mekanism.common.inventory.container.IEmptyContainer;
import mekanism.common.inventory.container.MekanismContainerTypes;
import mekanism.common.item.ItemPortableTeleporter;
import mekanism.common.util.text.TextComponentUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;

public class PortableTeleporterContainer extends MekanismItemContainer implements IEmptyContainer {

    public PortableTeleporterContainer(int id, PlayerInventory inv, Hand hand, ItemStack stack) {
        super(MekanismContainerTypes.PORTABLE_TELEPORTER, id, inv, hand, stack);
    }

    public PortableTeleporterContainer(int id, PlayerInventory inv, PacketBuffer buf) {
        this(id, inv, buf.readEnumValue(Hand.class), getStackFromBuffer(buf, ItemPortableTeleporter.class));
    }

    public Hand getHand() {
        return hand;
    }

    public ItemStack getStack() {
        return stack;
    }

    @Nullable
    @Override
    public Container createMenu(int i, @Nonnull PlayerInventory inv, @Nonnull PlayerEntity player) {
        return new PortableTeleporterContainer(i, inv, hand, stack);
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return TextComponentUtil.translate("mekanism.container.portable_teleporter");
    }
}