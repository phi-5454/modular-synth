package SynthLogic

/**
 * Does not support polyphony yet
 */
class ModularSynthesizer() {
  // Here just to manage calls, no practical use yet.
  val voices:Array[Int] = ???
  val outputComponent:SynthComponent[FloatValue] =
    ComponentLibrary.passthrough
  def output:Double = outputComponent.output.value
}

/**
 * The companion object contains factory methods
 */
object ModularSynthesizer:
  def default =
    ModularSynthesizer()

end ModularSynthesizer
