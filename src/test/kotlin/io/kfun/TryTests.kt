package io.kfun

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test


class TryTests {

    @Test
    fun `should return a Success type on clean execution`() {
        val tryResult: Try<Int> = Try {
            4 / 2
        }

        assertThat(tryResult.isSuccess()).isTrue()

        if (tryResult is Success) {
            assertThat(tryResult.value).isEqualTo(2)
        } else {
            assertThat(false).describedAs("Not expected!").isTrue()
        }
    }

    @Test
    fun `should return a Failure type on exceptions`() {
        val tryResult: Try<Int> = Try {
            1 / 0
        }

        assertThat(tryResult.isFailure()).isTrue()

        if (tryResult is Failure) {
            assertThat(tryResult.e)
                    .isInstanceOf(ArithmeticException::class.java)
        } else {
            assertThat(false).describedAs("Not expected!").isTrue()
        }
    }

    @Test
    fun `should be able to pattern match on type of result`() {
        val t1 = Try {
            1 / 0
        }
        val r1 = when (t1) {
            is Failure -> -1
            is Success -> t1.value
        }

        assertThat(r1).isEqualTo(-1)

        val t2 = Try { 2 }

        val r2 = when (t2) {
            is Failure -> -1
            is Success -> t2.value
        }

        assertThat(r2).isEqualTo(2)
    }

    @Test
    fun `should be able to map wrapped clean value`() {
        val t1 = Try { 2 }

        val t2 = t1.map({ it * 2 }).map { it * it }

        assertThat(t2).isEqualTo(Success(16))
    }

    @Test
    fun `should be able to map an exception through`() {
        val t1 = Try {
            2 / 0
        }

        val t2 = t1.map({ it * 2 }).map { it * it }

        assertThat(t2).isEqualTo(Failure<Int>((t2 as Failure).e))
    }

    @Test
    fun `should be able to map with exception along the way`() {
        val t1 = Try { 2 }

        val t2 = t1
                .map({ it * 2 })
                .map { it.toString() + "t" }
                .map { it.toInt() }

        assertThat(t2).isEqualTo(Failure<Int>((t2 as Failure).e))
    }

    @Test
    fun `should be able to flatMap a clean value`() {
        val t1 = Try { 2 }

        val t2 = t1
                .flatMap { i -> Try { i * 2 } }
                .flatMap { i -> Try { i.toString() } }

        assertThat(t2).isEqualTo(Success("4"))
    }

    @Test
    fun `should be able to flaMap with exception along the way`() {
        val t1 = Try { 2 }

        val t2 = t1
                .flatMap { i -> Try { i * 2 } }
                .flatMap { i -> Try { i.toString() + "t" } }
                .flatMap { i -> Try { i.toInt() } }

        println(t2)

        assertThat(t2.isFailure()).isTrue()
    }

    @Test
    fun `should return a default value in case of failure`() {
        val t1 = Try { 1 }

        assertThat(t1.getOrElse(100)).isEqualTo(1)

        val t2 = Try { "something" }
                .map { it.toInt() }
                .getOrElse(100)

        assertThat(t2).isEqualTo(100)
    }

    @Test
    fun `get should throw an exception for a Failure case`() {
        val t = Try {
            throw RuntimeException("exception")
        }

        assertThatThrownBy { t.get() }

    }

    @Test
    fun `test functional exception handling`() {
        fun divideFn(dividend: String, divisor: String): Try<Int> {
            val num = Try { dividend.toInt() }
            val denom = Try { divisor.toInt() }
            return num.flatMap { n -> denom.map { d -> n / d } }
        }

        fun divide(dividend: String, divisor: String): Int {
            val num = dividend.toInt()
            val denom = divisor.toInt()
            return num / denom
        }

        val result = divideFn("5t", "4")
        when (result) {
            is Success -> println("Got ${result.value}")
            is Failure -> println("An error : ${result.e}")
        }
        println(divideFn("5", "0"))
        println(divideFn("5", "3"))

        try {
            divide("5t", "4")
        } catch (e: ArithmeticException) {
            println("Got an exception $e")
        } catch (e: NumberFormatException) {
            println("Got an exception $e")
        }

    }


    @Test
    fun `orElse should return the default on failure`() {
        val t: Try<Int> = Try {
            throw RuntimeException("exception")
        }

        assertThat(t.orElse(Try { 1 }).get()).isEqualTo(1)
    }

    @Test
    fun `fold for a Success should apply fb`() {
        val t = Try { 1 }

        val r = t.fold({ m -> m.message }, { n -> n.toString() })

        assertThat(r).isEqualTo("1")
    }

    @Test
    fun `fold for a Success with exception in fb should apply fa`() {
        val t = Try { 1 }

        val r = t.fold({ m -> m.message }, { _ -> throw RuntimeException("something") })

        assertThat(r).isEqualTo("something")
    }

    @Test
    fun `fold for a Failure should apply fb`() {
        val t = Try { throw RuntimeException("something") }

        val r = t.fold({ m -> m.message }, { _ -> throw RuntimeException("else") })

        assertThat(r).isEqualTo("something")
    }
}