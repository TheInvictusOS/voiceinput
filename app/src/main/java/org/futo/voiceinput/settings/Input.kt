package org.futo.voiceinput.settings

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.futo.voiceinput.ENABLE_ANIMATIONS
import org.futo.voiceinput.ENABLE_SOUND
import org.futo.voiceinput.IS_VAD_ENABLED
import org.futo.voiceinput.LANGUAGE_TOGGLES
import org.futo.voiceinput.MANUALLY_SELECT_LANGUAGE
import org.futo.voiceinput.R
import org.futo.voiceinput.Screen
import org.futo.voiceinput.USE_LANGUAGE_SPECIFIC_MODELS

@Composable
@Preview
fun InputScreen(
    settingsViewModel: SettingsViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val (languages, setLanguages) = useDataStore(key = LANGUAGE_TOGGLES, default = setOf("en"))
    
    Screen(stringResource(R.string.input_options)) {
        ScrollableList {
            SettingToggle(
                stringResource(R.string.sounds),
                ENABLE_SOUND,
                default = true,
                subtitle = stringResource(R.string.will_play_a_sound_when_started_cancelled),
                disabledSubtitle = stringResource(R.string.will_not_play_sounds_when_started_cancelled)
            )

            SettingToggle(
                stringResource(R.string.animations),
                ENABLE_ANIMATIONS,
                default = true,
            )

            if(languages.size > 1) {
                SettingToggle("Manually select language", MANUALLY_SELECT_LANGUAGE, default = false)
            }


            Spacer(modifier = Modifier.height(32.dp))

            Tip(stringResource(R.string.stop_on_silence_info))
            SettingToggle(stringResource(R.string.stop_on_silence), IS_VAD_ENABLED, default = true)

            // Option only has effect when English is active and at least one other language
            if(languages.size > 1 && languages.contains("en")) {
                Spacer(modifier = Modifier.height(32.dp))

                Tip(stringResource(R.string.use_language_specific_models_info))
                SettingToggle(
                    stringResource(R.string.use_language_specific_models),
                    USE_LANGUAGE_SPECIFIC_MODELS,
                    default = true
                )
            }
        }
    }
}
