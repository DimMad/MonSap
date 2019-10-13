package test.techtest.moneysapling

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import test.techtest.moneysapling.databinding.ActivityMainBinding
import timber.log.Timber

/**
 * DataBindings and Navigation libraries are used.
 * As such the activity has no other purpose other than being a scaffold
 * for the navigation.
 *
 * With the current requirements no further setup is required.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // DataBindingUtils setContentView<>() to bind to the UI
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        Timber.d("MainActivity ::onCreate called...")
    }
}
