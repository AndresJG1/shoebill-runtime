/**
 * Copyright (C) 2011 JoJLlmAn
 * Copyright (C) 2011-2012 MK124
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.gtaun.shoebill.resource;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.gtaun.shoebill.ShoebillImpl;
import net.gtaun.shoebill.ShoebillLowLevel;
import net.gtaun.shoebill.events.plugin.PluginLoadEvent;
import net.gtaun.shoebill.events.plugin.PluginUnloadEvent;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @author JoJLlmAn, MK124
 */
public class ResourceManagerImpl implements ResourceManager
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceManagerImpl.class);
	
	
	private ShoebillImpl shoebill;
	private File dataDir;
	
	private Map<File, PluginDescription> descriptions;
	
	private Gamemode gamemode;
	
	private Map<Class<? extends Plugin>, Plugin> plugins;
	
	
	public ResourceManagerImpl(ShoebillImpl shoebill, File dataDir)
	{
		plugins = new HashMap<Class<? extends Plugin>, Plugin>();
		
		this.shoebill = shoebill;
		this.dataDir = dataDir;
		
		this.descriptions = generateDescriptions();
	}
	
	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
	private PluginDescription generateDescription(File file) throws ClassNotFoundException, IOException
	{
		PluginDescription desc = new PluginDescription(file, getClass().getClassLoader());
		return desc;
	}
	
	private Map<File, PluginDescription> generateDescriptions()
	{
		Map<File, PluginDescription> descriptions = new HashMap<>();
		List<File> files = shoebill.getArtifactLocator().getPluginFiles();
		
		for (File file : files)
		{
			try
			{
				PluginDescription desc = generateDescription(file);
				descriptions.put(file, desc);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return descriptions;
	}
	
	public void loadAllPlugin()
	{
		for (PluginDescription desc : descriptions.values())
		{
			loadPlugin(desc);
		}
	}
	
	public void unloadAllPlugin()
	{
		for (Plugin plugin : plugins.values())
		{
			unloadPlugin(plugin);
		}
	}
	
	@Override
	public Plugin loadPlugin(String coord)
	{
		File file = shoebill.getArtifactLocator().getPluginFile(coord);
		return loadPlugin(file);
	}
	
	@Override
	public Plugin loadPlugin(File file)
	{
		if (file.canRead() == false) return null;
		
		PluginDescription desc = descriptions.get(file);
		if (desc == null)
		{
			try
			{
				desc = new PluginDescription(file, getClass().getClassLoader());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		if (desc == null) return null;
		return loadPlugin(desc);
	}
	
	@Override
	public Plugin loadPlugin(PluginDescription desc)
	{
		try
		{
			LOGGER.info("Load plugin: " + desc.getName());
			Class<? extends Plugin> clazz = desc.getClazz();
			if (plugins.containsKey(clazz))
			{
				LOGGER.warn("There's a plugin which has the same class as \"" + desc.getClazz().getName() + "\".");
				LOGGER.warn("Abandon loading " + desc.getClazz().getName());
				return null;
			}
			
			Constructor<? extends Plugin> constructor = clazz.getConstructor();
			Plugin plugin = constructor.newInstance();
			
			File pluginDataDir = new File(dataDir, desc.getClazz().getName());
			if (!pluginDataDir.exists()) pluginDataDir.mkdirs();
			
			plugin.setContext(desc, shoebill, pluginDataDir);
			plugin.enable();
			
			plugins.put(clazz, plugin);
			
			PluginLoadEvent event = new PluginLoadEvent(plugin);
			ShoebillLowLevel shoebillLowLevel = (ShoebillLowLevel) shoebill;
			shoebillLowLevel.getEventManager().dispatchEvent(event, this);
			
			return plugin;
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void unloadPlugin(Plugin plugin)
	{
		for (Entry<Class<? extends Plugin>, Plugin> entry : plugins.entrySet())
		{
			if (entry.getValue() != plugin) continue;
			LOGGER.info("Unload plugin: " + plugin.getDescription().getClazz().getName());
			
			PluginUnloadEvent event = new PluginUnloadEvent(plugin);
			ShoebillLowLevel shoebillLowLevel = (ShoebillLowLevel) shoebill;
			shoebillLowLevel.getEventManager().dispatchEvent(event, this);
			
			try
			{
				plugin.disable();
			}
			catch (Throwable e)
			{
				e.printStackTrace();
			}
			
			plugins.remove(entry.getKey());
			return;
		}
	}
	
	@Override
	public <T extends Plugin> T getPlugin(Class<T> clz)
	{
		T plugin = clz.cast(plugins.get(clz));
		if (plugin != null) return plugin;
		
		for (Plugin p : plugins.values())
		{
			if (clz.isInstance(p)) return clz.cast(p);
		}
		
		return null;
	}
	
	@Override
	public Collection<Plugin> getPlugins()
	{
		return plugins.values();
	}

	@Override
	public Gamemode getGamemode()
	{
		return gamemode;
	}
	
	@Override
	public <T extends Gamemode> T getGamemode(Class<T> cls)
	{
		return cls.cast(gamemode);
	}
}
