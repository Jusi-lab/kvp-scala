object MergeSort {
  def mergeSort(array: Array[Int]): Array[Int] = {
    if (array.length <= 1) {
      array
    } else {
      val mid = array.length / 2
      val left = mergeSort(array.take(mid))
      val right = mergeSort(array.drop(mid))
      merge(left, right)
    }
  }

  def merge(left: Array[Int], right: Array[Int]): Array[Int] = {
    var result = new Array[Int](left.length + right.length)
    var i = 0
    var j = 0
    var k = 0

    while (i < left.length && j < right.length) {
      if (left(i) < right(j)) {
        result(k) = left(i)
        i += 1
      } else {
        result(k) = right(j)
        j += 1
      }
      k += 1
    }

    while (i < left.length) {
      result(k) = left(i)
      i += 1
      k += 1
    }

    while (j < right.length) {
      result(k) = right(j)
      j += 1
      k += 1
    }

    result
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(38, 27, 43, 3, 9, 82, 10)
    val sortedArr = mergeSort(arr)
    println("Sorted array: " + sortedArr.mkString(", "))
  }
}
import scala.collection.mutable.PriorityQueue

object TopNSort {
  def topN(arr: Array[Int], n: Int): Array[Int] = {
    val pq = PriorityQueue.empty[Int](Ordering.Int.reverse)
    for (elem <- arr) {
      pq.enqueue(elem)
      if (pq.size > n) {
        pq.dequeue()
      }
    }
    pq.toArray.sorted.reverse // Sorting is optional, as the PriorityQueue already maintains order
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(38, 27, 43, 3, 9, 82, 10, 55, 22, 17)
    val n = 5
    val topElements = topN(arr, n)
    println("Top " + n + " elements: " + topElements.mkString(", "))
  }
}


