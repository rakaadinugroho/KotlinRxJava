package com.rakaadinugroho.kotlinrxjava

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.rakaadinugroho.kotlinrxjava.adapter.NewsAdapter
import com.rakaadinugroho.kotlinrxjava.models.Base
import com.rakaadinugroho.kotlinrxjava.models.Data
import com.rakaadinugroho.kotlinrxjava.network.RequestInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val BASE_URL    = "https://liga-indonesia.id/api/"
    private var compositeDisposable: CompositeDisposable? = null
    private var newsList: List<Data>? = null
    private var adapter: NewsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        compositeDisposable = CompositeDisposable()
        initViews()
        loadNews()
    }

    private fun loadNews() {
        val requestData = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestInterface::class.java)

        compositeDisposable?.add(requestData.getNews("1","10","desc")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleData, this::handleError)
        )
    }
    private fun handleData(base: Base){
        newsList    = base.data;
        adapter = NewsAdapter(newsList!!)

        news_list.adapter   = adapter
    }

    private fun handleError(throwable: Throwable){
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.dispose()
    }
    private fun initViews() {
        news_list.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager   = LinearLayoutManager(this)
        news_list.layoutManager = layoutManager
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
