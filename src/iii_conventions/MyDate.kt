package iii_conventions

import com.sun.org.apache.xpath.internal.operations.Bool

data class MyDate (val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)
operator fun MyDate.plus(timeInterval: TimeInterval) = addTimeIntervals(timeInterval, 1)
operator fun MyDate.plus(bunchOfTimeInterval: BunchOfTimeInterval) = addTimeIntervals(bunchOfTimeInterval.timeInterval, bunchOfTimeInterval.number)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}
operator fun TimeInterval.times(number: Int) = BunchOfTimeInterval(this, number)

class DateRange(override val start: MyDate,
        override val endInclusive: MyDate) :
        ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = DateIterator(this)

    override fun contains(value: MyDate): Boolean = start <= value && value <= endInclusive
}

class DateIterator(dateRange: DateRange) : Iterator<MyDate> {
    var current: MyDate = dateRange.start
    var endInclusive: MyDate = dateRange.endInclusive

    override fun hasNext(): Boolean = current <= endInclusive

    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }

}

class BunchOfTimeInterval(val timeInterval: TimeInterval, val number: Int)
