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

public enum ShopName
{
	PIZZASTACK		( "FDPIZA" ),
	BURGERSHOT		( "FDBURG" ),
	CLUCKINBELL		( "FDCHICK" ),
	AMMUNATION1		( "AMMUN1" ),
	AMMUNATION2		( "AMMUN2" ),
	AMMUNATION3		( "AMMUN3" ),
	AMMUNATION5		( "AMMUN5" );
	
	
	private static final Map<String, ShopName> VALUES = new HashMap<String, ShopName>();
	public static ShopName get( String data )
	{
		return VALUES.get(data);
	}
	
	static
	{
		for( ShopName shopName : values() ) VALUES.put( shopName.data, shopName );
	}
	
	
	private final String data;
	
	
	private ShopName( String data )
	{
		this.data = data;
	}
	
	public String getData()
	{
		return data;
	}
}
