package net.mcreator.tcod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.World;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.tcod.TcodModVariables;
import net.mcreator.tcod.TcodMod;

import java.util.Map;
import java.util.HashMap;

public class YngxProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntityTick(LivingEvent.LivingUpdateEvent event) {
			Entity entity = event.getEntityLiving();
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TcodMod.LOGGER.warn("Failed to load dependency entity for procedure Yngx!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double nand = 0;
		if (entity.getPersistentData().getDouble("nand") != nand) {
			if (TcodModVariables.realday <= 10) {
				nand = TcodModVariables.realday;
			}
			if (TcodModVariables.realday > 10) {
				nand = (10 - TcodModVariables.realday);
			}
			if ((entity.getCapability(TcodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new TcodModVariables.PlayerVariables())).plsx != 1) {
				((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.MAX_HEALTH)
						.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.MAX_HEALTH).getValue()
								+ nand - entity.getPersistentData().getDouble("nand")));
				((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR)
						.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR).getValue()
								+ (nand - entity.getPersistentData().getDouble("nand")) * 3));
				((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
						.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue()
								+ nand - entity.getPersistentData().getDouble("nand")));
				((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS)
						.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS).getValue()
								+ (nand - entity.getPersistentData().getDouble("nand")) * 1));
				((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_SPEED)
						.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_SPEED).getValue()
								+ (nand - entity.getPersistentData().getDouble("nand")) * 0.003));
				entity.getPersistentData().putDouble("nand", TcodModVariables.realday);
			}
		}
	}
}
