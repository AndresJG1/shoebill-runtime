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

import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Location;
import net.gtaun.shoebill.data.LocationAngle;
import net.gtaun.shoebill.data.Vector3D;
import net.gtaun.shoebill.exception.CreationFailedException;
import net.gtaun.shoebill.object.Dialog;
import net.gtaun.shoebill.object.Label;
import net.gtaun.shoebill.object.Menu;
import net.gtaun.shoebill.object.Pickup;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.PlayerLabel;
import net.gtaun.shoebill.object.PlayerObject;
import net.gtaun.shoebill.object.SampObject;
import net.gtaun.shoebill.object.Server;
import net.gtaun.shoebill.object.Textdraw;
import net.gtaun.shoebill.object.Timer;
import net.gtaun.shoebill.object.Vehicle;
import net.gtaun.shoebill.object.World;
import net.gtaun.shoebill.object.Zone;
import net.gtaun.shoebill.object.impl.DialogImpl;
import net.gtaun.shoebill.object.impl.LabelImpl;
import net.gtaun.shoebill.object.impl.MenuImpl;
import net.gtaun.shoebill.object.impl.ObjectImpl;
import net.gtaun.shoebill.object.impl.PickupImpl;
import net.gtaun.shoebill.object.impl.PlayerImpl;
import net.gtaun.shoebill.object.impl.PlayerLabelImpl;
import net.gtaun.shoebill.object.impl.PlayerObjectImpl;
import net.gtaun.shoebill.object.impl.ServerImpl;
import net.gtaun.shoebill.object.impl.TextdrawImpl;
import net.gtaun.shoebill.object.impl.TimerImpl;
import net.gtaun.shoebill.object.impl.VehicleImpl;
import net.gtaun.shoebill.object.impl.WorldImpl;
import net.gtaun.shoebill.object.impl.ZoneImpl;
import net.gtaun.shoebill.proxy.ProxyManagerImpl;
import net.sf.cglib.proxy.Enhancer;

/**
 * 
 * 
 * @author MK124
 */
public class SampObjectFactoryImpl extends AbstractSampObjectFactory
{
	private static final Class<?>[] PLAYER_CONSTRUCTOR_ARGUMENT_TYPES = {int.class};
	private static final Class<?>[] VEHICLE_CONSTRUCTOR_ARGUMENT_TYPES = {int.class, LocationAngle.class, int.class, int.class, int.class};
	private static final Class<?>[] OBJECT_CONSTRUCTOR_ARGUMENT_TYPES = {int.class, Location.class, Vector3D.class, float.class};
	private static final Class<?>[] PLAYER_OBJECT_CONSTRUCTOR_ARGUMENT_TYPES = {Player.class, int.class, Location.class, Vector3D.class, float.class};
	private static final Class<?>[] PLAYER_OBJECT_CONSTRUCTOR_ATTACHED_PLAYER_ARGUMENT_TYPES = {Player.class, int.class, Location.class, Vector3D.class, float.class, Player.class};
	private static final Class<?>[] PLAYER_OBJECT_CONSTRUCTOR_ATTACHED_VEHICLE_ARGUMENT_TYPES = {Player.class, int.class, Location.class, Vector3D.class, float.class, Vehicle.class};
	private static final Class<?>[] PICKUP_CONSTRUCTOR_ARGUMENT_TYPES = {int.class, int.class, Location.class};
	private static final Class<?>[] LABEL_CONSTRUCTOR_ARGUMENT_TYPES = {String.class, Color.class, Location.class, float.class, boolean.class};
	private static final Class<?>[] PLAYER_LABEL_CONSTRUCTOR_ARGUMENT_TYPES = {Player.class, String.class, Color.class, Location.class, float.class, boolean.class};
	private static final Class<?>[] TEXTDRAW_CONSTRUCTOR_ARGUMENT_TYPES = {float.class, float.class, String.class};
	private static final Class<?>[] ZONE_CONSTRUCTOR_ARGUMENT_TYPES = {float.class, float.class, float.class, float.class};
	private static final Class<?>[] MENU_CONSTRUCTOR_ARGUMENT_TYPES = {String.class, int.class, float.class, float.class, float.class, float.class};
	private static final Class<?>[] TIMER_CONSTRUCTOR_ARGUMENT_TYPES = {int.class, int.class};
	
	
	private final SampObjectPoolImpl pool;

