package com.bewitchment.common.integration.patchouli;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.block.BlockGlyph;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.registry.ModObjects;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.function.Predicate;

public class BewitchmentPatchouli {
	private static final Predicate<IBlockState> ANY = state -> state.getBlock() == ModObjects.glyph && state.getValue(BlockGlyph.TYPE) != GlyphType.GOLDEN;
	private static final Predicate<IBlockState> GOLDEN = state -> state.getBlock() == ModObjects.glyph && state.getValue(BlockGlyph.TYPE) == GlyphType.GOLDEN;

	public static void init() {
		PatchouliAPI.instance.registerMultiblock(new ResourceLocation(Bewitchment.MODID, "circle_small"), PatchouliAPI.instance.makeMultiblock(new String[][]{{"  AAA  ", " A   A ", "A     A", "A  0  A", "A     A", " A   A ", "  AAA  "}}, 'A', PatchouliAPI.instance.predicateMatcher(ModObjects.glyph.getDefaultState().withProperty(BlockGlyph.TYPE, GlyphType.NORMAL), ANY), '0', PatchouliAPI.instance.predicateMatcher(ModObjects.glyph.getDefaultState().withProperty(BlockGlyph.TYPE, GlyphType.GOLDEN), GOLDEN), ' ', PatchouliAPI.instance.anyMatcher()));
		PatchouliAPI.instance.registerMultiblock(new ResourceLocation(Bewitchment.MODID, "circle_medium"), PatchouliAPI.instance.makeMultiblock(new String[][]{{"   AAAAA   ", "  A     A  ", " A  AAA  A ", "A  A   A  A", "A A     A A", "A A  0  A A", "A A     A A", "A  A   A  A", " A  AAA  A ", "  A     A  ", "   AAAAA   "}}, 'A', PatchouliAPI.instance.predicateMatcher(ModObjects.glyph.getDefaultState().withProperty(BlockGlyph.TYPE, GlyphType.NORMAL), ANY), '0', PatchouliAPI.instance.predicateMatcher(ModObjects.glyph.getDefaultState().withProperty(BlockGlyph.TYPE, GlyphType.GOLDEN), GOLDEN), ' ', PatchouliAPI.instance.anyMatcher()));
		PatchouliAPI.instance.registerMultiblock(new ResourceLocation(Bewitchment.MODID, "circle_large"), PatchouliAPI.instance.makeMultiblock(new String[][]{{"    AAAAAAA    ", "   A       A   ", "  A  AAAAA  A  ", " A  A     A  A ", "A  A  AAA  A  A", "A A  A   A  A A", "A A A     A A A", "A A A  0  A A A", "A A A     A A A", "A A  A   A  A A", "A  A  AAA  A  A", " A  A     A  A ", "  A  AAAAA  A  ", "   A       A   ", "    AAAAAAA    "}}, 'A', PatchouliAPI.instance.predicateMatcher(ModObjects.glyph.getDefaultState().withProperty(BlockGlyph.TYPE, GlyphType.NORMAL), ANY), '0', PatchouliAPI.instance.predicateMatcher(ModObjects.glyph.getDefaultState().withProperty(BlockGlyph.TYPE, GlyphType.GOLDEN), GOLDEN), ' ', PatchouliAPI.instance.anyMatcher()));
	}
}