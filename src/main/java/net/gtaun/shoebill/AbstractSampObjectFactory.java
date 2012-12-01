/**
 * Copyright (C) 2012 MK124
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

package net.gtaun.shoebill;

import net.gtaun.shoebill.data.Area;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Location;
import net.gtaun.shoebill.data.LocationAngle;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.data.Vector3D;
import net.gtaun.shoebill.exception.CreationFailedException;
import net.gtaun.shoebill.object.Label;
import net.gtaun.shoebill.object.Menu;
import net.gtaun.shoebill.object.Pickup;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.PlayerLabel;
import net.gtaun.shoebill.object.PlayerObject;
import net.gtaun.shoebill.object.PlayerTextdraw;
import net.gtaun.shoebill.object.SampObject;
import net.gtaun.shoebill.object.Textdraw;
import net.gtaun.shoebill.object.Timer;
import net.gtaun.shoebill.object.Vehicle;
import net.gtaun.shoebill.object.Zone;

/**
 * 
 * 
 * @author MK124
 */
public abstract class AbstractSampObjectFactory implements SampObjectFactory
{
	public AbstractSampObjectFactory()
	{
		
	}
	
	@Override
	public Vehicle createVehicle(int modelId, float x, float y, float z, int interiorId, int worldId, float angle, int color1, int color2, int respawnDelay) throws CreationFailedException
	{
		return createVehicle(modelId, new LocationAngle(x, y, z, interiorId, worldId, angle), color1, color2, respawnDelay);
	}
	
	@Override
	public Vehicle createVehicle(int modelId, float x, float y, float z, float angle, int color1, int color2, int respawnDelay) throws CreationFailedException
	{
		return createVehicle(modelId, new LocationAngle(x, y, z, 0, 0, angle), color1, color2, respawnDelay);
	}
	
	@Override
	public Vehicle createVehicle(int modelId, Vector3D pos, float angle, int color1, int color2, int respawnDelay) throws CreationFailedException
	{
		return createVehicle(modelId, new LocationAngle(pos, 0, 0, angle), color1, color2, respawnDelay);
	}
	
	@Override
	public Vehicle createVehicle(int modelId, Vector3D pos, int interiorId, int worldId, float angle, int color1, int color2, int respawnDelay) throws CreationFailedException
	{
		return createVehicle(modelId, new LocationAngle(pos, interiorId, worldId, angle), color1, color2, respawnDelay);
	}
	
	@Override
	public Vehicle createVehicle(int modelId, Location loc, float angle, int color1, int color2, int respawnDelay) throws CreationFailedException
	{
		return createVehicle(modelId, new LocationAngle(loc, angle), color1, color2, respawnDelay);
	}
	
	@Override
	public SampObject createObject(int modelId, float x, float y, float z, float rx, float ry, float rz) throws CreationFailedException
	{
		return createObject(modelId, new Location(x, y, z), new Vector3D(rx, ry, rz), 0);
	}
	
	@Override
	public SampObject createObject(int modelId, float x, float y, float z, float rx, float ry, float rz, float drawDistance) throws CreationFailedException
	{
		return createObject(modelId, new Location(x, y, z), new Vector3D(rx, ry, rz), drawDistance);
	}
	
	@Override
	public SampObject createObject(int modelId, Location loc, float rx, float ry, float rz) throws CreationFailedException
	{
		return createObject(modelId, loc, new Vector3D(rx, ry, rz), 0);
	}
	
	@Override
	public SampObject createObject(int modelId, Location loc, float rx, float ry, float rz, float drawDistance) throws CreationFailedException
	{
		return createObject(modelId, loc, new Vector3D(rx, ry, rz), drawDistance);
	}
	
	@Override
	public SampObject createObject(int modelId, Location loc, Vector3D rot) throws CreationFailedException
	{
		return createObject(modelId, loc, rot, 0);
	}
	
	@Override
	public PlayerObject createPlayerObject(Player player, int modelId, float x, float y, float z, float rx, float ry, float rz) throws CreationFailedException
	{
		return createPlayerObject(player, modelId, new Location(x, y, z), new Vector3D(rx, ry, rz), 0);
	}
	
