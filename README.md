[![Build Status](https://travis-ci.org/statguy/spark-mllib-icutokenizer.svg?branch=master)](https://travis-ci.org/statguy/spark-mllib-icutokenizer)

ICU tokenizer for Apache Spark
==============================

* This [Apache Spark](http://spark.apache.org/) extension provides a Scala class
`org.apache.spark.ml.feature.ICUTokenizer` that adds a multilanguage tokenizer support to Spark. The tokenizer
separates words from text of the languages that [the ICU project](http://icu-project.org) supports. In particular,
the tokenizer is useful for languages in which the words are written together without clear boundaries,
such as many Asian languages. Usage is similar to
[`Tokenizer`](https://spark.apache.org/docs/1.6.1/api/java/org/apache/spark/ml/feature/Tokenizer.html)
except the language is first defined with the `setLocale(locale)` method, where `locale` is a
[`java.util.Locale`](https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html) object.
* Additionally, the extension provides `org.apache.spark.ml.feature.Concatenator` transformer that concatenates
an array of strings in a Spark
[data frame](https://spark.apache.org/docs/1.6.1/api/java/org/apache/spark/sql/DataFrame.html) to a single string.
This is sort of a hack to enable the use of multiple tokenizers serially, since the tokenizers do not read
string arrays as input parameter. A typical use case would be to apply `ICUTokenizer` first, then concatenate
the strings and apply
[`RegexTokenizer`](https://spark.apache.org/docs/1.6.1/api/java/org/apache/spark/ml/feature/Tokenizer.html)
to remove non-words.

See examples in the [unit tests source code](/src/test/scala-2.10/ICUTokenizerTest.scala).

Run [SBT](http://www.scala-sbt.org/) `sbt package` to build a JAR from the sources to be included in
your project.

Feedback: Jussi Jousimo, [`jvj@iki.fi`](mailto:jvj@iki.fi).
