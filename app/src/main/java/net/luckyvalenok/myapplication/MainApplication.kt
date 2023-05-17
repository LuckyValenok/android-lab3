package net.luckyvalenok.myapplication

import android.app.Application
import net.luckyvalenok.myapplication.di.AppComponent
import net.luckyvalenok.myapplication.di.DaggerAppComponent

class MainApplication : Application() {
    val component: AppComponent = DaggerAppComponent.create()
}