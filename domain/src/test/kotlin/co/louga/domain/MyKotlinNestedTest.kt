package co.louga.domain

import org.junit.Test

class MyKotlinNestedTest {


  @Test
  fun testStart() {

    MyKotlinNested.start(0)
    MyKotlinNested.start(2)

    MyKotlinNested.Nested1().start(0)
    MyKotlinNested.Nested1().start(2)

    MyKotlinNested.Nested2().start(0)
    MyKotlinNested.Nested2().start(2)

    MyKotlinNested.Nested1.Nested1_1().start(0)
    MyKotlinNested.Nested1.Nested1_1().start(2)

  }
}