	private Enhancer worldFactory;
	private Enhancer serverFactory;
	private Enhancer playerFactory;
	private Enhancer vehicleFactory;
	private Enhancer objectFactory;
	private Enhancer playerObjectFactory;
	private Enhancer pickupFactory;
	private Enhancer labelFactory;
	private Enhancer playerLabelFactory;
	private Enhancer textdrawFactory;
	private Enhancer zoneFactory;
	private Enhancer menuFactory;
	private Enhancer dialogFactory;
	private Enhancer timerFactory;
	
	
	public SampObjectFactoryImpl(SampObjectPoolImpl pool)
	{
		this.pool = pool;
		initialize();
	}
	
	private void initialize()
	{
		worldFactory =			ProxyManagerImpl.createProxyableFactory(WorldImpl.class);
		serverFactory =			ProxyManagerImpl.createProxyableFactory(ServerImpl.class);
		playerFactory =			ProxyManagerImpl.createProxyableFactory(PlayerImpl.class);
		vehicleFactory =		ProxyManagerImpl.createProxyableFactory(VehicleImpl.class);
		objectFactory =			ProxyManagerImpl.createProxyableFactory(ObjectImpl.class);
		playerObjectFactory =	ProxyManagerImpl.createProxyableFactory(PlayerObjectImpl.class);
		pickupFactory =			ProxyManagerImpl.createProxyableFactory(PickupImpl.class);
		labelFactory =			ProxyManagerImpl.createProxyableFactory(LabelImpl.class);
		playerLabelFactory =	ProxyManagerImpl.createProxyableFactory(PlayerLabelImpl.class);
		textdrawFactory =		ProxyManagerImpl.createProxyableFactory(TextdrawImpl.class);
		zoneFactory =			ProxyManagerImpl.createProxyableFactory(ZoneImpl.class);
		menuFactory =			ProxyManagerImpl.createProxyableFactory(MenuImpl.class);
		dialogFactory =			ProxyManagerImpl.createProxyableFactory(DialogImpl.class);
		timerFactory =			ProxyManagerImpl.createProxyableFactory(TimerImpl.class);
	}

	public World createWorld()
	{
		World world = (World) worldFactory.create();
		pool.setWorld(world);
		return world;
	}

	public Server createServer()
	{
		Server server = (Server) serverFactory.create();
		pool.setServer(server);
		return server;
	}
	
	public Player createPlayer(int playerId)
	{
		final Object[] args = {playerId};
		Player player = (Player) playerFactory.create(PLAYER_CONSTRUCTOR_ARGUMENT_TYPES, args);
		pool.setPlayer(playerId, player);
		return player;
	}

	@Override
	public Vehicle createVehicle(int modelId, LocationAngle loc, int color1, int color2, int respawnDelay) throws CreationFailedException
	{
		final Object[] args = {modelId, loc, color1, color2, respawnDelay};
		Vehicle vehicle = (Vehicle) vehicleFactory.create(VEHICLE_CONSTRUCTOR_ARGUMENT_TYPES, args);
		pool.setVehicle(vehicle.getId(), vehicle);
		return vehicle;
	}

	@Override
	public SampObject createObject(int modelId, Location loc, Vector3D rot, float drawDistance) throws CreationFailedException
	{
		final Object[] args = {modelId, loc, rot, drawDistance};
		SampObject object = (SampObject) objectFactory.create(OBJECT_CONSTRUCTOR_ARGUMENT_TYPES, args);
		pool.setObject(object.getId(), object);
		return object;
	}

	@Override
	public PlayerObject createPlayerObject(Player player, int modelId, Location loc, Vector3D rot, float drawDistance) throws CreationFailedException
	{
		final Object[] args = {player, modelId, loc, rot, drawDistance};
		PlayerObject object = (PlayerObject) playerObjectFactory.create(PLAYER_OBJECT_CONSTRUCTOR_ARGUMENT_TYPES, args);
		pool.setPlayerObject(player, object.getId(), object);
		return object;
	}

