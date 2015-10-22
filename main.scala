package runtime

import scala.io.Source
import parser.Parser

object Main {
  def main(args: Array[String]) {
    //printImprovedHalstead(args(0))
    //println()
    var parser = new Parser(args(0))
    //println(parser getRegressionVariable)
    println(parser getLogitValue)
  }

  def printImprovedHalstead(filename: String) {
    var sb = getStringBuilder(filename)
    var parser = new Parser(sb.toString())
    println( parser getLineCount )
    println( parser getByteEntropy )
    println( parser getVolume )
  }

  def getStringBuilder(filename: String): StringBuilder = {
    return Source.fromFile(filename).addString(new StringBuilder(256))
  }
}
