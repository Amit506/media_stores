package com.example.media_stores

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.io.ByteArrayOutputStream
import java.util.concurrent.Executors


/** MediaStorePlugin */
class MediaStoresPlugin: FlutterPlugin, MethodCallHandler {

  private lateinit var channel : MethodChannel
  private  lateinit var context :Context
  private  lateinit var  sharedSongs: SharedSongs
  private  lateinit var  sharedImages: SharedImages
  private lateinit var  sharedVideos: SharedVideos


//  private val executor = Executors.newCachedThreadPool()

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {

    context=flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "media_store")

    channel.setMethodCallHandler(this)
  }


  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {

    if (call.method == "getSongs") {
      sharedSongs = SharedSongs()

      sharedSongs.getImages(context,result)
    }
    else if(call.method == "getThumbNail"){


      getByteArray(call, result, 0)
    }else if(call.method=="getVideos"){
      sharedVideos = SharedVideos()
      sharedVideos.getVideos(context,result)



    }          else if(call.method=="getImages"){

      sharedImages = SharedImages()
      sharedImages.getImages(context,result)

    }
    else if(call.method=="getVideoThumbnail"){


      getByteArray(call,result,1)
    }
    else if(call.method=="getImagesThumbnail"){


      getByteArray(call,result,2)
    }else if(call.method=="getPath") {
      val id: Long? = call.argument<Int>("id")?.toLong()
      val contentUri: Uri = ContentUris.withAppendedId(
              MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
              id!!)
      result.success(contentUri.path)
    }else if(call.method =="init"){

      result.success("oo")

    }else if(call.method=="getUriPath"){
      val uri: String? = call.argument<String>("uri")?.toString()

      val ur : Uri = Uri.parse(uri)
      val p : String?=getFilePathFromContentUri(ur,context.contentResolver)
      Log.d("temp",p.toString())

      result.success(p)

    }
    else if( call.method =="getStoragePermission") {
 if(!checkPermissionForReadExternalStorage()){
   requestPermissionForReadExternalStorage()


 }
   result.success(true)

    }else if(call.method =="checkStoragePermission") {
      result.success(checkPermissionForReadExternalStorage())




    }


    else if(call.method =="getPalette"){
      val byteArray: ByteArray? = call.argument<ByteArray>("imageByte")

               val paletteHelper = PaletteHelper(context,result)
               paletteHelper.createPaletteAsync(byteArray!!)




    }else{
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }



  private fun getByteArray(call: MethodCall, result: Result, mark: Int) {
    val executor = Executors.newSingleThreadExecutor()
    executor.execute {

      var byteArray: ByteArray? = null
      var handled = false
      var exc: Exception? = null
      try {

        val contentUri: Uri?
        val id: Long? = call.argument<Int>("id")?.toLong()
        val width: Int? = call.argument<Int>("width")?.toInt()
        val height: Int? = call.argument<Int>("height")?.toInt()
        val quality: Int? = call.argument<Int>("quality")?.toInt()
        if (mark == 0) {
          contentUri = ContentUris.withAppendedId(
                  MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                  id!!)
        } else if (mark == 1) {
          contentUri = ContentUris.withAppendedId(
                  MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                  id!!)
        } else {
          contentUri = ContentUris.withAppendedId(
                  MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                  id!!)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
          val bitmap = context.contentResolver.loadThumbnail(
                  contentUri, Size(width ?: 640, height ?: 480), null)
          val stream = ByteArrayOutputStream()
          bitmap.compress(Bitmap.CompressFormat.PNG, quality!!, stream)
          byteArray = stream.toByteArray()
          handled = true
        } else {
          val bitmap = MediaStore.Images.Thumbnails.getThumbnail(
                  context.contentResolver, id,
                  MediaStore.Images.Thumbnails.MINI_KIND,
                  null as BitmapFactory.Options?)
          val stream = ByteArrayOutputStream()
          bitmap.compress(Bitmap.CompressFormat.PNG, quality!!, stream)
          byteArray = stream.toByteArray()

        }
      } catch (e: Exception) {
        handled = false
        exc = e;
      }
      onResult(result, byteArray, handled, exc);
    }
  }
  private fun runOnUiThread(runnable: Runnable) {
    Handler(Looper.getMainLooper()).post(runnable)
  }
  private fun onResult(result: Result, thumbnail: ByteArray?, handled: Boolean, e: Exception?) {
    runOnUiThread(Runnable {
      if (!handled) {
        result.notImplemented()
        return@Runnable
      }
      if (e != null) {
        e.printStackTrace()
        result.error("exception", e.message, null)
        return@Runnable

      }
      result.success(thumbnail)
    })
  }
  private fun getFilePathFromContentUri(contentUri: Uri?,
                                        contentResolver: ContentResolver): String? {
    val filePath: String
    val filePathColumn = arrayOf(MediaStore.MediaColumns.DATA)
    val cursor: Cursor? = contentResolver.query(contentUri!!, filePathColumn, null, null, null)
    cursor?.moveToFirst()
    val columnIndex: Int = cursor!!.getColumnIndex(filePathColumn[0])
    filePath = cursor.getString(columnIndex)
    cursor?.close()
    return filePath
  }

  @Throws(java.lang.Exception::class)
  fun requestPermissionForReadExternalStorage() {
    try {
      ActivityCompat.requestPermissions((context as Activity), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
              0x3)
    } catch (e: java.lang.Exception) {
      e.printStackTrace()
      throw e
    }
  }
  private fun checkPermissionForReadExternalStorage(): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      val result = context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
      return result == PackageManager.PERMISSION_GRANTED
    }
    return false
  }
}



