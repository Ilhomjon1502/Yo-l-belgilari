package Models

import java.io.Serializable

class Belgi : Serializable{
    var id:Int? = null
    var name:String? = null
    var matni:String? = null
    var imagePath:String? = null
    var like = 0
    var category:Int = 0



    constructor()
    constructor(name: String?, matni: String?, imagePath: String?, like: Int, category: Int) {
        this.name = name
        this.matni = matni
        this.imagePath = imagePath
        this.like = like
        this.category = category
    }

    constructor(
        id: Int?,
        name: String?,
        matni: String?,
        imagePath: String?,
        like: Int,
        category: Int
    ) {
        this.id = id
        this.name = name
        this.matni = matni
        this.imagePath = imagePath
        this.like = like
        this.category = category
    }

}