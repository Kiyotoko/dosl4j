package org.dosl

class DoslListing: Iterable<String> {
    private val labels = HashMap<String, Set<String>>()
    private val paths = HashSet<String>()

    operator fun get(label: String): Set<String>? {
        return labels[label]
    }

    operator fun plusAssign(pair: Pair<String, String>) {
        val present = labels[pair.first]
        if (present == null) {
            labels[pair.first] = HashSet<String>(listOf(pair.second))
        } else {
            present.plus(pair.second)
        }
    }

    operator fun plusAssign(path: String) {
        paths += path
    }

    override fun iterator(): Iterator<String> {
        return paths.iterator()
    }
}