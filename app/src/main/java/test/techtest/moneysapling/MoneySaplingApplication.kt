package test.techtest.moneysapling

import android.app.Application
import timber.log.Timber

@Suppress("unused")
class MoneySaplingApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}