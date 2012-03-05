/**
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
 * @author JoJLlmAn
 *
 */

public class Vector3D extends Vector2D implements Cloneable, Serializable, Immutable
{
	private static final long serialVersionUID = 8493095902831171278L;
	

	private class ImmutablyVector3D extends Vector3D implements Immutably
	{
		private static final long serialVersionUID = Vector3D.serialVersionUID;
		
		private ImmutablyVector3D()
		{
			super( Vector3D.this.getX(), Vector3D.this.getY(), Vector3D.this.getZ() );
		}
	}
	
	
	private float z;
	
	
	public Vector3D()
	{

	}
	
	public Vector3D( float x, float y, float z )
	{
		super( x, y );
		this.z = z;
	}
	
	public float getZ()
	{
		return z;
	}
	
	public void setZ( float z )
	{
		if( this instanceof Immutably ) throw new ImmutablyException();
		this.z = z;
	}
	
	@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(49979693, 573259433, this, false);
	}

	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}
	
	@Override
	public Vector3D clone()
	{
		return (Vector3D) super.clone();
	}
	
	@Override
	public Vector3D immure()
	{
		return new ImmutablyVector3D();
	}
	
	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE, false);
	}
}
