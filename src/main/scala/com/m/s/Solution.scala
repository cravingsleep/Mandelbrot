package com.m.s

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import com.m.s.domain.Complex

object Solution {
  def main(args: Array[String]): Unit = {
    val width = 10000
    val height = 10000
    val out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

    (0 until width).par.foreach { x =>
      (0 until height).par.foreach { y =>
        val c = pixelToMandelbrotComplex(x, y, width, height)

        val iterations = Complex.divergentIterations(c)

        out.setRGB(x, y, Color.HSBtoRGB(iterations / 256.0f, 1, iterations / (iterations + 8f)))
      }
    }

    ImageIO.write(out, "png", new File("set.png"))
  }

  def pixelToMandelbrotComplex(x: Int, y: Int, width: Int, height: Int): Complex = {
    // x coordinate, mapped from (0..1000) -> (-2..1)
    // map x -> (0..3) - 2

    val real = (x * 3.0 / width) - 2.0

    // y coordinate mapped from (0..1000) -> (-1, 1)
    // map y -> (0..2) -1

    val imaginary = (y * 2.0 / height) - 1.0

    Complex(real, imaginary)
  }
}
