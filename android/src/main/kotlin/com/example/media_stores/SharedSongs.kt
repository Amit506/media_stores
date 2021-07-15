package com.example.media_store

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.provider.MediaStore
import android.util.Log
import io.flutter.plugin.common.MethodChannel
import java.util.concurrent.TimeUnit


class SharedSongs
 {

    class MyAsyncTask internal constructor(context: Context, result:MethodChannel.Result) : AsyncTask<Int, String,  MutableList<HashMap<String, Any?>>>() {
        private val context: Context = context
        private val result: MethodChannel.Result = result
        override fun onPreExecute() {

        }

        override fun doInBackground(vararg params: Int?): MutableList<HashMap<String, Any?>>? {
            val orderBy = MediaStore.Audio.AudioColumns.DATE_ADDED + " ASC"
            val arrayList = mutableListOf<HashMap<String, Any?>>()
            val selection = "${MediaStore.Video.Media.DURATION} >= ?"
            val selectionArgs = arrayOf<String>(TimeUnit.MILLISECONDS.convert(2, TimeUnit.MINUTES).toString())


            context.contentResolver.query(

                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    Projection.getAudioProjection(),
                    selection,
                    selectionArgs,
                    orderBy

            )?.use { cursor ->
                val columnId: Int = cursor.getColumnIndex(Projection.getAudioProjection()[0])
                val columnAlbumId: Int = cursor.getColumnIndex(Projection.getAudioProjection()[1])
                val columnartistId: Int = cursor.getColumnIndex(Projection.getAudioProjection()[2])
                val columnartist: Int = cursor.getColumnIndex(Projection.getAudioProjection()[3])
                val columnalbum: Int = cursor.getColumnIndex(Projection.getAudioProjection()[4])
                val columnisMusic: Int = cursor.getColumnIndex(Projection.getAudioProjection()[5])
                val columnisPodcast: Int = cursor.getColumnIndex(Projection.getAudioProjection()[6])
                val columnisRingTone: Int = cursor.getColumnIndex(Projection.getAudioProjection()[7])
                val columnisAlarm: Int = cursor.getColumnIndex(Projection.getAudioProjection()[8])
                val columnisNotification: Int = cursor.getColumnIndex(Projection.getAudioProjection()[9])
                val columntitle: Int = cursor.getColumnIndex(Projection.getAudioProjection()[10])
                val columndisplayName: Int = cursor.getColumnIndex(Projection.getAudioProjection()[11])
                val columncomposer: Int = cursor.getColumnIndex(Projection.getAudioProjection()[12])
                val columnyear: Int = cursor.getColumnIndex(Projection.getAudioProjection()[13])
                val columntrack: Int = cursor.getColumnIndex(Projection.getAudioProjection()[14])
                val columnduration: Int = cursor.getColumnIndex(Projection.getAudioProjection()[15])
                val columnbookmark: Int = cursor.getColumnIndex(Projection.getAudioProjection()[16])
                val columnbucketDisplay: Int = cursor.getColumnIndex(Projection.getAudioProjection()[17])
                val columnauthor: Int = cursor.getColumnIndex(Projection.getAudioProjection()[18])
                val columnwriter: Int = cursor.getColumnIndex(Projection.getAudioProjection()[19])
                val columnsize: Int = cursor.getColumnIndex(Projection.getAudioProjection()[20])
//                val x : Int =cursor.getColumnIndex(Projection.getAudioProjection()[21])
                while (cursor.moveToNext()) {

                    val id = cursor.getString(columnId);
                    val albumId = cursor.getString(columnAlbumId)
                    val artistId = cursor.getString(columnartistId)
                    val artist = cursor.getString(columnartist)
                    val album = cursor.getString(columnalbum)
                    val isMusic = cursor.getString(columnisMusic)
                    val isPodCast = cursor.getString(columnisPodcast)
                    val isRingtone = cursor.getString(columnisRingTone)
                    val isAlarm = cursor.getString(columnisAlarm)
                    val isNotification = cursor.getString(columnisNotification)
                    val title = cursor.getString(columntitle)
                    val displayName = cursor.getString(columndisplayName)
                    val composer = cursor.getString(columncomposer)
                    val year = cursor.getString(columnyear)
                    val track = cursor.getString(columntrack)
                    val duration = cursor.getString(columnduration)
                    val bookmark = cursor.getString(columnbookmark)
                    val bucketName = cursor.getString(columnbucketDisplay)
                    val author = cursor.getString(columnauthor)
                    val writer = cursor.getString(columnwriter)
                    val size = cursor.getString(columnsize)
                    val contentUri: Uri = ContentUris.withAppendedId(
                            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                            id.toLong()
                    )

                    val map = hashMapOf<String, Any?>(
                            "id" to id,
                            "albumId" to albumId,
                            "artistId" to artistId,
                            "artist" to artist,
                            "album" to album,
                            "isMusic" to isMusic,
                            "isPodCast" to isPodCast,
                            "isRingtone" to isRingtone,
                            "isAlarm" to isAlarm,
                            "isNotification" to isNotification,
                            "title" to title,
                            "displayName" to displayName,
                            "composer" to composer,
                            "year" to year,
                            "track" to track,
                            "duration" to duration,
                            "bookmark" to bookmark,
                            "bucketName" to bucketName,
                            "author" to author,
                            "writer" to writer,
                            "size" to size,
                            "uri" to contentUri.toString()

                    )
                    arrayList.add(map)

                }
            }




            return arrayList

        }

        override fun onPostExecute(result: MutableList<HashMap<String, Any?>>?) {

           this.result.success(result)


        }

        override fun onProgressUpdate(vararg text: String?) {


        }
    }
    fun getImages(context: Context,result: MethodChannel.Result) {
        val task = MyAsyncTask(context = context, result = result
        )
        task.execute()


    }
}