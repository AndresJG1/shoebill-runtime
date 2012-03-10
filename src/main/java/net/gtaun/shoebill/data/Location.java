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

package net.gtaun.shoebill.data;

import java.io.Serializable;

import net.gtaun.shoebill.util.immutable.Immutable;
import net.gtaun.shoebill.util.immutable.Immutably;
import net.gtaun.shoebill.util.immutable.ImmutablyException;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author MK124
 *
 */

public class Location extends Vector3D implements Cloneable, Serializable, Immutable
{
	private static final long serialVersionUID = 8895946392500802993L;

	
	private class ImmutableLocation extends Location implements Immutably
	{
		private static final long serialVersionUID = Location.serialVersionUID;
		
		private ImmutableLocation()
		{
			super( Location.this );
		}
		
		@Override
		public Location clone()
		{
			return new Location( this );
		}
	}
	
	
	private int interiorId, worldId;
	

	public Location()
	{

	}
	
	public Location( float x, float y, float z )
	{
		super( x, y, z );
	}
	
	public Location( float x, float y, float z, int worldId )
	{
		super( x, y, z );
		this.worldId = worldId;
	}
	
	public Location( float x, float y, float z, int interiorId, int worldId )
	{
		super( x, y, z );
		this.interiorId = interiorId;
		this.worldId = worldId;
	}

	public Location( Vector3D pos )
	{
		super( pos );
	}
	
	public Location( Vector3D pos, int worldId )
	{
		super( pos );
		this.worldId = worldId;
	}

	public Location( Vector3D pos, int interiorId, int worldId )
	{
		super( pos );
		this.interiorId = interiorId;
		this.worldId = worldId;
	}
	
	public Location( Location loc )
	{
		super( loc.getX(), loc.getY(), loc.getZ() );
		this.interiorId = loc.getInteriorId();
		this.worldId = loc.getWorldId();
	}
	
	public int getInteriorId()
	{
		return interiorId;
	}

	public void setInteriorId( int interiorId )
	{
		if( this instanceof Immutably ) throw new ImmutablyException();
		this.interiorId = interiorId;
	}

	public int getWorldId()
	{
		return worldId;
	}

	public void setWorldId( int worldId )
	{
		if( this instanceof Immutably ) throw new ImmutablyException();
		
		this.worldId = worldId;
	}

	public void set( float x, float y, float z )
	{
		if( this instanceof Immutably ) throw new ImmutablyException();

		super.set( x, y, z );
	}
	
	public void set( float x, float y, float z, int worldId )
	{
		if( this instanceof Immutably ) throw new ImmutablyException();
		
		super.set( x, y, z );
		setWorldId( worldId );
	}
	
	public void set( float x, float y, float z, int interiorId, int worldId )
	{
		if( this instanceof Immutably ) throw new ImmutablyException();
		
		super.set( x, y, z );
		setInteriorId( interiorId );
		setWorldId( worldId );
	}

	public void set( Vector3D pos, int worldId )
	{
		if( this instanceof Immutably ) throw new ImmutablyException();
		
		super.set( pos );
		setWorldId( worldId );
	}

	public void set( Vector3D pos, int interiorId, int worldId )
	{
		if( this instanceof Immutably ) throw new ImmutablyException();
		
		super.set( pos );
		setInteriorId( interiorId );
		setWorldId( worldId );
	}
	
	public void set( Location loc )
	{
		if( this instanceof Immutably ) throw new ImmutablyException();
		
		super.set( loc );
		setInteriorId( loc.getInteriorId() );
		setWorldId( loc.getWorldId() );
	}
	
	@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(198491329, 217645177, this, false);
	}
	
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}

	@Override
	public Location clone()
	{
		return (Location) super.clone();
	}
	
	@Override
	public Location immure()
	{
		return new ImmutableLocation();
	}
	
	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE, false);
	}
}
