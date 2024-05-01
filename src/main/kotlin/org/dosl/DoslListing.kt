package org.dosl

/**
 * Listing that contains all paths and labels of a dosl file.
 *
 * @author karlz
 */
class DoslListing {
    /**
     * Maps all labels to a set consisting of paths that were annotated with this label.
     */
    val labelsToPaths = HashMap<String, MutableSet<String>>()

    /**
     * Maps all paths to a set consisting of labels that this path were annotated with.
     */
    val pathsToLabels = HashMap<String, MutableSet<String>>()

    /**
     * List of paths in the file.
     */
    val paths = ArrayList<String>()

    /**
     * Adds the path in the specified label to the listing. Please note that paths added this way are only added to the
     * labels and not to the paths set.
     */
    fun addPath(label: String, path: String) {
        labelsToPaths.computeIfAbsent(label) { HashSet() }.add(path)
        pathsToLabels.computeIfAbsent(path) { HashSet() }.add(label)
    }

    /**
     * Adds the path to this listing.
     *
     * @param path the path to add
     */
    fun addPath(path: String) {
        paths.add(path)
    }
}