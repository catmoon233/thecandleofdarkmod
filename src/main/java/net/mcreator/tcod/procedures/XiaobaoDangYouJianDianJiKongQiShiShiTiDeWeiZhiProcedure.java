package net.mcreator.tcod.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.tcod.TcodModVariables;
import net.mcreator.tcod.TcodMod;

import java.util.Map;

public class XiaobaoDangYouJianDianJiKongQiShiShiTiDeWeiZhiProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TcodMod.LOGGER.warn("Failed to load dependency entity for procedure XiaobaoDangYouJianDianJiKongQiShiShiTiDeWeiZhi!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			double _setval = 1;
			entity.getCapability(TcodModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.opemn = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
