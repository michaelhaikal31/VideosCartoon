package ekel.app.videoscartoon.model

class mAllChanel{
    var status: String? = null
    var message: String? = null
    var data: List<AllChanel>? = null
}
class AllChanel{
    var image: String? = null
    var title: String? = null
    var detail : List<DetailChanel>? = null
}
class DetailChanel{
    var image: String? = null
    var title: String? = null
    var url : String? = null
}