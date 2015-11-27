package runtime

import scala.io.Source
import parser.Parser

object Main {
  def main(args: Array[String]) {
    var parser = new Parser( getStringBuilder(args(0)).toString.stripLineEnd )
    /* Debugging
    printImprovedHalstead(args(0))
    println()
    println(parser.getRegressionVariable)
    */
    println( parser getLogitValue )
  }

  def printImprovedHalstead(filename: String) {
    var sb = getStringBuilder(filename)
    var parser = new Parser(sb.toString())
    println( parser.getLineCount )
    println( parser.getByteEntropy )
    println( parser.getVolume )
  }

  def getStringBuilder(filename: String): StringBuilder = {
    return Source.fromFile(filename).addString(new StringBuilder(256))
  }
}
