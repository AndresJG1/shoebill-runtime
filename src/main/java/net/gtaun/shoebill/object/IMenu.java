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


/**
 * @author MK124
 *
 */

public interface IMenu extends IDestroyable
{
	int getId();
	String getTitle();
	int getColumns();
	float getX();
	float getY();
	float getCol1Width();
	float getCol2Width();
	String getColumnHeader();
	
	void addItem( int column, String text );
	void setColumnHeader( int column, String text );
	void disable();
	void disableRow( int row );
	void show( IPlayer player );
	void hide( IPlayer player );
}
