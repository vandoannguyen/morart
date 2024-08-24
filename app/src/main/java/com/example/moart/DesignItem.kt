package com.example.moart

import android.graphics.drawable.Drawable

class DesignItem(str: String, str2: String, drawable: Drawable) {
    var type: String = ""
    var name: String = ""
    val icon: Drawable

    init {
        this.type = str
        this.name = str2
        this.icon = drawable
    }

    override fun toString(): String {
        return "DesignItem{type='" + this.type + "', name='" + this.name + "', resourceDrawable=" + this.icon + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DesignItem

        if (type != other.type) return false
        if (name != other.name) return false
        if (icon != other.icon) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + icon.hashCode()
        return result
    }
}