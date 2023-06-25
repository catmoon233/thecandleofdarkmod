package net.mcreator.tcod.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.tcod.TcodModVariables;
import net.mcreator.tcod.TcodMod;

import java.util.Map;

public class LookservertimerlcProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TcodMod.LOGGER.warn("Failed to load dependency entity for procedure Lookservertimerlc!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(
					new StringTextComponent(("\u5F53\u524D\u670D\u52A1\u5668\u65F6\u95F4\u4E3A:" + TcodModVariables.servert)), (false));
		}
	}
}
