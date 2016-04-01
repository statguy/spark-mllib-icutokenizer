package org.apache.spark.ml.feature

import org.apache.spark.ml.UnaryTransformer
import org.apache.spark.ml.param.{Param, ParamMap}
import org.apache.spark.ml.util.{DefaultParamsReadable, DefaultParamsWritable, Identifiable}
import org.apache.spark.sql.types.{ArrayType, DataType, StringType}

class Concatenator(override val uid: String) extends
  UnaryTransformer[Seq[String], String, Concatenator] with DefaultParamsWritable {
  def this() = this(Identifiable.randomUID("concatString"))

  val separator = new Param[String](this, "separator", "separator used for concatenating strings")

  def setSeparator(value: String): this.type = set(separator, value)

  def getSeparator: String = $(separator)

  setDefault(separator -> " ")

  override protected def createTransformFunc: Seq[String] => String = _.mkString($(separator))

  override protected def validateInputType(inputType: DataType): Unit = {
    require(inputType == ArrayType(StringType, true), s"Input type must be array string type but got $inputType.")
  }

  override protected def outputDataType: DataType = StringType

  override def copy(extra: ParamMap): Concatenator = defaultCopy(extra)
}

object Concatenator extends DefaultParamsReadable[Concatenator] {
  override def load(path: String): Concatenator = super.load(path)
}
