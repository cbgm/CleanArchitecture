package com.distribution.christian.cleantest.profile.presentation.help.privacy

import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.distribution.christian.cleantest.core.device.ToolbarLoader
import com.distribution.christian.cleantest.profile.R
import com.distribution.christian.cleantest.profile.core.ui.ProfileBaseFragment
import com.distribution.christian.cleantest.profile.presentation.feedback.model.FeedbackFragmentConsistency


class PrivacyFragment : ProfileBaseFragment<FeedbackFragmentConsistency>() {

   private lateinit var webview: WebView

   companion object {
      fun newInstance() = PrivacyFragment()
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_privacy
   }

   override fun initViews(view: View) {
      webview = view.findViewById(R.id.webview)
      webview.loadData("<html>\n" +
                             "<body>\n" +
                             "<h2>Privacy Policy</h2>\n" +
                             "<p>[Individual or Company Name] built the [App Name] app as a [open source | free | freemium | ad-supported | commercial] app. This SERVICE is provided by [Individual or company name] [at no cost] and is intended\n" +
                             "    for use as is.</p>\n" +
                             "<p>This page is used to inform website visitors regarding [my|our] policies with the collection, use, and\n" +
                             "    disclosure of Personal Information if anyone decided to use [my|our] Service.</p>\n" +
                             "<p>If you choose to use [my|our] Service, then you agree to the collection and use of information in\n" +
                             "    relation with this policy. The Personal Information that [I|we] collect are used for providing and\n" +
                             "    improving the Service. [I|We] will not use or share your information with anyone except as described\n" +
                             "    in this Privacy Policy.</p>\n" +
                             "<p>The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions,\n" +
                             "    which is accessible at [App Name], unless otherwise defined in this Privacy Policy.</p>\n" +
                             "\n" +
                             "<p><strong>Information Collection and Use</strong></p>\n" +
                             "<p>For a better experience while using our Service, [I|we] may require you to provide us with certain\n" +
                             "    personally identifiable information, including but not limited to [add whatever else you collect here, e.g. users name | address | location | pictures]. \n" +
                             "\tThe information that [I|we] request is [retained on your device and is not\n" +
                             "    collected by [me|us] in any way]|[will be retained by us and used as described in this privacy policy.</p>\n" +
                             "<p>The app does use third party services that may collect information used to identify you. [You can mention Google services here and link to Google's privacy policy if you want].\n" +
                             "\n" +
                             "<p><strong>Log Data</strong></p>\n" +
                             "<p>[I|We] want to inform you that whenever you use [my|our] Service, in case of an error in the app [I|we] collect\n" +
                             "    data and information (through third party products) on your phone called Log Data. This Log Data\n" +
                             "    may include information such as your devices’s Internet Protocol (“IP”) address, device name,\n" +
                             "    operating system version, configuration of the app when utilising [my|our] Service, the time and date\n" +
                             "    of your use of the Service, and other statistics.</p>\n" +
                             "\n" +
                             "<p><strong>Cookies</strong></p>\n" +
                             "<p>Cookies are files with small amount of data that is commonly used an anonymous unique identifier.\n" +
                             "    These are sent to your browser from the website that you visit and are stored on your devices’s\n" +
                             "    internal memory.</p>\n" +
                             "<p>>!-- Check if this is true for your app, if unsure, just assume that you do use cookies and modify this next line -->This Services does not uses these “cookies” explicitly. However, the app may use third party code\n" +
                             "    and libraries that use “cookies” to collection information and to improve their services. You\n" +
                             "    have the option to either accept or refuse these cookies, and know when a cookie is being sent\n" +
                             "    to your device. If you choose to refuse our cookies, you may not be able to use some portions of\n" +
                             "    this Service.</p>\n" +
                             "\n" +
                             "<p><strong>Service Providers</strong></p> <!-- This part need seem like it's not needed, but if you use any Google services, or any other third party libraries, chances are, you need this. -->\n" +
                             "<p>[I|We] may employ third-party companies and individuals due to the following reasons:</p>\n" +
                             "<ul>\n" +
                             "    <li>To facilitate our Service;</li>\n" +
                             "    <li>To provide the Service on our behalf;</li>\n" +
                             "    <li>To perform Service-related services; or</li>\n" +
                             "    <li>To assist us in analyzing how our Service is used.</li>\n" +
                             "</ul>\n" +
                             "<p>[I|We] want to inform users of this Service that these third parties have access to your Personal\n" +
                             "    Information. The reason is to perform the tasks assigned to them on our behalf. However, they\n" +
                             "    are obligated not to disclose or use the information for any other purpose.</p>\n" +
                             "\n" +
                             "<p><strong>Security</strong></p>\n" +
                             "<p>[I|We] value your trust in providing us your Personal Information, thus we are striving to use\n" +
                             "    commercially acceptable means of protecting it. But remember that no method of transmission over\n" +
                             "    the internet, or method of electronic storage is 100% secure and reliable, and [I|we] cannot\n" +
                             "    guarantee its absolute security.</p>\n" +
                             "\n" +
                             "<p><strong>Links to Other Sites</strong></p>\n" +
                             "<p>This Service may contain links to other sites. If you click on a third-party link, you will be\n" +
                             "    directed to that site. Note that these external sites are not operated by [me|us]. Therefore, I\n" +
                             "    strongly advise you to review the Privacy Policy of these websites. I have no control over, and\n" +
                             "    assume no responsibility for the content, privacy policies, or practices of any third-party\n" +
                             "    sites or services.</p>\n" +
                             "\n" +
                             "<p><strong>Children’s Privacy</strong></p>\n" +
                             "<p>This Services do not address anyone under the age of 13. [I|We] do not knowingly collect personal\n" +
                             "    identifiable information from children under 13. In the case [I|we] discover that a child under 13\n" +
                             "    has provided [me|us] with personal information, [I|we] immediately delete this from our servers. If you\n" +
                             "    are a parent or guardian and you are aware that your child has provided us with personal\n" +
                             "    information, please contact [me|us] so that [I|we] will be able to do necessary actions.</p>\n" +
                             "\n" +
                             "<p><strong>Changes to This Privacy Policy</strong></p>\n" +
                             "<p>[I|We] may update our Privacy Policy from time to time. Thus, you are advised to review this page\n" +
                             "    periodically for any changes. [I|We] will notify you of any changes by posting the new Privacy Policy\n" +
                             "    on this page. These changes are effective immediately, after they are posted on this page.</p>\n" +
                             "\n" +
                             "<p><strong>Contact Us</strong></p>\n" +
                             "<p>If you have any questions or suggestions about [my|our] Privacy Policy, do not hesitate to contact\n" +
                             "    [me|us].</p>\n" +
                             "<p>This Privacy Policy page was created at <a href=\"https://privacypolicytemplate.net\"\n" +
                             "                                              target=\"_blank\">privacypolicytemplate.net</a>.</p>\n" +
                             "</body>\n" +
                             "</html>", "text/html", "UTF-8")

      ToolbarLoader(
            activity as AppCompatActivity?,
            R.string.title_privacy,
            true
      )
   }
}