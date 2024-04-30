package org.dosl

class DoslParseListener: DoslBaseListener() {
    val listing = DoslListing()

    override fun exitRoot(ctx: DoslParser.RootContext) {
        for (entry in ctx.entry()) {
            parseEntry(entry, "", ArrayList())
        }
    }

    private fun parseGroup(ctx: DoslParser.GroupContext, path: String, labels: List<String>) {
        val subLabel = ArrayList(labels)
        if (ctx.label() != null) {
            for (label in ctx.label()) {
                subLabel.add(label.NAME().text)
            }
        }
        var subPath = String(path.toByteArray())
        if (ctx.PATH() != null) {
            subPath += content(ctx.PATH().text)
        }
        for (item in ctx.body().entry()) {
            parseEntry(item, subPath, subLabel)
        }
    }

    private fun parseEntry(ctx: DoslParser.EntryContext, path: String, labels: List<String>) {
        if (ctx.group() != null) {
            parseGroup(ctx.group(), path, labels)
        } else if (ctx.item() != null) {
            val node = ctx.item()
            val name = path + content(node.PATH().text)
            for (label in node.label()) {
                val key = label.NAME().text
                val set = listing.labels[key]
                if (set != null) set.add(name)
                else listing.labels[key] = HashSet(listOf(name))
            }
            for (label in labels) {
                val set = listing.labels[label]
                if (set != null) set.add(name)
                else listing.labels[label] = HashSet(listOf(name))
            }
            listing.paths.add(name)
        }
    }
}