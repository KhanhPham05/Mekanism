package mekanism.common.tile.interfaces;

import javax.annotation.Nonnull;
import mekanism.common.base.ISustainedInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public interface ITileContainer extends ISidedInventory, ISustainedInventory {

    default boolean hasInventory() {
        return true;
    }

    @Nonnull
    NonNullList<ItemStack> getInventory();

    @Override
    default int getSizeInventory() {
        return hasInventory() ? getInventory().size() : 0;
    }

    @Override
    default boolean isEmpty() {
        return !hasInventory() || getInventory().stream().allMatch(ItemStack::isEmpty);
    }

    @Nonnull
    @Override
    default ItemStack getStackInSlot(int slotID) {
        return hasInventory() ? getInventory().get(slotID) : ItemStack.EMPTY;
    }

    @Nonnull
    @Override
    default ItemStack decrStackSize(int slotID, int amount) {
        return hasInventory() ? ItemStackHelper.getAndSplit(getInventory(), slotID, amount) : ItemStack.EMPTY;
    }

    @Nonnull
    @Override
    default ItemStack removeStackFromSlot(int slotID) {
        return hasInventory() ? ItemStackHelper.getAndRemove(getInventory(), slotID) : ItemStack.EMPTY;
    }

    @Override
    default int getInventoryStackLimit() {
        return 64;
    }

    @Override
    default void openInventory(@Nonnull EntityPlayer player) {
    }

    @Override
    default void closeInventory(@Nonnull EntityPlayer player) {
    }

    @Override
    default boolean hasCustomName() {
        return true;
    }

    @Override
    default boolean isItemValidForSlot(int slotID, @Nonnull ItemStack itemstack) {
        return hasInventory();
    }

    @Override
    default boolean canInsertItem(int slotID, @Nonnull ItemStack itemstack, @Nonnull EnumFacing side) {
        return isItemValidForSlot(slotID, itemstack);
    }

    @Override
    default boolean canExtractItem(int slotID, @Nonnull ItemStack itemstack, @Nonnull EnumFacing side) {
        return hasInventory();
    }

    @Override
    default int getField(int id) {
        return 0;
    }

    @Override
    default void setField(int id, int value) {
    }

    @Override
    default int getFieldCount() {
        return 0;
    }

    @Override
    default void clear() {
    }

    @Nonnull
    @Override
    default ITextComponent getDisplayName() {
        return new TextComponentString(getName());
    }
}