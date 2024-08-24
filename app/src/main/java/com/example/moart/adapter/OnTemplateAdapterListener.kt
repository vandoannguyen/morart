package com.example.moart.adapter

interface OnTemplateAdapterListener {
    fun onClickLoadMoreTemplates()

//    fun onClickPlayAudio(template: Template?, i2: Int, i9: Int, templateView: TemplateView?)

    fun onClickShowAll(str: String?, i2: Int, z9: Boolean)

    fun onRepeatAudio()

    fun onStopAudio()

//    fun onTemplateSelectedListener(
//        i2: Int, i9: Int, template: Template?, templateItem: TemplateItem?
//    )

//    fun updateNewTemplate(template: Template?, i2: Int)
}