package ekel.app.videoscartoon

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import ekel.app.videoscartoon.adapter.DetailChanelAdapter
import kotlinx.android.synthetic.main.activity_detail_chanel.*

class DetailChanel : AppCompatActivity() {
    private var rv: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_chanel)
        var title = intent.getStringExtra("title")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = title
        supportActionBar!!.elevation = 0f

        adViewDetailChanel.loadAd(AdRequest.Builder().build())

        rv = findViewById(R.id.recycler_detail_chanel) as RecyclerView
        rv!!.layoutManager = LinearLayoutManager(this)
        rv!!.itemAnimator = DefaultItemAnimator()
        getDetailChanel()
    }
    private fun getDetailChanel() {
        var adapter = DetailChanelAdapter(this@DetailChanel, P.produkList!!);
        rv!!.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish ()
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
}
