package parser

import java.io._
import java.nio.charset.StandardCharsets
import scala.collection.mutable.HashMap
import scala.collection.JavaConverters._
import scala.math
import org.antlr.v4.runtime._
import cgrammar._

object Parser {
  var Y_INTERCEPT = 8.87
  var LINE_COUNT_COEF = 0.4
  var BYTE_ENTROPY_COEF = -1.5
  var VOLUME_COEF = -0.033
}

class Parser(string: String) {
  private var characterMap = scala.collection.mutable.HashMap.empty[Char,Int]

  def generateAST() {
    var in = new ANTLRInputStream
        (new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8)))
    var lexer = new CLexer(in)
    var tokens = new CommonTokenStream(lexer)
    var parser = new CParser(tokens)
    var rulesList = List(parser.getRuleNames().toSeq: _*)
    println( parser.functionDefinition().toStringTree(
      rulesList.asJava ) )
  }

  def getLineCount(): Int = {
    return string.lines.count((s: String) => true)
  }

  private def percentOccurrence(c: Char): Double = {
    return characterMap(c).toDouble / string.length
  }
  
  private def logProduct(c: Char): Double = {
    var p = percentOccurrence(c)
    return p * (math.log(p) / math.log(2))
  }

  def getByteEntropy(): Double = {
    for ( c <- string.toCharArray ) {
      characterMap += (c -> (characterMap.getOrElse(c, 0) + 1))
    }

    var entropySum = 0.0
    for ( kv <- characterMap ) {
      entropySum += logProduct(kv._1)
    }
    return -entropySum
  }
  
  def getVolume(): Double = {
    var in = new ANTLRInputStream(
        new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8)))
    var lexer = new CLexer(in)
    var tokenStream = new CommonTokenStream(lexer)
    tokenStream.fill()
    var tokens = tokenStream.getTokens()
    var tokenSet = scala.collection.mutable.Set.empty[String]
    for ( i <- 0 until tokens.size ) {
      tokenSet += tokens.get(i).getText
    }
    //println(tokens.size)
    //println(tokenSet.size)
    return tokens.size * (math.log(tokenSet.size) / math.log(2))
  }

  def getRegressionVariable: Double = {
    return Parser.Y_INTERCEPT
        .+(Parser.LINE_COUNT_COEF * getLineCount)
        .+(Parser.BYTE_ENTROPY_COEF * getByteEntropy)
        .+(Parser.VOLUME_COEF * getVolume)
  }

  def getLogitValue: Double = {
    return 1 / (1 + math.pow(math.E, -getRegressionVariable))
  }
}
