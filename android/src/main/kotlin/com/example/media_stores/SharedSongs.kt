package com.example.media_stores

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.provider.MediaStore
import android.util.Log

import io.flutter.plugin.common.MethodChannel
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap


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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    val columnId: Int = cursor.getColumnIndex(Projection.getAudioProjection()[0])
                    val columnAlbumId: Int = cursor.getColumnIndex(Projection.getAudioProjection()[1])
                    val columnArtistId: Int = cursor.getColumnIndex(Projection.getAudioProjection()[2])
                    val columnArtist: Int = cursor.getColumnIndex(Projection.getAudioProjection()[3])
                    val columnAlbum: Int = cursor.getColumnIndex(Projection.getAudioProjection()[4])
                    val columnIsMusic: Int = cursor.getColumnIndex(Projection.getAudioProjection()[5])
                    val columnIsPodcast: Int = cursor.getColumnIndex(Projection.getAudioProjection()[6])
                    val columnIsRingTone: Int = cursor.getColumnIndex(Projection.getAudioProjection()[7])
                    val columnIsAlarm: Int = cursor.getColumnIndex(Projection.getAudioProjection()[8])
                    val columnIsNotification: Int = cursor.getColumnIndex(Projection.getAudioProjection()[9])
                    val columnTitle: Int = cursor.getColumnIndex(Projection.getAudioProjection()[10])
                    val columnDisplayName: Int = cursor.getColumnIndex(Projection.getAudioProjection()[11])
                    val columnComposer: Int = cursor.getColumnIndex(Projection.getAudioProjection()[12])
                    val columnYear: Int = cursor.getColumnIndex(Projection.getAudioProjection()[13])
                    val columnTrack: Int = cursor.getColumnIndex(Projection.getAudioProjection()[14])
                    val columnDuration: Int = cursor.getColumnIndex(Projection.getAudioProjection()[15])
                    val columnBookmark: Int = cursor.getColumnIndex(Projection.getAudioProjection()[16])
                    val columnBucketDisplay: Int = cursor.getColumnIndex(Projection.getAudioProjection()[17])

                    val columnAuthor: Int = cursor.getColumnIndex(Projection.getAudioProjection()[18])
                    val columnWriter: Int = cursor.getColumnIndex(Projection.getAudioProjection()[19])
                    val columnSize: Int = cursor.getColumnIndex(Projection.getAudioProjection()[20])
//                val x : Int =cursor.getColumnIndex(Projection.getAudioProjection()[21])
                    while (cursor.moveToNext()) {

                        val id = cursor.getString(columnId);
                        val albumId = cursor.getString(columnAlbumId)
                        val artistId = cursor.getString(columnArtistId)
                        val artist = cursor.getString(columnArtist)
                        val album = cursor.getString(columnAlbum)
                        val isMusic = cursor.getString(columnIsMusic)
                        val isPodCast = cursor.getString(columnIsPodcast)
                        val isRingtone = cursor.getString(columnIsRingTone)
                        val isAlarm = cursor.getString(columnIsAlarm)
                        val isNotification = cursor.getString(columnIsNotification)
                        val title = cursor.getString(columnTitle)
                        val displayName = cursor.getString(columnDisplayName)
                        val composer = cursor.getString(columnComposer)
                        val year = cursor.getString(columnYear)
                        val track = cursor.getString(columnTrack)
                        val duration = cursor.getString(columnDuration)
                        val bookmark = cursor.getString(columnBookmark)
                        val bucketName = cursor.getString(columnBucketDisplay)
                        val author = cursor.getString(columnAuthor)
                        val writer = cursor.getString(columnWriter)
                        val size = cursor.getString(columnSize)
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
                }else{
                    Log.d("temp","-----------------------------------");
                    val columnId: Int = cursor.getColumnIndex(Projection.getAudioProjection()[0])
                    val columnAlbumId: Int = cursor.getColumnIndex(Projection.getAudioProjection()[1])
                    val columnArtistId: Int = cursor.getColumnIndex(Projection.getAudioProjection()[2])

                    val columnIsMusic: Int = cursor.getColumnIndex(Projection.getAudioProjection()[3])
                    val columnIsPodcast: Int = cursor.getColumnIndex(Projection.getAudioProjection()[4])
                    val columnIsRingTone: Int = cursor.getColumnIndex(Projection.getAudioProjection()[5])
                    val columnIsAlarm: Int = cursor.getColumnIndex(Projection.getAudioProjection()[6])
                    val columnIsNotification: Int = cursor.getColumnIndex(Projection.getAudioProjection()[7])
                    val columnTitle: Int = cursor.getColumnIndex(Projection.getAudioProjection()[8])
                    val columnDisplayName: Int = cursor.getColumnIndex(Projection.getAudioProjection()[9])

                    val columnYear: Int = cursor.getColumnIndex(Projection.getAudioProjection()[10])
                    val columnTrack: Int = cursor.getColumnIndex(Projection.getAudioProjection()[11])

                    val columnBookmark: Int = cursor.getColumnIndex(Projection.getAudioProjection()[12])
                    val columnSize: Int = cursor.getColumnIndex(Projection.getAudioProjection()[13])
//                val x : Int =cursor.getColumnIndex(Projection.getAudioProjection()[21])
                    Log.d("temp", "-----------------------------------$columnId");
                    while (cursor.moveToNext()) {

                        val id = cursor.getString(columnId);
                        val albumId = cursor.getString(columnAlbumId)
                        val artistId = cursor.getString(columnArtistId)

                        val isMusic = cursor.getString(columnIsMusic)
                        val isPodCast = cursor.getString(columnIsPodcast)
                        val isRingtone = cursor.getString(columnIsRingTone)
                        val isAlarm = cursor.getString(columnIsAlarm)
                        val isNotification = cursor.getString(columnIsNotification)
                        val title = cursor.getString(columnTitle)
                        val displayName = cursor.getString(columnDisplayName)

                        val year = cursor.getString(columnYear)
                        val track = cursor.getString(columnTrack)

                        val bookmark = cursor.getString(columnBookmark)

                        val size = cursor.getString(columnSize)
                        val contentUri: Uri = ContentUris.withAppendedId(
                                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                                id.toLong()
                        )
                        Log.d("temp",id);
                        val map = hashMapOf<String, Any?>(
                                "id" to id,
                                "albumId" to albumId,
                                "artistId" to artistId,

                                "isMusic" to isMusic,
                                "isPodCast" to isPodCast,
                                "isRingtone" to isRingtone,
                                "isAlarm" to isAlarm,
                                "isNotification" to isNotification,
                                "title" to title,
                                "displayName" to displayName,

                                "year" to year,
                                "track" to track,

                                "bookmark" to bookmark,


                                "size" to size,
                                "uri" to contentUri.toString()

                        )
                        arrayList.add(map)

                        Log.d("temo",arrayList.toString());
                    }
                }

            }

Log.d("temo",arrayList.toString());


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