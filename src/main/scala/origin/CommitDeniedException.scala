package origin

private[spark] class CommitDeniedException(msg : _root_.scala.Predef.String, jobID : scala.Int, splitID : scala.Int, attemptNumber : scala.Int) extends scala.Exception {
  //任务提交否定任务
  def toTaskCommitDeniedReason : org.apache.spark.TaskCommitDenied = { /* compiled code */ }
}
