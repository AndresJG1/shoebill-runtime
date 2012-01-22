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

package net.gtaun.shoebill.util.event;

/**
 * @author MK124
 *
 */

public enum EventListenerPriority
{
	BOTTOM		( (short) -32768 ),
	LOWEST		( (short) -16384 ),
    LOW			( (short) -8192 ),
    NORMAL		( (short) 0 ),
    HIGH		( (short) 8192 ),
    HIGHEST		( (short) 16384 ),
    MONITOR		( (short) 32767 );

    private final short value;

    private EventListenerPriority( short value )
    {
        this.value = value;
    }

    public short getValue()
    {
        return value;
    }
}
