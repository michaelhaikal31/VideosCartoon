package ekel.app.videoscartoon

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.youtube.player.*
import kotlinx.android.synthetic.main.activity_contain_chanel.*
import java.util.regex.Pattern


class ContainChanel : YouTubeBaseActivity() {
    lateinit var mInterstitial : InterstitialAd
    var id_url: String?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contain_chanel)
        id_url = intent.getStringExtra("url").toString()
        var title = intent.getStringExtra("title").toString()

        adViewContainChanel.loadAd(AdRequest.Builder().build())
        mInterstitial = InterstitialAd(this)
        mInterstitial.adUnitId = getString(R.string.interstitial_contain_chanel)
        mInterstitial.loadAd(AdRequest.Builder().build())

        initialize(getYoutubeFromUrl(id_url!!)!!)
//        var video_contain = findViewById<YouTubePlayerView>(R.id.video_contain)
//        video_contain.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//            override fun onReady(youTubePlayer: YouTubePlayer) {
//                youTubePlayer.loadVideo(id_url!!, 0f)
//            }
//        })
    }

    private fun initialize(videoId: String){
//        var youtubeFragment = supportFragmentManager.findFragmentById(R.id.video_contain) as YouTubePlayerSupportFragment
        video_contain.initialize(getString(R.string.key_api_youtube), object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
//                if(p1 == null ) return
//                if(p2){
//                    p1.play()
//                }else{
//                    p1.cueVideo(videoId)
//                    p1.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
//                }
                p1!!.loadVideo(videoId)
                p1!!.play()

            }

            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Log.i("YouTube faillure", "Provider "+p0.toString()+" YouTubeInitializationResult "+p1!!.toString())
                // TODO Auto-generated method stub
               Toast.makeText(this@ContainChanel,"Tunggu Sesaat Lagi ... ", Toast.LENGTH_SHORT).show()
                try {
                    var intent = YouTubeStandalonePlayer.createVideoIntent(this@ContainChanel, getString(R.string.key_api_youtube), getYoutubeFromUrl(id_url!!)!!)
                    startActivity(intent);
                    finish(); //to exit current Activity in which YouTubeFragment is not working
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        })
    }
    fun getYoutubeFromUrl(inUrl: String): String?{
        if(inUrl.toLowerCase().contains("youtu.be")){
            return inUrl.substring(inUrl.lastIndexOf("/")+1)
        }
        val pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"
        val compiledPattern = Pattern.compile(pattern)
        val matcher = compiledPattern.matcher(inUrl)
        return  if (matcher.find()){
            matcher.group()
        }else null
    }

    override fun onDestroy() {
        mInterstitial.show()
        super.onDestroy()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish ()
        }
        return super.onOptionsItemSelected(item)
    }
}
