package ekel.app.videoscartoon

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context

object MyProgressbar {
    private var p: ProgressDialog? = null

    fun showProgress(c: Context) {
        val progressDialog = ProgressDialog(c)
        progressDialog.setMessage("Loading...")
        this.p = progressDialog
        this.p!!.show();
    }

    fun hideProgress() {
        this.p!!.dismiss();
    }
}
