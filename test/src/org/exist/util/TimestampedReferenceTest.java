/*
 * eXist Open Source Native XML Database
 * Copyright (C) 2001-2018 The eXist Project
 * http://exist-db.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.exist.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Adam Retter <adam@evolvedbinary.com>
 */
public class TimestampedReferenceTest {

    @Test
    public void setIfExpired_expired() {
        final TimestampedReference<String> timestampedReference = new TimestampedReference<>(true, "Original");
        assertEquals("Original", timestampedReference.get());

        timestampedReference.setIfExpired(System.nanoTime(), () -> "Updated");
        assertEquals("Updated", timestampedReference.get());
    }

    @Test
    public void setIfExpired_notExpired() {
        final long firstTimestamp = System.nanoTime();

        final TimestampedReference<String> timestampedReference = new TimestampedReference<>(true, "Original");
        assertEquals("Original", timestampedReference.get());

        timestampedReference.setIfExpired(firstTimestamp, () -> "Updated");
        assertEquals("Original", timestampedReference.get());
    }

    @Test
    public void setIfExpiredOrNull_expired() {
        final TimestampedReference<String> timestampedReference = new TimestampedReference<>(true, "Original");
        assertEquals("Original", timestampedReference.get());

        timestampedReference.setIfExpiredOrNull(System.nanoTime(), () -> "Updated");
        assertEquals("Updated", timestampedReference.get());
    }

    @Test
    public void setIfExpiredOrNull_notExpired() {
        final long firstTimestamp = System.nanoTime();

        final TimestampedReference<String> timestampedReference = new TimestampedReference<>(true, "Original");
        assertEquals("Original", timestampedReference.get());

        timestampedReference.setIfExpiredOrNull(firstTimestamp, () -> "Updated");
        assertEquals("Original", timestampedReference.get());
    }

    @Test
    public void setIfExpiredOrNull_expiredAndNull() {
        final TimestampedReference<String> timestampedReference = new TimestampedReference<>(true);
        assertEquals(null, timestampedReference.get());

        timestampedReference.setIfExpiredOrNull(System.nanoTime(), () -> "Updated");
        assertEquals("Updated", timestampedReference.get());
    }

    @Test
    public void setIfExpiredOrNull_notExpiredAndNull() {
        final long firstTimestamp = System.nanoTime();

        final TimestampedReference<String> timestampedReference = new TimestampedReference<>(true, null);
        assertEquals(null, timestampedReference.get());

        timestampedReference.setIfExpiredOrNull(firstTimestamp, () -> "Updated");
        assertEquals("Updated", timestampedReference.get());
    }
}
