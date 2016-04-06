import java.util.Locale

import org.apache.spark.ml.feature._
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

import scala.collection.mutable

/**
  * Created by jvj on 4/6/2016 AD.
  */
class ICUTokenizerTest extends FlatSpec with Matchers with BeforeAndAfter {
  val master = "local[1]"
  val appName = "icutokenizer-test"
  implicit var sc: SparkContext = _
  implicit var sqlContext: SQLContext = _

  before {
    val conf = new SparkConf()
      .setMaster(master)
      .setAppName(appName)
    sc = new SparkContext(conf)
    sqlContext = new SQLContext(sc)
  }

  after {
    if (sc != null) sc.stop()
  }

  "Thai sentences" should "be tokenized" in {
    val data = Seq(
      Tuple1("สวัสดีค่ะ"),
      Tuple1("ยินดีที่ได้รู้จักค่ะ")
    )
    val df0 = sqlContext.createDataFrame(data).toDF("text")

    val tokenizer = new ICUTokenizer()
      .setInputCol("text")
      .setOutputCol("tokens")
      .setLocale(new Locale("th"))
    val df1 = tokenizer.transform(df0)
    val tokens = df1.collect.map(_.getAs[mutable.WrappedArray[String]]("tokens").toArray)
    tokens should be(Array(
      Array("สวัสดี", "ค่ะ"),
      Array("ยินดี", "ที่", "ได้", "รู้จัก", "ค่ะ"))
    )
  }

  "String array" should "be concatenated to string" in {
    val data = Seq(
      Tuple1(Array("ยินดี", "ที่", "ได้", "รู้จัก", "ค่ะ"))
    )

    val df0 = sqlContext.createDataFrame(data).toDF("tokens")
    val concatenator = new Concatenator()
      .setInputCol("tokens")
      .setOutputCol("text")
      .setSeparator(",")
    val df1 = concatenator.transform(df0)
    val text = df1.collect.map(_.getString(1))
    text(0) should be("ยินดี,ที่,ได้,รู้จัก,ค่ะ")
  }
}
