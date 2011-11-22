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

import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.util.event.IEventDispatcher;

/**
 * @author MK124
 *
 */

public interface ITextdraw extends IDestroyable
{
	IEventDispatcher getEventDispatcher();
	
	int getId();
	float getX();
	float getY();
	String getText();
	
	void setLetterSize( float x, float y );
	void setTextSize( float x, float y );
	void setAlignment( int alignment );
	void setColor( Color color );
	void setUseBox( boolean use );
	void setBoxColor( Color color );
	void setShadow( int size );
	void setOutline( int size );
	void setBackgroundColor( Color color );
	void setFont( int font );
	void setProportional( int set );
	void setText( String text );
	void show( IPlayer player );
	void hide( IPlayer player );
	void showForAll();
	void hideForAll();
}
