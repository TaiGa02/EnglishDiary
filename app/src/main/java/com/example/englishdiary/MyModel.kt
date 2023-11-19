package com.example.englishdiary

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MyModel :RealmObject() {
    @PrimaryKey
    var id :Long = 0
    var diary :String = ""
    var title :String = ""
    var date : String =""
    var read : Long = 0

}