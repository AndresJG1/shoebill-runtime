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

package net.gtaun.shoebill.object.impl;

import net.gtaun.shoebill.data.constant.VehicleComponentSlot;
import net.gtaun.shoebill.object.primitive.VehicleComponent;
import net.gtaun.shoebill.object.primitive.VehiclePrim;
import net.gtaun.shoebill.samp.SampNativeFunction;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * @author MK124, JoJLlmAn
 *
 */

public class VehicleComponentImpl implements VehicleComponent
{
	private VehiclePrim vehicle;
	private int[] components = new int[ VehicleComponentSlot.values().length ];
	
	
	VehicleComponentImpl( VehiclePrim vehicle )
	{
		this.vehicle = vehicle;
		update();
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
	@Override
	public VehiclePrim getVehicle()
	{
		return vehicle;
	}
	
	@Override
	public void add( int componentId )
	{
		if( vehicle.isDestroyed() ) return;
		
		SampNativeFunction.addVehicleComponent( vehicle.getId(), componentId );
		
		int slot = SampNativeFunction.getVehicleComponentType(componentId);
		components[slot] = SampNativeFunction.getVehicleComponentInSlot(vehicle.getId(), slot);
	}
	
	@Override
	public void remove( int componentId )
	{
		if( vehicle.isDestroyed() ) return;
		
		SampNativeFunction.removeVehicleComponent( vehicle.getId(), componentId );
		
		int slot = SampNativeFunction.getVehicleComponentType(componentId);
		components[slot] = SampNativeFunction.getVehicleComponentInSlot(vehicle.getId(), slot);
	}
	
	@Override
	public void remove( VehicleComponentSlot slot )
	{
		if( vehicle.isDestroyed() ) return;
		
		int componentId = components[ slot.getData() ];
		SampNativeFunction.removeVehicleComponent( vehicle.getId(), componentId );
	}
	
	@Override
	public int get( VehicleComponentSlot slot )
	{
		if( vehicle.isDestroyed() ) return 0;
		return SampNativeFunction.getVehicleComponentInSlot( vehicle.getId(), slot.getData() );
	}
	
	@Override
	public int[] toArray()
	{
		int[] data = new int[ components.length ];
		System.arraycopy( components, 0, data, 0, components.length );
		
		return data;
	}
	
		
	final void update()
	{
		for( int i=0; i<components.length; i++ )
		{
			components[i] = SampNativeFunction.getVehicleComponentInSlot(vehicle.getId(), i);
		}
	}
}