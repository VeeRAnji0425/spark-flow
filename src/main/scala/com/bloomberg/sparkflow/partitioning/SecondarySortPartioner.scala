/*
 * Copyright 2016 Bloomberg LP
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

package com.bloomberg.sparkflow.partitioning

import org.apache.spark.Partitioner

/**
  * Created by ngoehausen on 6/6/16.
  */
class SecondarySortPartioner[K, K2, V](partitions: Int) extends Partitioner {
  require(partitions >= 0, s"Number of partitions ($partitions) cannot be negative.")

  override def numPartitions: Int = partitions

  override def getPartition(key: Any): Int = {
    val (k, k2) = key.asInstanceOf[(K, K2)]
    // should be k.hashCode() mod numPartitions but java is dumb
    (k.hashCode() % numPartitions + numPartitions) % numPartitions
  }

}
