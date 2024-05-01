/*
 * MIT License
 *
 * Copyright (c) 2024 Karl Zschiebsch
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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