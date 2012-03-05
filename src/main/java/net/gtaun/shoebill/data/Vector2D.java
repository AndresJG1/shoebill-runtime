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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author JoJLlmAn
 *
 */

public class Vector2D implements Cloneable, Serializable
{
	private static final long serialVersionUID = 3303330394405245831L;
	
	
	public float x, y;
	
	
	public Vector2D()
	{
		
	}
	
	public Vector2D( float x, float y )
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(433024253, 472882049, this, false);
	}
	
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}
	
	@Override
	public Vector2D clone()
	{
		try
		{
			return (Vector2D) super.clone();
		}
		catch( CloneNotSupportedException e )
		{
			throw new InternalError();
		}
	}
	
	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE, false);
	}
}
