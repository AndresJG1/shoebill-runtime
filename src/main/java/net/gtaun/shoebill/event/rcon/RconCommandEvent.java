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

package net.gtaun.shoebill.event.rcon;

import net.gtaun.shoebill.util.event.Event;

/**
 * @author MK124
 *
 */

public class RconCommandEvent extends Event
{
	String command;
	
	public String getCommand()		{ return command; }
	
	
	public RconCommandEvent( String command, int result )
	{
		this.command = command;
		
		this.result = result;
	}
	
	@Override
	public void setResult( int result )
	{
		if( result != 0 ) result = 1;
	}
}
