package miyatin.tools

case class Node(
    distance:Double,
    name:String
)

case class Point(
    name:String,
    neighbor:List[Node]
) {
    def ==(other:Point) = this.name == other.name
}

case class Cell(
    distance:Double,
    name:String,
    prev:Option[String]
)

case class Line(
    step:Int,
    astarisk:Cell,
    P:Set[Point],
    cells:Map[String, Cell]
)

class Dijkstra(data:List[Point]) {
    val inf = 100000    // 無限の代わり
    val V   = data      // ダイクストラ終了条件

    def nextStep (step:Int, now:Cell, d:Map[String, Cell], T:Set[Point], P:Set[Point]):List[Line] = {

        val nextd:Map[String, Cell] = Map() ++ (for ((name, cell) <- d) yield {

            // dはdataと名前空間で等価
            val nowPoint = data.find(point => point.name == now.name).get

            (name, 
            cell.prev match {
                case Some(prevName) =>
                    val prevPoint = data.find(point => point.name == now.name).get
                    val prevCell = d.find(t => t._1 == prevPoint.name).get._2
                    prevPoint.neighbor.find(x => x.name == name) match {
                        case Some(node) => 
                            if (cell.distance > node.distance + prevCell.distance) {
                                Cell(node.distance + prevCell.distance, name, Some(now.name))
                            } else {
                                cell
                            }
                        case None => cell
                    }
                case None => nowPoint.neighbor.find(x => x.name == name) match {
                    case Some(p) => Cell(now.distance + p.distance, name, Some(now.name))
                    case None => cell
                }
            })

        }).toList

        val nowPoint = data.find(point => point.name == now.name).get
        val nextT = T.diff(Set(nowPoint))
        val nextP = P.union(Set(nowPoint))

        val nextList = if (!nextT.isEmpty) { 
            val nextNow = nextd.toList.filter{ t => 
                        !nextT.filter(x => x.name == t._1).isEmpty 
                    }.minBy( t => t._2.distance)._2
            nextStep(step+1, nextNow, nextd, nextT, nextP)
        } else {
            Nil
        }
        List(Line(step, now, P, d)) ++ nextList
    }

    def make = {
        val head = data.head
        val d0 = Map(head.name -> Cell(0,head.name,None)) ++ 
                    (for (p <- data.tail) yield (p.name, Cell(inf,p.name,None))).toList

        nextStep(0, Cell(0,head.name,None), d0, data.toSet, Set[Point]())
    }

}