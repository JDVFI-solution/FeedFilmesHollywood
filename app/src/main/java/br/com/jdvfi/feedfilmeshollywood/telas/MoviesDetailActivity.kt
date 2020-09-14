package br.com.jdvfi.feedfilmeshollywood.telas

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.jdvfi.feedfilmeshollywood.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.activity_movies_detail.*

class MoviesDetailActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_detail)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        collapsing_toolbar.title = ""

        appbar.addOnOffsetChangedListener(this@MoviesDetailActivity);

        val requestOptions = RequestOptions();

        Glide.with(this)
                .load("https://static01.nyt.com/images/2019/06/05/arts/blackgodfather1/blackgodfather1-mediumThreeByTwo210.jpg")
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(backdrop);

        actionBar?.title = "Adventures in the Drug Trades"
        actionBar?.subtitle = "“Square Grouper” is a documentary about pot smuggling in South Florida in the 1970s and ’80s."
        actionBar?.elevation = 4.0F


        initWebView("http://www.nytimes.com/2011/04/15/movies/square-grouper-movie-review.html");

    }

    fun initWebView(url: String){
        val myWebView: WebView = findViewById(R.id.webView)
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.setAppCachePath(cacheDir.path)

        settings.setSupportZoom(false)
        settings.builtInZoomControls = false
        settings.displayZoomControls = false
        settings.textZoom = 100


        settings.blockNetworkImage = false
        settings.loadsImagesAutomatically = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            settings.safeBrowsingEnabled = true  // api 26
        }

        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.mediaPlaybackRequiresUserGesture = false


        settings.domStorageEnabled = true
        settings.setSupportMultipleWindows(true)
        settings.loadWithOverviewMode = true
        settings.allowContentAccess = true
        settings.setGeolocationEnabled(true)
        settings.allowUniversalAccessFromFileURLs = true
        settings.allowFileAccess = true


        myWebView.fitsSystemWindows = true

        myWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null)

        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        myWebView.loadUrl(url)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition();
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed();
        return true;
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {

    }

    override
    fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater()?.inflate(R.menu.menu_details, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

       var id: Int = item.itemId;

        if (id == R.id.view_web){
             val i = Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("http://www.nytimes.com/2011/04/15/movies/square-grouper-movie-review.html"));
            startActivity(i);
            return true;
        }

        else if (id == R.id.share){
            try{

                val i = Intent(Intent.ACTION_SEND);
                i.setType("text/plan");
                i.putExtra(Intent.EXTRA_SUBJECT, "Adventures in the Drug Trades");
                val body: String = "http://www.nytimes.com/2011/04/15/movies/square-grouper-movie-review.html \n";
                i.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(i, "Compartilhar com :"));

            }catch (e: Exception){
                Toast.makeText(this, "Hmm.. Sorry, \nNão compartilhou", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
