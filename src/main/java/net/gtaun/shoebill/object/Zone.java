/**
 * Copyright (C) 2011 MK124
 * Copyright (C) 2011 JoJLlmAn
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

package net.gtaun.shoebill.object;

import java.util.Vector;

import net.gtaun.lungfish.data.Area;
import net.gtaun.lungfish.data.Color;
import net.gtaun.lungfish.object.IPlayer;
import net.gtaun.lungfish.object.IZone;
import net.gtaun.shoebill.NativeFunction;

/**
 * @author MK124, JoJLlmAn
 *
 */

public class Zone implements IZone
{
	public static Vector<Zone> get()
	{
		return Gamemode.getInstances(Gamemode.instance.zonePool, Zone.class);
	}
	
	public static <T> Vector<T> get( Class<T> cls )
	{
		return Gamemode.getInstances(Gamemode.instance.zonePool, cls);
	}
	
	
	private boolean[] isPlayerShowed = new boolean[Gamemode.MAX_PLAYERS];
	private boolean[] isPlayerFlashing = new boolean[Gamemode.MAX_PLAYERS];
	
	
	int id;
	Area area;
	
	public int getId()				{ return id; }
	public Area getArea()			{ return area.clone(); }


	public Zone( float minX, float minY, float maxX, float maxY )
	{
		this.area = new Area( minX, minY, maxX, maxY );
		init();
	}
	
	public Zone( Area area )
	{
		this.area = area.clone();
		init();
	}
	
	private void init()
	{
		id = NativeFunction.gangZoneCreate( area.minX, area.minY, area.maxX, area.maxY );
		
		for(int i=0; i<Gamemode.MAX_PLAYERS; i++)
		{
			isPlayerShowed[i] = false;
			isPlayerFlashing[i] = false;
		}
		
		Gamemode.instance.zonePool[id] = this;
	}


//---------------------------------------------------------
	
	public void destroy()
	{
		NativeFunction.gangZoneDestroy( id );
		Gamemode.instance.zonePool[id] = null;
	}
	
	
	public void show( IPlayer p, int color )
	{
		Player player = (Player) p;
		
		NativeFunction.gangZoneShowForPlayer( player.id, id, color );
		isPlayerShowed[player.id] = true;
		isPlayerFlashing[player.id] = false;
	}
	
	public void hide( IPlayer p )
	{
		Player player = (Player) p;
		
		NativeFunction.gangZoneHideForPlayer( player.id, id );
		
		isPlayerShowed[player.id] = false;
		isPlayerFlashing[player.id] = false;
	}
	
	public void flash( IPlayer p, int color )
	{
		Player player = (Player) p;
		if( isPlayerShowed[player.id] ){
			NativeFunction.gangZoneFlashForPlayer( player.id, id, color );
			isPlayerFlashing[player.id] = true;
		}
	}
	
	public void stopFlash( IPlayer p )
	{
		Player player = (Player) p;
		
		NativeFunction.gangZoneStopFlashForPlayer( player.id, id );
		isPlayerFlashing[player.id] = false;
	}
	

	public void showForAll( Color color )
	{
		NativeFunction.gangZoneShowForAll( id, color.getValue() );
		for( int i=0; i<Gamemode.MAX_PLAYERS; i++ ) isPlayerShowed[i] = true;
	}
	
	public void hideForAll()
	{
		NativeFunction.gangZoneHideForAll( id );
		
		for( int i=0; i<Gamemode.MAX_PLAYERS; i++ )
		{
			isPlayerShowed[i] = false;
			isPlayerFlashing[i] = false;
		}
	}
	
	public void flashForAll( Color color )
	{
		NativeFunction.gangZoneFlashForAll( id, color.getValue() );
		for( int i=0; i<Gamemode.MAX_PLAYERS; i++ ) if( isPlayerShowed[i] ) isPlayerFlashing[i] = true;
	}

	public void stopFlashForAll()
	{
		NativeFunction.gangZoneStopFlashForAll( id );
		for( int i=0; i<Gamemode.MAX_PLAYERS; i++ ) isPlayerFlashing[i] = false;
	}
}
