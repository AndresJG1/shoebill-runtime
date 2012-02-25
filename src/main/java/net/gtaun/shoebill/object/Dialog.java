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

package net.gtaun.shoebill.object;

import net.gtaun.shoebill.SampObjectPool;
import net.gtaun.shoebill.Shoebill;
import net.gtaun.shoebill.data.type.DialogStyle;

/**
 * @author MK124
 *
 */

public class Dialog implements IDialog
{
	private static int count = 0;


	private int id;


	@Override public int getId()									{ return id; }
	
	
	public Dialog()
	{
		initialize();
	}
	
	private void initialize()
	{
		id = count;
		count++;
		
		SampObjectPool pool = (SampObjectPool) Shoebill.getInstance().getManagedObjectPool();
		pool.putDialog( id, this );
	}
	
	
	@Override
	public void show( IPlayer player, DialogStyle style, String caption, String text, String button1, String button2 )
	{
		player.showDialog( this, style, caption, text, button1, button2 );
	}
	
	@Override
	public void cancel( IPlayer player )
	{
		if( player.getDialog() != this ) return;
		player.cancelDialog();
	}
}
