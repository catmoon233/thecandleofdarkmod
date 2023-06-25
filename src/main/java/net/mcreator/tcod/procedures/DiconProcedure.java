package net.mcreator.tcod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.tcod.TcodModVariables;
import net.mcreator.tcod.TcodMod;

import java.util.Map;
import java.util.HashMap;

public class DiconProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntitySpawned(EntityJoinWorldEvent event) {
			Entity entity = event.getEntity();
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			World world = event.getWorld();
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
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TcodMod.LOGGER.warn("Failed to load dependency world for procedure Dicon!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TcodMod.LOGGER.warn("Failed to load dependency entity for procedure Dicon!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		double nand = 0;
		new Object() {
			private int ticks = 0;
			private float waitTicks;
			private IWorld world;

			public void start(IWorld world, int waitTicks) {
				this.waitTicks = waitTicks;
				MinecraftForge.EVENT_BUS.register(this);
				this.world = world;
			}

			@SubscribeEvent
			public void tick(TickEvent.ServerTickEvent event) {
				if (event.phase == TickEvent.Phase.END) {
					this.ticks += 1;
					if (this.ticks >= this.waitTicks)
						run();
				}
			}

			private void run() {
				if ((entity.getCapability(TcodModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new TcodModVariables.PlayerVariables())).plsx == 1) {
					if (TcodModVariables.realday <= 10) {
						((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.MAX_HEALTH).setBaseValue(
								(((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.MAX_HEALTH).getValue()
										+ (10 - TcodModVariables.realday) * 5));
						((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR)
								.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR).getValue()
										+ (10 - TcodModVariables.realday) * 3));
						((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_DAMAGE).setBaseValue(
								(((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue() + 10
										- TcodModVariables.realday));
						((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS).setBaseValue(
								(((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS).getValue()
										+ (10 - TcodModVariables.realday) * 1));
						((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_SPEED).setBaseValue(
								(((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_SPEED).getValue()
										+ (10 - TcodModVariables.realday) * 0.003));
						entity.getPersistentData().putDouble("nand", TcodModVariables.realday);
					}
					if (TcodModVariables.realday > 10) {
						((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.MAX_HEALTH).setBaseValue(
								(((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.MAX_HEALTH).getValue()
										+ (TcodModVariables.realday - 10) * 5));
						((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR)
								.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR).getValue()
										+ (TcodModVariables.realday - 10) * 3));
						((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_DAMAGE).setBaseValue(
								(((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue()
										+ TcodModVariables.realday - 10));
						((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS).setBaseValue(
								(((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS).getValue()
										+ (TcodModVariables.realday - 10) * 1));
						((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_SPEED).setBaseValue(
								(((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_SPEED).getValue()
										+ (TcodModVariables.realday - 10) * 0.003));
						entity.getPersistentData().putDouble("nand", TcodModVariables.realday);
					}
				}
				MinecraftForge.EVENT_BUS.unregister(this);
			}
		}.start(world, (int) 2);
	}
}
