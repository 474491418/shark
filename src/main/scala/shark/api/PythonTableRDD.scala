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

package shark.api

import net.razorvine.pickle.Pickler

import org.apache.spark.api.java.JavaRDD

class PythonTableRDD(tableRDD: JavaTableRDD)
extends JavaRDD[Array[Byte]](tableRDD.rdd.map(PythonTableRDD.pickle)) {
  val schema = tableRDD.schema.map(PythonTableRDD.pickle)
}

object PythonTableRDD {

  def sql2rdd(sc: JavaSharkContext, cmd: String) {
    new PythonTableRDD(sc.sql2rdd(cmd))
  }

  def pickle(o: Any): Array[Byte] = {
    val pickle = new Pickler
    pickle.dumps(o)
  }
}
