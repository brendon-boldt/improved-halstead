package runtime

import scala.io.Source
import parser.Parser

object Main {
  def main(args: Array[String]) {
    var parser = new Parser(getStringBuilder(args(0)).toString)
    //println(parser.regressionVariable)
    println(parser.logitValue)
  }

  def printImprovedHalstead(filename: String) {
    var sb = getStringBuilder(filename)
    var parser = new Parser(sb.toString())
    println( parser.lineCount )
    println( parser.byteEntropy)
    println( parser.volume)
  }

  def getStringBuilder(filename: String): StringBuilder = {
    return Source.fromFile(filename).addString(new StringBuilder(256))
  }
}
