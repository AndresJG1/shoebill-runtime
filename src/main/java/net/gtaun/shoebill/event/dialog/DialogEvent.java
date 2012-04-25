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

package net.gtaun.shoebill.event.dialog;

import net.gtaun.shoebill.object.IDialog;
import net.gtaun.shoebill.object.IPlayer;
import net.gtaun.shoebill.util.event.Event;

/**
 * @author MK124
 *
 */

public abstract class DialogEvent extends Event
{
	private IDialog dialog;
	private IPlayer player;
	
	public IDialog getDialog()			{ return dialog; }
	public IPlayer getPlayer()			{ return player; }
	
	
	protected DialogEvent( IDialog dialog, IPlayer player )
	{
		this.dialog = dialog;
		this.player = player;
	}
}
