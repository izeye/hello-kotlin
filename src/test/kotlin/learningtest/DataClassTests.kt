package learningtest

import com.google.gson.Gson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests for data class.
 *
 * @author Johnny Lim
 */
class DataClassTests {

    @Test
    fun copy() {
        val person = Person(firstName = "Johnny", lastName = "Lim", age = 20, firstItems = listOf(Item("ID card")))
        person.secondItems.add(Item("coin"))

        val copied1 = person.copy()

        assertThat(copied1).isNotSameAs(person)
        assertThat(copied1.firstName).isSameAs(person.firstName)
        assertThat(copied1.lastName).isSameAs(person.lastName)
        assertThat(copied1.age).isSameAs(person.age)
        assertThat(copied1.firstItems).isSameAs(person.firstItems)
        assertThat(copied1.secondItems).isEmpty()

        val copied2 = person.copy(firstItems = person.firstItems.toList())

        assertThat(copied2).isNotSameAs(person)
        assertThat(copied2.firstName).isSameAs(person.firstName)
        assertThat(copied2.lastName).isSameAs(person.lastName)
        assertThat(copied2.age).isSameAs(person.age)
        assertThat(copied2.firstItems).isEqualTo(person.firstItems).isNotSameAs(person.firstItems)
        assertThat(copied2.firstItems[0]).isSameAs(person.firstItems[0])
        assertThat(copied2.secondItems).isEmpty()

        val copied3 = person.copy(firstItems = person.firstItems.map { it.copy() }.toList())

        assertThat(copied3).isNotSameAs(person)
        assertThat(copied3.firstName).isSameAs(person.firstName)
        assertThat(copied3.lastName).isSameAs(person.lastName)
        assertThat(copied3.age).isSameAs(person.age)
        assertThat(copied3.firstItems).isEqualTo(person.firstItems).isNotSameAs(person.firstItems)
        assertThat(copied3.firstItems[0]).isEqualTo(person.firstItems[0]).isNotSameAs(person.firstItems[0])
        assertThat(copied3.secondItems).isEmpty()
    }

    @Test
    fun gsonDeepCopy() {
        val person = Person(firstName = "Johnny", lastName = "Lim", age = 20, firstItems = listOf(Item("ID card")))
        person.secondItems.add(Item("coin"))

        val copied = person.gsonDeepCopy()

        assertThat(copied).isNotSameAs(person)
        assertThat(copied.firstName).isNotSameAs(person.firstName)
        assertThat(copied.lastName).isNotSameAs(person.lastName)
        assertThat(copied.age).isSameAs(person.age)
        assertThat(copied.firstItems).isEqualTo(person.firstItems).isNotSameAs(person.firstItems)
        assertThat(copied.firstItems[0]).isEqualTo(person.firstItems[0]).isNotSameAs(person.firstItems[0])
        assertThat(copied.secondItems).isEqualTo(person.secondItems).isNotSameAs(person.secondItems)
        assertThat(copied.secondItems[0]).isEqualTo(person.secondItems[0]).isNotSameAs(person.secondItems[0])
    }

}

/**
 * Person.
 *
 * @author Johnny Lim
 */
data class Person(
    val firstName: String,
    val lastName: String,
    var age: Int,
    val firstItems: List<Item>,
) {
    val secondItems = mutableListOf<Item>()

    fun gsonDeepCopy(): Person = GSON.fromJson(GSON.toJson(this), Person::class.java)

    companion object {
        private val GSON = Gson()
    }
}

/**
 * Item.
 *
 * @author Johnny Lim
 */
data class Item(
    val name: String,
)
