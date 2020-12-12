package ekel.app.videoscartoon

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkUtils {

    fun isInternetAvailable(ctx: Context): Boolean {
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return (netInfo != null && netInfo.isConnectedOrConnecting
                && cm.activeNetworkInfo!!.isAvailable
                && cm.activeNetworkInfo!!.isConnected)
    }

    fun showNetworkAlertDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setTitle("No Internet")
        builder.setMessage("Internet is not available")

        builder.setPositiveButton("OK"
        ) { dialog, which ->
            dialog.dismiss()
            (context as Activity).finish()
        }
        builder.create().show()
    }
}