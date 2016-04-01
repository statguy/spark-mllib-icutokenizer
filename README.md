ICU tokenizer for Apache Spark
==============================

The Scala class `org.apache.spark.ml.feature.ICUTokenizer` adds a multilanguage tokenizer to
[Apache Spark](http://spark.apache.org/) to separate words from text of languages that
[the ICU project](http://icu-project.org) supports. This has the importance of tokenizing
languages in which words are written together without clear boundaries.
Usage is similar to [`Tokenizer`](https://spark.apache.org/docs/1.6.1/api/java/org/apache/spark/ml/feature/Tokenizer.html)
except the language is given using the [`Locale`](https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html) object.

The `org.apache.spark.ml.feature.Concatenator` transformer concatenates an array of strings in a Spark
data frame to a single string. This is sort of a hack to enable the use of multiple tokenizers serially.
A typical use case would be to apply `ICUTokenizer` first, then concatenate the strings
and apply [`RegexTokenizer`](https://spark.apache.org/docs/1.6.1/api/java/org/apache/spark/ml/feature/Tokenizer.html)
to remove non-words.

See examples in the unit tests directory (coming soon...).

Use the standard Scala `sbt package` to build a jar from the sources.

Feedback: Jussi Jousimo, [`jvj@iki.fi`](mailto:jvj@iki.fi).
