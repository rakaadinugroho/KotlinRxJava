package com.rakaadinugroho.kotlinrxjava.models

/**
 * Created by Raka on 11/26/17.
 */
// result generated from /json

data class Base(val total: Number?, val per_page: Number?, val current_page: Number?, val last_page: Number?, val next_page_url: String?, val prev_page_url: Any?, val from: Number?, val to: Number?, val data: List<Data>?)

data class Data(val id: Number?, val slug: String?, val news_date: String?, val published_at: String?, val featured: Number?, val title: String?, val description: String?, val content: String?, val image: String?, val image_type: Number?, val image_info: Image_info?, val created_at: String?, val updated_at: String?, val attachment_file: String?, val match_id: Any?, val link: String?, val previous_news_slug: String?, val next_news_slug: String?, val image_list: String?, val image_detail: String?, val image_big: String?, val image_home: String?, val image_news: String?)

data class Image_info(val url_path: String?, val mime_type: String?, val size: Number?)
