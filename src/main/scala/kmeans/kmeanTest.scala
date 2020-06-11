package kmeans

import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.feature.{HashingTF, IDF, Tokenizer}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

case class Record(
                   id: String,
                   companyname: String,
                   direction: String,
                   productinfo: String
                 )

object kmeanTest {
  def main(args: Array[String]): Unit = {
    //机器学习最主要的是性能的调优，及一些参数的设置。
    val conf = new SparkConf()
    conf.setAppName("kmean test").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._
    val records = sc.textFile("D://test.txt")
      .map { x =>
        val data = x.split(",")
        Record(data(0), data(1), data(2), data(3))
      }.toDF().cache()

    val wordData = new Tokenizer()
      .setInputCol("productinfo")
      .setOutputCol("productwords")
      .transform(records)

    val tfData = new HashingTF()
      .setNumFeatures(20)
      .setInputCol("productwords")
      .setOutputCol("productFeatures")
      .transform(wordData)
    val idfModel = new IDF()
      .setInputCol("productFeatures")
      .setOutputCol("features")
      .fit(tfData)
    //    idfModel.save("")
    val idfData = idfModel.transform(tfData)
    val traingData = idfData.select("id", "companyname", "features")
    val kmeans = new KMeans()
      .setK(10)
      .setMaxIter(5)
      .setFeaturesCol("features")
      .setPredictionCol("prediction")
    val kmeansmodel = kmeans.fit(traingData)
    val kmeansData = kmeansmodel.transform(traingData).cache()
    kmeansData.show()
  }
}
