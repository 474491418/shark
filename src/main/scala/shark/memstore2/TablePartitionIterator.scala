/*
 * Copyright (C) 2012 The Regents of The University California.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package shark.memstore2

import java.nio.ByteBuffer


/**
 * An iterator for a partition of data. Each element returns a ColumnarStruct
 * that can be read by a ColumnarStructObjectInspector.
 */
class TablePartitionIterator(val numRows: Long, val columnIterators: Array[ColumnIterator])
  extends Iterator[ColumnarStruct] {

  val struct = new ColumnarStruct(columnIterators)

  var position: Long = 0

  def hasNext(): Boolean = position < numRows

  def next(): ColumnarStruct = {
    System.out.println("POSITION ========================== " + position + " " + numRows)
    position += 1
    columnIterators.foreach(_.next)
    struct
  }
}
