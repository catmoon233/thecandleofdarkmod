package net.mcreator.tcod.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.tcod.TcodModVariables;
import net.mcreator.tcod.TcodMod;

import java.util.Map;

public class Look3Procedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TcodMod.LOGGER.warn("Failed to load dependency entity for procedure Look3!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double a = 0;
		a = Math.floor(TcodModVariables.serverdiday / 20);
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(("\u5F53\u524D\u5FAA\u73AF\u6570\u4E3A:" + a)), (false));
		}
	}
}
