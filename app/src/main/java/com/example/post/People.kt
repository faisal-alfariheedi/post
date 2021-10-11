package com.example.post

import com.google.gson.annotations.SerializedName

class dat {

    var data:ArrayList<People>?=null

    class People {
        @SerializedName("pk")
        var pk:Int?=null
        @SerializedName("name")
        var name: String? = null

        @SerializedName("location")
        var location: String? = null
        constructor(name: String?, location: String?) {
            this.name = name
            this.location = location
        }
    }
}