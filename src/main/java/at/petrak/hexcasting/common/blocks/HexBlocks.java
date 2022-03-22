package at.petrak.hexcasting.common.blocks;

import at.petrak.hexcasting.HexMod;
import at.petrak.hexcasting.common.blocks.circles.BlockEntitySlate;
import at.petrak.hexcasting.common.blocks.circles.BlockRedirector;
import at.petrak.hexcasting.common.blocks.circles.BlockSlate;
import at.petrak.hexcasting.common.blocks.circles.impetuses.BlockAbstractImpetus;
import at.petrak.hexcasting.common.blocks.circles.impetuses.BlockEntityRightClickImpetus;
import at.petrak.hexcasting.common.blocks.circles.impetuses.BlockRightClickImpetus;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HexBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HexMod.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
        ForgeRegistries.BLOCK_ENTITIES, HexMod.MOD_ID);

    public static final RegistryObject<Block> CONJURED = BLOCKS.register("conjured",
        () -> new BlockConjured(
            BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.DIAMOND)
                .sound(SoundType.AMETHYST)
                .noDrops()
                .instabreak()
                .noOcclusion()
                .isSuffocating(HexBlocks::never)
                .isViewBlocking(HexBlocks::never)));

    private static BlockBehaviour.Properties slateish() {
        return BlockBehaviour.Properties
            .of(Material.STONE, MaterialColor.DEEPSLATE)
            .sound(SoundType.DEEPSLATE_TILES)
            .strength(4f, 4f);
    }

    public static final RegistryObject<BlockSlate> SLATE = BLOCKS.register("slate",
        () -> new BlockSlate(slateish()));
    public static final RegistryObject<BlockRedirector> REDIRECTOR = BLOCKS.register("redirector",
        () -> new BlockRedirector(slateish()));


    public static final RegistryObject<Block> EMPTY_IMPETUS = BLOCKS.register("empty_impetus",
        () -> new Block(slateish()));
    public static final RegistryObject<BlockRightClickImpetus> IMPETUS_RIGHTCLICK = BLOCKS.register(
        "impetus_rightclick",
        () -> new BlockRightClickImpetus(slateish()
            .lightLevel(bs -> bs.getValue(BlockAbstractImpetus.ENERGIZED) ? 15 : 0)));

    // Decoration?!
    public static final RegistryObject<Block> SLATE_BLOCK = BLOCKS.register("slate_block",
        () -> new Block(slateish().strength(2f, 4f)));

    public static final RegistryObject<BlockEntityType<BlockEntityConjured>> CONJURED_TILE = BLOCK_ENTITIES.register(
        "conjured_tile",
        () -> BlockEntityType.Builder.of(BlockEntityConjured::new, CONJURED.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntitySlate>> SLATE_TILE = BLOCK_ENTITIES.register(
        "slate_tile",
        () -> BlockEntityType.Builder.of(BlockEntitySlate::new, SLATE.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityRightClickImpetus>> IMPETUS_RIGHTCLICK_TILE =
        BLOCK_ENTITIES.register("impetus_rightclick_tile",
            () -> BlockEntityType.Builder.of(BlockEntityRightClickImpetus::new, IMPETUS_RIGHTCLICK.get()).build(null));


    private static boolean never(Object... args) {
        return false;
    }
}
