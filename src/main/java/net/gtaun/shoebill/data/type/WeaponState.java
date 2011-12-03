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

package net.gtaun.shoebill.data.type;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MK124
 *
 */

public enum WeaponState
{
	UNKNOWN( -1 ),
	NO_BULLETS( 0 ),
	LAST_BULLET( 1 ),
	MORE_BULLETS( 2 ),
	RELOADING( 3 );
	
	
	private static Map<Integer, WeaponState> values = new HashMap<Integer, WeaponState>();
	public static WeaponState get( int data )		{ return values.get(data); }
	
	static
	{
		for( WeaponState state : values() ) values.put( state.data, state );
	}
	
	
	private int data;
	
	public int getData()	{ return data; }
	
	
	private WeaponState( int data )
	{
		this.data = data;
	}
}
