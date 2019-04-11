package com.bewitchment.common.integration.patchouli.processors;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.registry.Ritual;
import net.minecraft.util.ResourceLocation;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariableProvider;

public class RitualProcessor implements IComponentProcessor {
	private Ritual ritual;

	@Override
	public String process(String val) {
		if (val.startsWith("circle")) return "";
		return "";
	}

	@Override
	public void setup(IVariableProvider<String> json) {
		ritual = BewitchmentAPI.REGISTRY_RITUAL.getValue(new ResourceLocation(json.get("ritual")));
	}
}