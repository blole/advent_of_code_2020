data class Bag(val color: String) {
    val parents: MutableSet<Bag> = mutableSetOf()
    lateinit var children: List<Pair<Int, Bag>>

    fun ancestors(seen: Set<Bag> = setOf()): Set<Bag> = seen + parents.flatMap { it.ancestors(seen + it) }
    val requiredChildCount: Int by lazy {
        children.sumBy { (count, child) ->
            count * (child.requiredChildCount + 1)
        }
    }
}

class Day07(io: Kattio) {
    private val bags = mutableMapOf<String, Bag>()
    init {
        for (line in io.lines()) {
            line.destructure("(.+) bags contain (.+)") { parentColor: String, childrenString: String ->
                val parentBag = bags.getOrPut(parentColor) { Bag(parentColor) }
                parentBag.children = childrenString
                    .split(", ")
                    .mapNotNull { childString ->
                        childString.destructure("""(\d+) (.+) bags?\.?""") { count: Int, color: String ->
                            val childBag = bags.getOrPut(color) { Bag(color) }
                            childBag.parents += parentBag
                            Pair(count, childBag)
                        }
                    }
            }!!
        }
    }

    fun a() = bags["shiny gold"]!!.ancestors().size
    fun b() = bags["shiny gold"]!!.requiredChildCount
}
