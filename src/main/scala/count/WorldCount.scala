package count

import org.apache.spark.SparkConf

object WorldCount {
  val conf = new SparkConf()
  conf.setAppName("world count")
  conf.setMaster("local[2]")
}
