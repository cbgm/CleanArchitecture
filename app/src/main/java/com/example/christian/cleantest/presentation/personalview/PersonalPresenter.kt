package com.example.christian.cleantest.presentation.personalview

import javax.inject.Inject

class PersonalPresenter @Inject constructor(): PersonalContract.Presenter {

    lateinit var personalView: PersonalContract.View


    override fun loadPersonalisation() {

    }

    override fun setVIew(v: PersonalContract.View) {
        this.personalView = v
    }

    override fun onBind() {
        this.personalView.showLoading(false)
        this.personalView.showContent(true)
        this.personalView.showError(false)
        loadPersonalisation()
    }

    override fun onUnbind() {
    }
}