	@Override
	public PlayerObject createPlayerObject(Player player, int modelId, float x, float y, float z, float rx, float ry, float rz, float drawDistance) throws CreationFailedException
	{
		return createPlayerObject(player, modelId, new Location(x, y, z), new Vector3D(rx, ry, rz), drawDistance);
	}
	
	@Override
	public PlayerObject createPlayerObject(Player player, int modelId, Location loc, float rx, float ry, float rz) throws CreationFailedException
	{
		return createPlayerObject(player, modelId, loc, new Vector3D(rx, ry, rz), 0);
	}
	
	@Override
	public PlayerObject createPlayerObject(Player player, int modelId, Location loc, float rx, float ry, float rz, float drawDistance) throws CreationFailedException
	{
		return createPlayerObject(player, modelId, loc, new Vector3D(rx, ry, rz), drawDistance);
	}
	
	@Override
	public PlayerObject createPlayerObject(Player player, int modelId, Location loc, Vector3D rot) throws CreationFailedException
	{
		return createPlayerObject(player, modelId, loc, rot, 0);
	}
	
	@Override
	public Pickup createPickup(int modelId, int type, float x, float y, float z, int worldId) throws CreationFailedException
	{
		return createPickup(modelId, type, new Location(x, y, z, worldId));
	}
	
	@Override
	public Pickup createPickup(int modelId, int type, float x, float y, float z) throws CreationFailedException
	{
		return createPickup(modelId, type, new Location(x, y, z));
	}
	
	@Override
	public Label createLabel(String text, Color color, float x, float y, float z, int worldId, float drawDistance, boolean testLOS) throws CreationFailedException
	{
		return createLabel(text, color, new Location(x, y, z, worldId), drawDistance, testLOS);
	}
	
	@Override
	public Label createLabel(String text, Color color, Vector3D pos, int worldId, float drawDistance, boolean testLOS) throws CreationFailedException
	{
		return createLabel(text, color, new Location(pos, worldId), drawDistance, testLOS);
	}
	
	@Override
	public PlayerLabel createPlayerLabel(Player player, String text, Color color, float x, float y, float z, float drawDistance, boolean testLOS)
	{
		return createPlayerLabel(player, text, color, new Location(x, y, z), drawDistance, testLOS);
	}
	
	@Override
	public PlayerLabel createPlayerLabel(Player player, String text, Color color, float x, float y, float z, int worldId, float drawDistance, boolean testLOS)
	{
		return createPlayerLabel(player, text, color, new Location(x, y, z, worldId), drawDistance, testLOS);
	}
	
	@Override
	public Textdraw createTextdraw(float x, float y)
	{
		return createTextdraw(x, y, " ");
	}
	
	@Override
	public Textdraw createTextdraw(Vector2D pos)
	{
		return createTextdraw(pos.getX(), pos.getY(), " ");
	}
	
	@Override
	public Textdraw createTextdraw(Vector2D pos, String text)
	{
		return createTextdraw(pos.getX(), pos.getY(), text);
	}
	
	@Override
	public PlayerTextdraw createPlayerTextdraw(Player player, float x, float y)
	{
		return createPlayerTextdraw(player, x, y, " ");
	}
	
	@Override
	public PlayerTextdraw createPlayerTextdraw(Player player, Vector2D pos)
	{
		return createPlayerTextdraw(player, pos.getX(), pos.getY(), " ");
	}
	
	@Override
	public PlayerTextdraw createPlayerTextdraw(Player player, Vector2D pos, String text)
	{
		return createPlayerTextdraw(player, pos.getX(), pos.getY(), text);
	}
	
	@Override
	public Zone createZone(Area area)
	{
		return createZone(area.getMinX(), area.getMinY(), area.getMaxX(), area.getMaxY());
	}
	
	@Override
	public Menu createMenu(String title, int columns, Vector2D pos, float col1Width, float col2Width)
	{
		return createMenu(title, columns, pos.getX(), pos.getY(), col1Width, col2Width);
	}
	
	@Override
	public Timer createTimer(int interval)
	{
		return createTimer(interval, Timer.COUNT_INFINITE);
	}
}
