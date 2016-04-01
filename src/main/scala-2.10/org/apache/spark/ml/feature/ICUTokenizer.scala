package org.apache.spark.ml.feature

import java.util.Locale

import com.ibm.icu.text._
import org.apache.spark.ml._
import org.apache.spark.ml.param._
import org.apache.spark.ml.util._
import org.apache.spark.sql.types._

class ICUTokenizer(override val uid: String)
  extends UnaryTransformer[String, Seq[String], ICUTokenizer] with DefaultParamsWritable {

  def this() = this(Identifiable.randomUID("ICUTok"))

  val locale = new Param[Locale](this, "locale", "locale used for tokenizing")

  def setLocale(value: Locale): this.type = set(locale, value)

  def getLocale: Locale = $(locale)

  setDefault(locale -> new Locale("en_US"))

  override protected def createTransformFunc: String => Seq[String] = {
    textRaw =>
      val boundary = BreakIterator.getWordInstance($(locale))
      val text = textRaw.toLowerCase
      boundary.setText(text)

      val words = new scala.collection.mutable.ArrayBuffer[String]()

      var start = boundary.first
      var end = boundary.next
      while (end != BreakIterator.DONE) {
        val word = text.substring(start, end).trim
        // Ignore whitespace
        if (word.nonEmpty) words += word
        start = end
        end = boundary.next
      }

      words
  }

  override protected def validateInputType(inputType: DataType): Unit = {
    require(inputType == StringType, s"Input type must be string type but got $inputType.")
  }

  override protected def outputDataType: DataType = new ArrayType(StringType, true)

  override def copy(extra: ParamMap): ICUTokenizer = defaultCopy(extra)
}

object ICUTokenizer extends DefaultParamsReadable[ICUTokenizer] {
  override def load(path: String): ICUTokenizer = super.load(path)
}
