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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import net.gtaun.shoebill.data.type.WeaponType;
import net.gtaun.shoebill.util.immutable.Immutable;
import net.gtaun.shoebill.util.immutable.Immutably;
import net.gtaun.shoebill.util.immutable.ImmutablyException;

/**
 * @author MK124
 *
 */

public class WeaponData implements Cloneable, Serializable, Immutable
{
	private static final long serialVersionUID = 8584508544432627380L;
	

	private class ImmutablyWeaponData extends WeaponData implements Immutably
	{
		private static final long serialVersionUID = WeaponData.serialVersionUID;
		
		private ImmutablyWeaponData()
		{
			super( WeaponData.this.getType(), WeaponData.this.getAmmo() );
		}
	}
	
	
	private WeaponType type;
	private int ammo;
	
	
	public WeaponData()
	{
		type = WeaponType.NONE;
		ammo = 0;
	}
	
	public WeaponData( WeaponType type, int ammo )
	{
		this.type = type;
		this.ammo = ammo;
	}
	
	public WeaponType getType()
	{
		return type;
	}
	
	public void setType( WeaponType type )
	{
		if( this instanceof Immutably ) throw new ImmutablyException();
		this.type = type;
	}
	
	public int getAmmo()
	{
		return ammo;
	}
	
	public void setAmmo( int ammo )
	{
		if( this instanceof Immutably ) throw new ImmutablyException();
		this.ammo = ammo;
	}
	
	@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(275604547, 817504253, this, false);
	}
	
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}
	
	@Override
	public WeaponData clone()
	{
		try
		{
			return (WeaponData) super.clone();
		}
		catch( CloneNotSupportedException e )
		{
			throw new InternalError();
		}
	}
	
	@Override
	public WeaponData immure()
	{
		return new ImmutablyWeaponData();
	}
	
	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE, false);
	}
}
