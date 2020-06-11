package executor

import java.io.{ByteArrayOutputStream, DataOutputStream}

import scala.collection.mutable

object ScalaExcutor {
  def function(): Unit = {
    //设置数据输出
    val out = new ByteArrayOutputStream(4096)
    val dataOut = new DataOutputStream(out)
    //Write currentFiles
    val taskFiles = new mutable.HashMap[String, Long]()
    val numFiles = dataIn.readInt();
  }

  def main(args: Array[String]): Unit = {

  }
}
