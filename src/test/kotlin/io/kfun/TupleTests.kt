package io.kfun

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TupleTests {

    @Test
    fun `should be able to create a tuple of 1 element`() {
        val tup = Tuple1("elem1")
        assertThat(tup._1).isEqualTo("elem1")

        val (e1) = tup
        assertThat(e1).isEqualTo("elem1")
    }

    @Test
    fun `should be able to create Tuple1 with a utility method`() {
        val tup = Tuple.of("elem1")

        assertThat(tup._1).isEqualTo("elem1")
    }

    @Test
    fun `should be able to create a tuple with 2 elements of different types`() {
        val tup = Tuple2("elem1", "elem2")
        assertThat(tup._1).isEqualTo("elem1")
        assertThat(tup._2).isEqualTo("elem2")

        val (e1, e2) = tup

        assertThat(e1).isEqualTo("elem1")
        assertThat(e2).isEqualTo("elem2")
    }

    @Test
    fun `should be able to create Tuple2 with a utility method`() {
        val tup = Tuple.of("elem1", "elem2")
        assertThat(tup._1).isEqualTo("elem1")
        assertThat(tup._2).isEqualTo("elem2")
    }

    @Test
    fun `should be able to use Pair as a standin for Tuple2`() {
        val pair = Pair("elem1", "elem2")
        assertThat(pair._1).isEqualTo("elem1")
        assertThat(pair._2).isEqualTo("elem2")
    }

    @Test
    fun `should be able to create a tuple with 3 elements of different types`() {
        val tup = Tuple3("elem1", "elem2", "elem3")
        assertThat(tup._1).isEqualTo("elem1")
        assertThat(tup._2).isEqualTo("elem2")
        assertThat(tup._3).isEqualTo("elem3")

        val (e1, e2, e3) = tup

        assertThat(e1).isEqualTo("elem1")
        assertThat(e2).isEqualTo("elem2")
        assertThat(e3).isEqualTo("elem3")

    }

    @Test
    fun `should be able to create Tuple3 with a utility method`() {
        val tup = Tuple.of("elem1", "elem2", "elem3")
        assertThat(tup._1).isEqualTo("elem1")
        assertThat(tup._2).isEqualTo("elem2")
        assertThat(tup._3).isEqualTo("elem3")
    }

    @Test
    fun `should be able to use Triple as a standin for Tuple3`() {
        val trip = Triple("elem1", "elem2", "elem3")
        assertThat(trip._1).isEqualTo("elem1")
        assertThat(trip._2).isEqualTo("elem2")
        assertThat(trip._3).isEqualTo("elem3")
    }

    @Test
    fun `should be able to create a tuple with 4 elements each of different type`() {
        val tup = Tuple4("elem1", "elem2", "elem3", "elem4")
        assertThat(tup._1).isEqualTo("elem1")
        assertThat(tup._2).isEqualTo("elem2")
        assertThat(tup._3).isEqualTo("elem3")
        assertThat(tup._4).isEqualTo("elem4")

        val (e1, e2, e3, e4) = tup
        assertThat(e1).isEqualTo("elem1")
        assertThat(e2).isEqualTo("elem2")
        assertThat(e3).isEqualTo("elem3")
        assertThat(e4).isEqualTo("elem4")

    }

    @Test
    fun `should be able to create Tuple4 with a utility method`() {
        val tup = Tuple.of("elem1", "elem2", "elem3", "elem4")
        assertThat(tup._1).isEqualTo("elem1")
        assertThat(tup._2).isEqualTo("elem2")
        assertThat(tup._3).isEqualTo("elem3")
        assertThat(tup._4).isEqualTo("elem4")
    }

    @Test
    fun `should be able to create a tuple with 5 elements each of different type`() {
        val tup = Tuple5("elem1", "elem2", "elem3", "elem4", "elem5")
        assertThat(tup._1).isEqualTo("elem1")
        assertThat(tup._2).isEqualTo("elem2")
        assertThat(tup._3).isEqualTo("elem3")
        assertThat(tup._4).isEqualTo("elem4")
        assertThat(tup._5).isEqualTo("elem5")

        val (e1, e2, e3, e4, e5) = tup
        assertThat(e1).isEqualTo("elem1")
        assertThat(e2).isEqualTo("elem2")
        assertThat(e3).isEqualTo("elem3")
        assertThat(e4).isEqualTo("elem4")
        assertThat(e5).isEqualTo("elem5")
    }

    @Test
    fun `should be able to create Tuple5 with a utility method`() {
        val tup = Tuple.of("elem1", "elem2", "elem3", "elem4", "elem5")
        assertThat(tup._1).isEqualTo("elem1")
        assertThat(tup._2).isEqualTo("elem2")
        assertThat(tup._3).isEqualTo("elem3")
        assertThat(tup._4).isEqualTo("elem4")
        assertThat(tup._5).isEqualTo("elem5")
    }

}