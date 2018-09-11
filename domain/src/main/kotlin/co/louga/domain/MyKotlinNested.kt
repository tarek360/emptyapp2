package co.louga.domain

class MyKotlinNested {

  companion object {
    fun start(num: Int) {
      if (num > 1) {
        print("more than 1")
      }
    }
  }

  class Nested1 {
    fun start(num: Int) {
      if (num > 1) {
        print("more than 1")
      }
    }

    class Nested1_1 {
      fun start(num: Int) {
        if (num > 1) {
          print("more than 1")
        }
      }
    }
  }


  class Nested2 {
    fun start(num: Int) {
      if (num > 1) {
        print("more than 1")
      }
    }
  }

}
