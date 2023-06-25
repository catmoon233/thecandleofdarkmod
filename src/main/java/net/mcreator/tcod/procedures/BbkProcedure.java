package net.mcreator.tcod.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.tcod.item.XiaobaoItem;
import net.mcreator.tcod.item.Xiaobao2Item;
import net.mcreator.tcod.TcodModVariables;
import net.mcreator.tcod.TcodMod;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;
import java.util.HashMap;

public class BbkProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				Entity entity = event.player;
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
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TcodMod.LOGGER.warn("Failed to load dependency entity for procedure Bbk!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double aw = 0;
		if (!(((entity.getCapability(TcodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new TcodModVariables.PlayerVariables())).bao)
				.getItem() == Xiaobao2Item.block)) {
			{
				ItemStack _setval = new ItemStack(Xiaobao2Item.block);
				entity.getCapability(TcodModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.bao = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(TcodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new TcodModVariables.PlayerVariables())).opemn == 0) {
			if (!((new Object() {
				public ItemStack getItemStack(int sltid, Entity entity) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
					return _retval.get();
				}
			}.getItemStack((int) (8), entity)).getItem() == XiaobaoItem.block)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(XiaobaoItem.block);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 20,
							((PlayerEntity) entity).container.func_234641_j_());
				}
				{
					final ItemStack _setstack = new ItemStack(XiaobaoItem.block);
					final int _sltid = (int) (8);
					_setstack.setCount((int) 1);
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable) {
							((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
						}
					});
				}
			}
		}
		if ((entity.getCapability(TcodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new TcodModVariables.PlayerVariables())).opemn == 1) {
			if (!((new Object() {
				public ItemStack getItemStack(int sltid, Entity entity) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
					return _retval.get();
				}
			}.getItemStack((int) (8), entity)).getItem() == Xiaobao2Item.block)) {
				{
					final ItemStack _setstack = ((entity.getCapability(TcodModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new TcodModVariables.PlayerVariables())).bao);
					final int _sltid = (int) (8);
					_setstack.setCount((int) 1);
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable) {
							((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
						}
					});
				}
			}
		}
		aw = 9;
	}
}
