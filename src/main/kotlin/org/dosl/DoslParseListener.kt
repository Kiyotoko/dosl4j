package org.dosl

import DoslBaseListener

class DoslParseListener: DoslBaseListener() {
    val listing = DoslListing()

    override fun exitRoot(ctx: DoslParser.RootContext) {
        for (item in ctx.item()) {
            parseItem(item, "", ArrayList())
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
        if (ctx.path() != null) {
            subPath += content(ctx.path().PATH().text)
        }
        for (item in ctx.body().item()) {
            parseItem(item, subPath, subLabel)
        }
    }

    private fun parseItem(ctx: DoslParser.ItemContext, path: String, labels: List<String>) {
        if (ctx.group() != null) {
            parseGroup(ctx.group(), path, labels)
        } else if (ctx.path() != null) {
            val node = ctx.path()
            val name = path + content(node.PATH().text)
            for (label in node.label()) {
                listing += Pair(label.NAME().text, name)
            }
            for (label in labels) {
                listing += Pair(label, name)
            }
            listing += name
        }
    }
}