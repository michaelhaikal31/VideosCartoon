package ekel.app.videoscartoon

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.pmui.apps.api.ApiHelper
import com.pmui.apps.api.callback.ApiCallHelper
import com.pmui.apps.api.callback.ApiCallback
import com.pmui.apps.api.service.ApiService
import ekel.app.videoscartoon.NetworkUtils.showNetworkAlertDialog
import ekel.app.videoscartoon.adapter.AllChanelAdapter
import ekel.app.videoscartoon.model.mAllChanel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private var rv: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.elevation = 0f

        MobileAds.initialize(this, getString(R.string.admob_app_id))
        adView.loadAd(AdRequest.Builder().build())
        rv = findViewById(R.id.recycler_all_chanel) as RecyclerView
        rv!!.layoutManager = GridLayoutManager(this, 2)
        rv!!.itemAnimator = DefaultItemAnimator()
        getAllChanel()
    }
    private fun getAllChanel() {
        if (!NetworkUtils.isInternetAvailable(this)) return showNetworkAlertDialog(this)

        MyProgressbar.showProgress(this)
        val observable = ApiHelper
            .getInstance(this)
            .getService(ApiService::class.java, null)
            .allChanel
         ApiCallHelper.call(observable, object : ApiCallback<mAllChanel> {
            override fun onSuccess(response: mAllChanel) {
                MyProgressbar.hideProgress()
                if (response.status != "sukses"){
                    Toast.makeText(this@MainActivity, "Failed Load Data!", Toast.LENGTH_SHORT).show()
                }else{
                    var adapter = AllChanelAdapter(this@MainActivity, response.data!!);
                    rv!!.adapter = adapter
                    adapter!!.notifyDataSetChanged()
                }
            }
            override fun onFailed(throwable: Throwable) {
                MyProgressbar.hideProgress()
                Toast.makeText(this@MainActivity, "Failed Load Data!", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
             R.id.action_rate_us -> {
                 var uri = Uri.parse("https://play.google.com/store/apps/details?id="+packageName)
                 startActivity(Intent(Intent.ACTION_VIEW, uri)) }
             R.id.btnRefresh -> getAllChanel()
             R.id.action_share-> {
                 val intent= Intent()
                 intent.action=Intent.ACTION_SEND
                 intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id="+packageName)
                 intent.type="text/plain"
                 startActivity(Intent.createChooser(intent,"Share To:"))
             }
            else -> super.onOptionsItemSelected(item)
        }
        return true;
    }
}
