package net.mcreator.tcod.procedures;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.tcod.TcodMod;

import java.util.Map;

public class ZlsxProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TcodMod.LOGGER.warn("Failed to load dependency entity for procedure Zlsx!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double nand = 0;
		((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.MAX_HEALTH)
				.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.MAX_HEALTH).getValue() + nand * 5));
		((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR)
				.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR).getValue() + nand * 3));
		((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_DAMAGE).setBaseValue(
				(((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue() + nand * 3));
		((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS).setBaseValue(
				(((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS).getValue() + nand * 1));
		((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_SPEED).setBaseValue(
				(((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_SPEED).getValue() + nand * 0.003));
	}
}
