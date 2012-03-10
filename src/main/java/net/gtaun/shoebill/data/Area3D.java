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

public class Area3D extends Area implements Cloneable, Serializable, Immutable
{
	private static final long serialVersionUID = 7421659231232420433L;
	
	
	private class ImmutablyArea3D extends Area3D implements Immutably
	{
		private static final long serialVersionUID = Area3D.serialVersionUID;

		private ImmutablyArea3D()
		{
			super( Area3D.this );
		}
		
		@Override
		public Area3D clone()
		{
			return new Area3D( this );
		}
	}
	
	
	private float minZ, maxZ;
	
	
	public Area3D( float minX, float minY, float minZ, float maxX, float maxY, float maxZ )
	{
		super( minX, minY, maxX, maxY );
		this.minZ = minZ;
		this.maxZ = maxZ;
	}

	public Area3D( Area area, float minZ, float maxZ )
	{
		super( area );
		this.minZ = minZ;
		this.maxZ = maxZ;
	}
	
	public Area3D( Area3D area )
	{
		super( area );
		this.minZ = area.getMinZ();
		this.maxZ = area.getMaxZ();
	}
	
	public float getMinZ()
	{
		return minZ;
	}

	public void setMinZ( float minZ )
	{
		if( this instanceof Immutably ) throw new ImmutablyException();
		
		this.minZ = minZ;
	}

	public float getMaxZ()
	{
		return maxZ;
	}

	public void setMaxZ( float maxZ )
	{
		if( this instanceof Immutably ) throw new ImmutablyException();
		
		this.maxZ = maxZ;
	}

	public void set( float minX, float minY, float minZ, float maxX, float maxY, float maxZ )
	{
		if( this instanceof Immutably ) throw new ImmutablyException();
		
		setMinX( minX );
		setMinY( minY );
		setMinZ( minZ );
		setMaxX( maxX );
		setMaxY( maxY );
		setMaxZ( maxZ );
	}
	
	public void set( Area area, float minZ, float maxZ )
	{
		if( this instanceof Immutably ) throw new ImmutablyException();
		
		super.set( area );

		setMinZ( minZ );
		setMaxZ( maxZ );
	}
	
	public void set( Area3D area )
	{
		if( this instanceof Immutably ) throw new ImmutablyException();
		
		setMinX( area.getMinX() );
		setMinY( area.getMinY() );
		setMinZ( area.getMinZ() );
		setMaxX( area.getMaxX() );
		setMaxY( area.getMaxY() );
		setMaxZ( area.getMaxZ() );
	}

	public float volume()
	{
		return (getMaxX()-getMinX()) * (getMaxY()-getMaxY()) * (getMaxZ()-getMinZ());
	}
	
	@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(256203221, 275604541, this, false);
	}
	
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}

	@Override
	public Area3D clone()
	{
		return (Area3D) super.clone();
	}
	
	@Override
	public Area3D immure()
	{
		return new ImmutablyArea3D();
	}
	
	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE, false);
	}
}
