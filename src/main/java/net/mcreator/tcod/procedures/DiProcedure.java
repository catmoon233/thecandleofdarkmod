package net.mcreator.tcod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.IWorld;

import net.mcreator.tcod.TcodModVariables;
import net.mcreator.tcod.TcodMod;

import java.util.Map;
import java.util.HashMap;

public class DiProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onWorldTick(TickEvent.WorldTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				IWorld world = event.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("world", world);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TcodMod.LOGGER.warn("Failed to load dependency world for procedure Di!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double a = 0;
		TcodModVariables.servert = (world.getWorldInfo().getDayTime());
		TcodModVariables.serverdiday = (TcodModVariables.servert / 24000);
		a = Math.floor(TcodModVariables.serverdiday / 20);
		TcodModVariables.realday = Math.floor(TcodModVariables.serverdiday - a * 20);
	}
}
