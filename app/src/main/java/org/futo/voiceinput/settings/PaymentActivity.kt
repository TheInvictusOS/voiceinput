package org.futo.voiceinput.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import org.futo.voiceinput.ui.theme.WhisperVoiceInputTheme

class PaymentActivity : ComponentActivity() {
    private fun updateContent() {
        setContent {
            WhisperVoiceInputTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PaymentScreen(onExit = {
                        finish()
                    })
                }
            }
        }
    }

    private lateinit var viewModel: SettingsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModels<SettingsViewModel>().value

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    updateContent()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.onResume()
    }

    override fun onRestart() {
        super.onRestart()

        viewModel.onResume()
    }
}