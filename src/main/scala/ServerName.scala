import org.apache.spark.{SparkConf, SparkContext}

object ServerName {
  def main(args: Array[String]): Unit = {
    //创建spark配置对象
    val conf = new SparkConf();
    //设置运行时的显示名称
    conf.setAppName("ServerName")
    //设置运行在本地还是集群中，运行在本地可以写local或者local[K]
    //如果运行在集群中那种需要以standlone模式运行，需要使用spark://HOST:PORT
    conf.setMaster("local[2]")
    //第二步，创建sc对象，这是spark程序的唯一入口
    //sc核心作用：初始化spark应用程序   所需要的核心组件，包括DAGScheduler TaskScheduler SchedulerBackEnd
    //还会负责spark程序向master注册程序
    val sc = new SparkContext(conf)
    //第三步:根据具体的数据来源后等通过sparkcontext来创建rdd
    //，创建rdd,外部来源通过scala集合使用然后产生rdd，通过rdd产生rdd
    val lines = sc.textFile("D:\\hello.txt")
    //用于些函数来进行计算
    val words = lines
      .flatMap(_.split(","))
      .flatMap(_.split(" "))
      .filter(word => word != " ");
    val pairs = words.map(word => (word, 1))
    val wordsCount = pairs.reduceByKey(_ + _)
    val result = wordsCount.collect();
    result.foreach(println);
    sc.stop()
  }
}
