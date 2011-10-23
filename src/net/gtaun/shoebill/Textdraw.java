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

package net.gtaun.shoebill;

import java.util.Vector;


/**
 * @author MK124, JoJLlmAn
 *
 */

public class Textdraw
{
	public static Vector<Textdraw> get()
	{
		return Gamemode.getInstances(Gamemode.instance.textdrawPool, Textdraw.class);
	}
	
	public static <T> Vector<T> get( Class<T> cls )
	{
		return Gamemode.getInstances(Gamemode.instance.textdrawPool, cls);
	}
	
	private boolean[] isPlayerShowed = new boolean[Gamemode.MAX_PLAYERS];
	
	
	int id;
	float x, y;
	String text;
	
	public int id()			{ return id; }
	public float x()		{ return x; }
	public float y()		{ return y; }
	public String text()	{ return text; }
	
	
	public Textdraw( float x, float y, String text )
	{
		if( text == null ) throw new NullPointerException();
		
		this.x = x;
		this.y = y;
		this.text = text;
		
		init();
	}
	
	public Textdraw( float x, float y )
	{
		this.x = x;
		this.y = y;
		this.text = "";
		
		init();
	}
	
	private void init()
	{
		id = NativeFunction.textDrawCreate( x, y, text );
		for( int i=0; i<Gamemode.MAX_PLAYERS; i++ ) isPlayerShowed[i] = false;
		
		Gamemode.instance.textdrawPool[id] = this;
	}
	
	
//---------------------------------------------------------
	
	public void destroy()
	{
		NativeFunction.textDrawDestroy( id );
		Gamemode.instance.textdrawPool[id] = null;
	}
	
	public void setLetterSize( float x, float y )
	{
		NativeFunction.textDrawLetterSize( id, x, y );
	}
	
	public void setTextSize( float x, float y )
	{
		NativeFunction.textDrawTextSize( id, x, y );
	}
	
	public void setAlignment( int alignment )
	{
		NativeFunction.textDrawAlignment( id, alignment );
	}
	
	public void setColor( int color )
	{
		NativeFunction.textDrawColor( id, color );
	}
	
	public void useBox( boolean use )
	{
		NativeFunction.textDrawUseBox( id, use );
	}
	
	public void setBoxColor( int color )
	{
		NativeFunction.textDrawBoxColor( id, color );
	}
	
	public void setShadow( int size )
	{
		NativeFunction.textDrawSetShadow( id, size );
	}
	
	public void setOutline( int size )
	{
		NativeFunction.textDrawSetOutline( id, size );
	}
	
	public void setBackgroundColor( int color )
	{
		NativeFunction.textDrawBackgroundColor( id, color );
	}
	
	public void setFont( int font )
	{
		NativeFunction.textDrawFont( id, font );
	}
	
	public void setProportional( int set )
	{
		NativeFunction.textDrawSetProportional( id, set );
	}
	
	public void setText( String text )
	{
		if( text == null ) throw new NullPointerException();
		
		this.text = text;
		NativeFunction.textDrawSetString( id, text );
	}
	
	public void show( Player player )
	{
		NativeFunction.textDrawShowForPlayer( player.id, id );
		isPlayerShowed[player.id] = true;
	}
	
	public void hide( Player player )
	{
		NativeFunction.textDrawHideForPlayer( player.id, id );
		isPlayerShowed[player.id] = false;
	}
	
	public void showForAll()
	{
		NativeFunction.textDrawShowForAll( id );
		for( int i=0; i<Gamemode.MAX_PLAYERS; i++ ) isPlayerShowed[i] = true;
	}
	
	public void hideForAll()
	{
		NativeFunction.textDrawHideForAll( id );
		for( int i=0; i<Gamemode.MAX_PLAYERS; i++ ) isPlayerShowed[i] = false;
	}
}