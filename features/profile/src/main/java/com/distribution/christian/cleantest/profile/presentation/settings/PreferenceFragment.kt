package com.distribution.christian.cleantest.profile.presentation.settings

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.distribution.christian.cleantest.core.core.util.ondemand.SplitInstallRequester
import com.distribution.christian.cleantest.core.device.ToolbarLoader
import com.distribution.christian.cleantest.profile.R
import org.koin.android.ext.android.inject


class SettingsFragment : PreferenceFragmentCompat() {

   private val splitInstallRequester: SplitInstallRequester by inject()

   companion object {
      const val TAG = "Settings"
      fun newInstance(): SettingsFragment {
         return SettingsFragment()
      }
   }

   override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
      setPreferencesFromResource(R.xml.preferences, rootKey)
      val screen = this.preferenceScreen

      splitInstallRequester.installedModules()
            .forEach {
               val checkBoxPref = SwitchPreferenceCompat(screen.context)
               checkBoxPref.key = it
               checkBoxPref.title = it
               checkBoxPref.isChecked = true
               checkBoxPref.setOnPreferenceChangeListener { pref, newValue ->
                  val switchVal = newValue as Boolean
                  if (!switchVal) {
                     splitInstallRequester.removeFeature(pref.key)
                  }
                  checkBoxPref.isChecked = switchVal
                  newValue
               }
               screen.addPreference(checkBoxPref)
            }

   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      ToolbarLoader(
            activity as AppCompatActivity?,
            R.string.title_settings,
            true
      )
   }
}