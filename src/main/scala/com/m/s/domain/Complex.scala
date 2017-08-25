package com.m.s.domain

object Complex {
  def abs(complex: Complex): Double = scala.math.sqrt(complex.real * complex.real + complex.imaginary * complex.imaginary)

  def divergentIterations(c: Complex, z: Complex = Complex(0, 0), iterations: Int = 256, absLimit: Double = 2): Int = {
    val z2 = z * z + c

    if (abs(z2) > absLimit || iterations == 0) {
      iterations
    } else {
      divergentIterations(c, z2, iterations - 1, absLimit)
    }
  }
}

case class Complex(real: Double, imaginary: Double) {
  def +(that: Complex) = Complex(real + that.real, imaginary + that.imaginary)

  def *(that: Complex) = Complex(real * that.real - imaginary * that.imaginary, real * that.imaginary + imaginary * that.real)

  override def toString: String = s"$real + ${imaginary}i"
}