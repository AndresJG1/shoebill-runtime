/**
 * Copyright (C) 2011 MK124
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

package net.gtaun.shoebill.object.impl;

import net.gtaun.shoebill.SampObjectPool;
import net.gtaun.shoebill.Shoebill;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Location;
import net.gtaun.shoebill.data.Point3D;
import net.gtaun.shoebill.exception.CreationFailedException;
import net.gtaun.shoebill.object.primitive.LabelPrim;
import net.gtaun.shoebill.object.primitive.PlayerPrim;
import net.gtaun.shoebill.object.primitive.VehiclePrim;
import net.gtaun.shoebill.samp.SampNativeFunction;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author MK124
 *
 */

public class LabelImpl implements LabelPrim
{
	private int id = INVALID_ID;
	private String text;
	private Color color;
	private Location location;
	private float drawDistance;
	
	private Point3D offset;
	private PlayerPrim attachedPlayer;
	private VehiclePrim attachedVehicle;
	

	public LabelImpl( String text, Color color, float x, float y, float z, int worldId, float drawDistance, boolean testLOS ) throws CreationFailedException
	{
		initialize( text, color, new Location(x, y, z, worldId), drawDistance, testLOS );
	}
	
	public LabelImpl( String text, Color color, Point3D pos, int worldId, float drawDistance, boolean testLOS ) throws CreationFailedException
	{
		initialize( text, color, new Location(pos, worldId), drawDistance, testLOS );
	}
	
	public LabelImpl( String text, Color color, Location loc, float drawDistance, boolean testLOS ) throws CreationFailedException
	{
		initialize( text, color, new Location(loc), drawDistance, testLOS );
	}
	
	private void initialize( String text, Color color, Location loc, float drawDistance, boolean testLOS ) throws CreationFailedException
	{
		if( StringUtils.isEmpty(text) ) text = " ";
		
		this.text = text;
		this.color = new Color( color );
		this.location = loc;
		this.drawDistance = drawDistance;
		
		id = SampNativeFunction.create3DTextLabel( text, color.getValue(), location.getX(), location.getY(), location.getZ(), drawDistance, location.getWorldId(), testLOS );
		if( id == INVALID_ID ) throw new CreationFailedException();
		
		SampObjectPool pool = (SampObjectPool) Shoebill.getInstance().getManagedObjectPool();
		pool.setLabel( id, this );
	}
	
	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
	@Override
	public void destroy()
	{
		if( isDestroyed() ) return;
		
		SampNativeFunction.delete3DTextLabel( id );
		
		SampObjectPool pool = (SampObjectPool) Shoebill.getInstance().getManagedObjectPool();
		pool.setLabel( id, null );

		id = INVALID_ID;
	}
	
	@Override
	public boolean isDestroyed()
	{
		return id == INVALID_ID;
	}

	@Override
	public int getId()
	{
		return id;
	}
	
	@Override
	public String getText()
	{
		return text;
	}
	
	@Override
	public Color getColor()
	{
		return color.clone();
	}
	
	@Override
	public float getDrawDistance()
	{
		return drawDistance;
	}
	
	@Override
	public PlayerPrim getAttachedPlayer()
	{
		return attachedPlayer;
	}
	
	@Override
	public VehiclePrim getAttachedVehicle()
	{
		return attachedVehicle;
	}
	
	@Override
	public Location getLocation()
	{
		if( isDestroyed() ) return null;
		
		Location loc = null;
		
		if( attachedPlayer != null )	loc = attachedPlayer.getLocation();
		if( attachedVehicle != null )	loc = attachedVehicle.getLocation();
		
		if( loc != null )
		{
			location.set( loc.getX() + offset.getX(), loc.getY() + offset.getY(), loc.getZ() + offset.getZ(), loc.getInteriorId(), loc.getWorldId() );
		}
		
		return location.clone();
	}

	@Override
	public void attach( PlayerPrim player, float x, float y, float z )
	{
		if( isDestroyed() ) return;
		if( player.isOnline() == false ) return;
		
		offset = new Point3D( x, y, z );
		
		SampNativeFunction.attach3DTextLabelToPlayer( id, player.getId(), x, y, z );
		attachedPlayer = player;
		attachedVehicle = null;
	}
	
	@Override
	public void attach( PlayerPrim player, Point3D offset )
	{
		attach( player, offset.getX(), offset.getY(), offset.getZ() );
	}

	@Override
	public void attach( VehiclePrim vehicle, float x, float y, float z )
	{
		if( isDestroyed() ) return;
		if( vehicle.isDestroyed() ) return;

		offset = new Point3D( x, y, z );
		
		SampNativeFunction.attach3DTextLabelToVehicle( id, vehicle.getId(), x, y, z );
		attachedPlayer = null;
		attachedVehicle = vehicle;
	}

	@Override
	public void attach( VehiclePrim vehicle, Point3D offset )
	{
		attach( vehicle, offset.getX(), offset.getY(), offset.getZ() );
	}

	@Override
	public void update( Color color, String text )
	{
		if( isDestroyed() ) return;
		if( text == null ) throw new NullPointerException();
		
		this.color.set( color );
		this.text = text;
		
		SampNativeFunction.update3DTextLabelText( id, color.getValue(), text );
	}
}