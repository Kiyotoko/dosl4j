package org.dosl

/**
 * The dosl parse listener for parsing the tokens to a listing object.
 *
 * @author karlz
 */
class DoslParseListener: DoslBaseListener() {
    /**
     * The listing object in which the content is parsed.
     */
    val listing = DoslListing()

    override fun exitRoot(ctx: DoslParser.RootContext) {
        for (entry in ctx.entry()) {
            parseEntry(entry, "", ArrayList())
        }
    }

    /**
     * Parses a generic entry node. An entry can  be a group or an item.
     */
    private fun parseEntry(context: DoslParser.EntryContext, path: String, labels: List<String>) {
        if (context.group() != null) { // Context is a group
            parseGroup(context.group(), path, labels)
        } else if (context.item() != null) { // Context is an item
            parseItem(context.item(), path, labels)
        }
    }

    /**
     * Parses a group node. A group wraps a list of groups or items.
     */
    private fun parseGroup(context: DoslParser.GroupContext, path: String, labels: List<String>) {
        val subLabel = ArrayList(labels) // Copy labels
        if (context.label() != null) {
            for (label in context.label()) {
                subLabel.add(label.NAME().text)
            }
        }
        var subPath = String(path.toByteArray()) // Copy path
        if (context.PATH() != null) {
            subPath += content(context.PATH().text)
        }
        for (item in context.body().entry()) { // Parse all entries
            parseEntry(item, subPath, subLabel)
        }
    }

    /**
     * Parses an item node. An item consists of any number of labels and a path.
     */
    private fun parseItem(context: DoslParser.ItemContext, path: String, labels: List<String>) {
        val fullPath = path + content(context.PATH().text) // Create  full path
        for (ctx in context.label()) { // Add path to own labels
            listing.addPath(ctx.NAME().text, fullPath)
        }
        for (label in labels) { // Add path to group labels
            listing.addPath(label, fullPath)
        }
        listing.addPath(fullPath) // Add path to set
    }
}