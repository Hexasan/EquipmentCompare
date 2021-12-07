package com.anthonyhilyard.equipmentcompare;

import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;


public class EquipmentCompare
{
	@SuppressWarnings("unused")
	public static final Logger LOGGER = LogManager.getLogger();
	public static boolean tooltipActive = false;
	private static final KeyMapping showComparisonTooltip = new KeyMapping("Show comparison tooltip", KeyConflictContext.GUI, InputConstants.Type.KEYSYM.getOrCreate(GLFW.GLFW_KEY_LEFT_SHIFT), "key.categories.inventory");

	public void onClientSetup(FMLClientSetupEvent event)
	{
		ClientRegistry.registerKeyBinding(showComparisonTooltip);
	}

	@SubscribeEvent
	public static void onKeyInput(KeyInputEvent event)
	{
		// For some reason GUI conflict context does not seem to work properly for modifier keys?
		// In any case, this lower level approach works...
		if (showComparisonTooltip.matches(event.getKey(), 0))
		{
			if (event.getAction() == GLFW.GLFW_PRESS)
			{
				tooltipActive = true;
			}
			else if (event.getAction() == GLFW.GLFW_RELEASE)
			{
				tooltipActive = false;
			}
		}
	}
}
