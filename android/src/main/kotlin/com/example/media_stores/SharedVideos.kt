package com.example.media_stores

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.Result

import java.text.SimpleDateFormat
import java.util.*

class SharedVideos(){

    class MyAsyncTask internal constructor(context: Context, result: MethodChannel.Result) : AsyncTask<Int, String, MutableList<HashMap<String, Any?>>>() {
        private val context: Context = context
        private lateinit var projection: Projection

        private val result: MethodChannel.Result = result
        override fun onPreExecute() {

        }

        override fun doInBackground(vararg params: Int?): MutableList<HashMap<String, Any?>>? {
             projection = Projection()
        var format = "yyyy-MM-dd hh:mm:ss";
val formatter =  SimpleDateFormat(format, Locale.getDefault());


            val orderBy = MediaStore.Video.VideoColumns.DATE_ADDED + " DESC"
            val arrayList = mutableListOf<HashMap<String, Any?>>()
            print(context.applicationInfo)
            context.
            contentResolver.query(

                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    projection.getVideoProjection(),
                    null,
                    null,
                    orderBy

            )?.use { cursor ->
                Log.d("temp",cursor.count.toString())
                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                     val columnId: Int = cursor.getColumnIndex(projection.getVideoProjection()[0])
                     val columnDisplayName: Int = cursor.getColumnIndex(projection.getVideoProjection()[1])
                     val columnDuration: Int = cursor.getColumnIndex(projection.getVideoProjection()[2])
                     val columnSize: Int = cursor.getColumnIndex(projection.getVideoProjection()[3])
                     val columnTitle: Int = cursor.getColumnIndex(projection.getVideoProjection()[4])
                     val columnDateAdded: Int = cursor.getColumnIndex(projection.getVideoProjection()[5])
                     val columnVolumeName: Int = cursor.getColumnIndex(projection.getVideoProjection()[6])
                     val columnResolution: Int = cursor.getColumnIndex(projection.getVideoProjection()[7])
                     val columnOrientation: Int = cursor.getColumnIndex(projection.getVideoProjection()[8])
                     val columnArtist: Int = cursor.getColumnIndex(projection.getVideoProjection()[9])
                     val columnAuthor: Int = cursor.getColumnIndex(projection.getVideoProjection()[10])
                     val columnAlbumArtist: Int = cursor.getColumnIndex(projection.getVideoProjection()[11])
                     val columnHeight: Int = cursor.getColumnIndex(projection.getVideoProjection()[12])
                     val columnIsDownload: Int = cursor.getColumnIndex(projection.getVideoProjection()[13])


                     while (cursor.moveToNext()) {


                         val id = cursor.getString(columnId);
                         val displayName = cursor.getString(columnDisplayName)
                         val duration = cursor.getString(columnDuration)
                         val size = cursor.getString(columnSize)
                         val title = cursor.getString(columnTitle)
                         val dateAdded =cursor.getString(columnDateAdded)
                         val volumeName = cursor.getString(columnVolumeName)
                         val resolution = cursor.getString(columnResolution)
                         val orientation = cursor.getString(columnOrientation)
                         val artist = cursor.getString(columnArtist)
                         val author = cursor.getString(columnAuthor)
                         val albumArtist = cursor.getString(columnAlbumArtist)
                         val height = cursor.getString(columnHeight)
                         val isDownload = cursor.getString(columnIsDownload)

                         val contentUri: Uri = ContentUris.withAppendedId(
                                 MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                                 id.toLong()
                         )
                         val sizeInMb : Double = size.toDouble()/(1024*1024)

                         val dateTime:  String= formatter.format( Date(dateAdded.toLong()*1000));

                         val map = hashMapOf<String, Any?>(
                                 "id" to id,
                                 "displayName" to displayName,
                                 "duration" to duration,
                                 "size" to sizeInMb.toString(),
                                 "title" to title,
                                 "dateAdded" to dateTime,
                                 "volumeName" to volumeName,
                                 "resolution" to resolution,
                                 "orientation" to orientation,
                                 "artist" to artist,
                                 "author" to author,
                                 "albumArtist" to albumArtist,
                                 "height" to height,
                                 "isDownload" to isDownload,

                                 "uri" to contentUri.toString()
                         )
                         arrayList.add(map)

                     }
                }else{
                     val columnId: Int = cursor.getColumnIndex(projection.getVideoProjection()[0])
                     val columnDisplayName: Int = cursor.getColumnIndex(projection.getVideoProjection()[1])

                     val columnSize: Int = cursor.getColumnIndex(projection.getVideoProjection()[2])
                     val columnTitle: Int = cursor.getColumnIndex(projection.getVideoProjection()[3])
                     val columnDateAdded: Int = cursor.getColumnIndex(projection.getVideoProjection()[4])

                     val columnHeight: Int = cursor.getColumnIndex(projection.getVideoProjection()[5])


                     while (cursor.moveToNext()) {


                         val id = cursor.getString(columnId);
                         val displayName = cursor.getString(columnDisplayName)

                         val size = cursor.getString(columnSize)
                         val title = cursor.getString(columnTitle)
                         val dateAdded =cursor.getString(columnDateAdded)

                         val height = cursor.getString(columnHeight)


                         val contentUri: Uri = ContentUris.withAppendedId(
                                 MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                                 id.toLong()
                         )
                         val sizeInMb : Double = size.toDouble()/(1024*1024)
                         val dateTime:  String= formatter.format( Date(dateAdded.toLong()))

                         val map = hashMapOf<String, Any?>(
                                 "id" to id,
                                 "displayName" to displayName,

                                 "size" to sizeInMb.toString(),
                                 "title" to title,
                                 "dateAdded" to dateTime,

                                 "height" to height,


                                 "uri" to contentUri.toString()
                         )
                         arrayList.add(map)

                     }
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
    fun getVideos(context:Context,result: Result){
        val task = MyAsyncTask(context = context, result = result
        )
        task.execute()


    }
}