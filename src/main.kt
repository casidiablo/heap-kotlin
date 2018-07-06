fun main(args: Array<String>) {
    val heap = Heap<Int>(max = true)
            .insertAll(8)
            .insert(3)
            .insert(32)
            .insert(12)
            .insert(4)
            .insert(5)
            .insert(8)
            .insert(14)
            .insert(9)
            .insert(17)
            .insert(25)
            .insert(29)
    println(heap)

    // every extraction should be the max of the heap
    var lastExtract = heap.extract()
    while (heap.hasMore()) {
        val head = heap.extract()
        if (lastExtract < head) {
            throw IllegalStateException("Oh my, it does not work")
        } else {
            lastExtract = head
        }
    }
}