	@Override
	public Pickup createPickup(int modelId, int type, Location loc) throws CreationFailedException
	{
		final Object[] args = {modelId, type, loc};
		Pickup pickup = (Pickup) pickupFactory.create(PICKUP_CONSTRUCTOR_ARGUMENT_TYPES, args);
		pool.setPickup(pickup.getId(), pickup);
		return pickup;
	}

	@Override
	public Label createLabel(String text, Color color, Location loc, float drawDistance, boolean testLOS) throws CreationFailedException
	{
		final Object[] args = {text, color, loc, drawDistance, testLOS};
		Label label = (Label) labelFactory.create(LABEL_CONSTRUCTOR_ARGUMENT_TYPES, args);
		pool.setLabel(label.getId(), label);
		return label;
	}

	@Override
	public PlayerLabel createPlayerLabel(Player player, String text, Color color, Location loc, float drawDistance, boolean testLOS)
	{
		final Object[] args = {player, text, color, loc, drawDistance, testLOS};
		PlayerLabel label = (PlayerLabel) playerLabelFactory.create(PLAYER_LABEL_CONSTRUCTOR_ARGUMENT_TYPES, args);
		pool.setLabel(label.getId(), label);
		return label;
	}

	@Override
	public PlayerLabel createPlayerLabel(Player player, String text, Color color, Location loc, float drawDistance, boolean testLOS, Player attachedPlayer)
	{
		final Object[] args = {player, text, color, loc, drawDistance, testLOS, attachedPlayer};
		PlayerLabel label = (PlayerLabel) playerLabelFactory.create(PLAYER_OBJECT_CONSTRUCTOR_ATTACHED_PLAYER_ARGUMENT_TYPES, args);
		pool.setLabel(label.getId(), label);
		return label;
	}

	@Override
	public PlayerLabel createPlayerLabel(Player player, String text, Color color, Location loc, float drawDistance, boolean testLOS, Vehicle attachedVehicle)
	{
		final Object[] args = {player, text, color, loc, drawDistance, testLOS, attachedVehicle};
		PlayerLabel label = (PlayerLabel) playerLabelFactory.create(PLAYER_OBJECT_CONSTRUCTOR_ATTACHED_VEHICLE_ARGUMENT_TYPES, args);
		pool.setLabel(label.getId(), label);
		return label;
	}

	@Override
	public Textdraw createTextdraw(float x, float y, String text)
	{
		final Object[] args = {x, y, text};
		Textdraw textdraw = (Textdraw) textdrawFactory.create(TEXTDRAW_CONSTRUCTOR_ARGUMENT_TYPES, args);
		pool.setTextdraw(textdraw.getId(), textdraw);
		return textdraw;
	}

	@Override
	public Zone createZone(float minX, float minY, float maxX, float maxY)
	{
		final Object[] args = {minX, minY, maxX, maxY};
		Zone zone = (Zone) zoneFactory.create(ZONE_CONSTRUCTOR_ARGUMENT_TYPES, args);
		pool.setZone(zone.getId(), zone);
		return zone;
	}

	@Override
	public Menu createMenu(String title, int columns, float x, float y, float col1Width, float col2Width)
	{
		final Object[] args = {title, columns, x, y, col1Width, col2Width};
		Menu menu = (Menu) menuFactory.create(MENU_CONSTRUCTOR_ARGUMENT_TYPES, args);
		pool.setMenu(menu.getId(), menu);
		return menu;
	}

	@Override
	public Dialog createDialog()
	{
		Dialog dialog = (Dialog) dialogFactory.create();
		pool.putDialog(dialog.getId(), dialog);
		return dialog;
	}

	@Override
	public Timer createTimer(int interval, int count)
	{
		final Object[] args = {interval, count};
		Timer timer = (Timer) timerFactory.create(TIMER_CONSTRUCTOR_ARGUMENT_TYPES, args);
		pool.putTimer(timer);
		return timer;
	}
}