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

import net.gtaun.shoebill.data.Vector3D;
import net.gtaun.shoebill.data.type.PlayerAttachBone;

/**
 * @author MK124
 *
 */

public interface IPlayerAttach extends IPlayerRelated
{
	public interface IPlayerAttachSlot
	{
		public PlayerAttachBone getBone();
		public int getModelId();
		public Vector3D getOffset();
		public Vector3D getRotation();
		public Vector3D getScale();
		
		public boolean set( PlayerAttachBone bone, int modelId, Vector3D offset, Vector3D rotation, Vector3D scale );
		public boolean remove();
		public boolean isUsed( int slot );
	}
	
	IPlayerAttachSlot getSlot( int slot );
	IPlayerAttachSlot[] getSlots();
}
