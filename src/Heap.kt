class Heap<T : Comparable<T>>(val max: Boolean = true) {
    private val tree = mutableListOf<T>()

    fun insert(t: T): Heap<T> {
        tree.add(t)
        upHeap(tree.size - 1)
        return this
    }

    fun insertAll(vararg ts: T): Heap<T> {
        ts.forEach { insert(it) }
        return this
    }

    fun extract(): T {
        val head = tree.first()

        // put last in the head and remove it from the tail
        tree[0] = tree.last()
        tree.removeAt(tree.size - 1)

        downHeap(0)

        return head
    }

    private fun upHeap(idx: Int) {
        if (idx == 0) {
            return
        }

        val parentIdx = parentIndex(idx)
        if (max && tree[parentIdx] < tree[idx] || !max && tree[parentIdx] > tree[idx]) {
            tree.swap(parentIdx, idx)
        }
        upHeap(parentIdx)
    }

    private fun downHeap(idx: Int) {
        val leftIdx = 2 * idx + 1
        val rightIdx = 2 * idx + 2

        val hasBothChildren = leftIdx < tree.size && rightIdx < tree.size
        val hasOnlyLeftChild = leftIdx < tree.size
        var indexToSwap = -1
        when {
            hasBothChildren -> {
                if (max && (tree[idx] < tree[leftIdx] || tree[idx] < tree[rightIdx])) {
                    indexToSwap = if (tree[leftIdx] > tree[rightIdx]) leftIdx else rightIdx
                }
                if (!max && (tree[idx] > tree[leftIdx] || tree[idx] > tree[rightIdx])) {
                    indexToSwap = if (tree[leftIdx] < tree[rightIdx]) leftIdx else rightIdx
                }
            }
            hasOnlyLeftChild -> {
                if ((max && tree[idx] < tree[leftIdx]) || (!max && tree[idx] > tree[leftIdx])) {
                    indexToSwap = leftIdx
                }
            }
        }

        if (indexToSwap != -1) {
            tree.swap(idx, indexToSwap)
            downHeap(indexToSwap)
        }
    }

    private fun parentIndex(idx: Int): Int {
        return Math.floor((idx.toDouble() - 1.0) / 2).toInt()
    }

    override fun toString(): String {
        return tree.toString()
    }

    fun hasMore(): Boolean = tree.isNotEmpty()
}

private fun <E> MutableList<E>.swap(lhsIdx: Int, rhsIdx: Int) {
    this[lhsIdx] = this[rhsIdx].also {
        this[rhsIdx] = this[lhsIdx]
    }
}
