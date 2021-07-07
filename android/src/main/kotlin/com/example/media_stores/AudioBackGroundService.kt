package com.example.media_store
//
//import android.content.Context
//import android.media.AudioManager
//import android.media.MediaPlayer
//import android.media.session.MediaSession
//import android.media.session.PlaybackState
//import android.net.Uri
//import android.os.Bundle
//import android.support.v4.media.MediaMetadataCompat
//import android.support.v4.media.session.MediaControllerCompat
//import android.support.v4.media.session.MediaSessionCompat
//import android.support.v4.media.session.PlaybackStateCompat
//import io.flutter.plugin.common.MethodChannel
//import java.lang.reflect.Method
//
//
//class AudioBackGroundService (context: Context,method : MethodChannel) {
//
//
//    private lateinit var mSession: MediaSessionCompat
//    val channel: MethodChannel = method
//    val context: Context = context
//
//    fun initializeMediaSession() {
//        mSession = MediaSessionCompat(context, "MediaService")
//        val callBack = MediaCallback(context,channel, mSession)
//        mSession.setCallback(callBack)
//        mSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS)
//        mSession.setActive(true)
//        val mediaControllerCompat : MediaControllerCompat
//        val metadataCompat : MediaMetadataCompat
//        print("------------------------------------------wqlkjdksfbv");
//
//
//    }
//    private val callback = object: MediaSessionCompat.Callback(){}
//
//    inner class MediaControllerCallback: MediaControllerCompat.Callback() {
//        override fun onMetadataChanged(metadata: MediaMetadataCompat?) {
//            super.onMetadataChanged(metadata)
//            println(
//                    "metadata changed to ${metadata?.getString(
//                            MediaMetadataCompat.METADATA_KEY_MEDIA_URI)}")
//        }
//
//        override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
//            super.onPlaybackStateChanged(state)
//            println("state changed to $state")
//        }
//    }
//
//
//fun setState(){
////    val state = PlaybackStateCompat.Builder()
////            .setActions()
////            .setState(PlaybackState.STATE_PLAYING, , 1.0f)
////            .build()
//
////    mSession.setPlaybackState(state)
//    mSession.isActive = true
//}
//
//
//
//
//}
//
//
//
//
//
//
//
//
//
//
//
//class MediaCallback(val context: Context,
//                    val channel:  MethodChannel ,
//                           val mediaSession: MediaSessionCompat,
//                           var mediaPlayer: MediaPlayer? = null) :
//        MediaSessionCompat.Callback() {
//
//    override fun onPlayFromUri(uri: Uri?, extras: Bundle?) {
//        super.onPlayFromUri(uri, extras)
//        println("Playing ${uri.toString()}")
//        channel.invokeMethod("onPlayFrom Uri",{uri})
//    }
//
//    override fun onPlay() {
//        super.onPlay()
//        channel.invokeMethod("onPlay",{})
//        println("onPlay called")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        channel.invokeMethod("onStop",{})
//        println("onStop called")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        channel.invokeMethod("onPause",{})
//        val am = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
//        println("onPause called")
//    }
//}