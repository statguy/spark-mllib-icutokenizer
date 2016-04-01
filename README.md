ICU tokenizer for Apache Spark
==============================

The Scala class `org.apache.spark.ml.feature.ICUTokenizer` adds a multilanguage tokenizer support
to [Apache Spark](http://spark.apache.org/) to separate words from text of languages that
[the ICU project](http://icu-project.org) supports. This has the importance of tokenizing
languages in which words are written together without clear boundaries, such as many Asian languages.
Usage is similar to [`Tokenizer`](https://spark.apache.org/docs/1.6.1/api/java/org/apache/spark/ml/feature/Tokenizer.html)
except the language is defined with the `setLocale(locale)` method, where `locale` is
the [`java.util.Locale`](https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html) object.

The `org.apache.spark.ml.feature.Concatenator` transformer concatenates an array of strings in a Spark
[data frame](https://spark.apache.org/docs/1.6.1/api/java/org/apache/spark/sql/DataFrame.html) to a single string.
This is sort of a hack to enable the use of multiple tokenizers serially, since the tokenizers do not support
string arrays. A typical use case would be to apply `ICUTokenizer` first, then concatenate the strings and apply
[`RegexTokenizer`](https://spark.apache.org/docs/1.6.1/api/java/org/apache/spark/ml/feature/Tokenizer.html)
to remove non-words.

See examples in the unit tests directory (coming soon...).

Use the standard [SBT](http://www.scala-sbt.org/) one-liner `sbt package` to build a JAR from the sources to be
included in your project.

Feedback: Jussi Jousimo, [`jvj@iki.fi`](mailto:jvj@iki.fi).
