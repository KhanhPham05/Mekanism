package mekanism.common.block.basic;

import java.util.Locale;
import javax.annotation.Nonnull;
import mekanism.api.Coord4D;
import mekanism.common.Mekanism;
import mekanism.common.block.BlockTileDrops;
import mekanism.common.block.interfaces.IHasInventory;
import mekanism.common.block.interfaces.ITieredBlock;
import mekanism.common.tier.InductionCellTier;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.induction_cell.TileEntityAdvancedInductionCell;
import mekanism.common.tile.induction_cell.TileEntityBasicInductionCell;
import mekanism.common.tile.induction_cell.TileEntityEliteInductionCell;
import mekanism.common.tile.induction_cell.TileEntityUltimateInductionCell;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockInductionCell extends BlockTileDrops implements ITieredBlock<InductionCellTier>, IHasInventory {

    private final InductionCellTier tier;

    public BlockInductionCell(InductionCellTier tier) {
        super(Material.IRON);
        this.tier = tier;
        setHardness(5F);
        setResistance(10F);
        setRegistryName(new ResourceLocation(Mekanism.MODID, tier.getBaseTier().getSimpleName().toLowerCase(Locale.ROOT) + "_induction_cell"));
    }

    @Override
    public InductionCellTier getTier() {
        return tier;
    }

    @Override
    @Deprecated
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos) {
        if (!world.isRemote) {
            TileEntity tileEntity = new Coord4D(pos, world).getTileEntity(world);
            if (tileEntity instanceof TileEntityMekanism) {
                ((TileEntityMekanism) tileEntity).onNeighborChange(neighborBlock);
            }
        }
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
        switch (tier) {
            case BASIC:
                return new TileEntityBasicInductionCell();
            case ADVANCED:
                return new TileEntityAdvancedInductionCell();
            case ELITE:
                return new TileEntityEliteInductionCell();
            case ULTIMATE:
                return new TileEntityUltimateInductionCell();
        }
        return null;
    }

    @Override
    public int getInventorySize() {
        return 2;
    }
}