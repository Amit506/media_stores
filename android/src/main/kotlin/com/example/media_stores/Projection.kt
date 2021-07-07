package com.example.media_store

import android.provider.MediaStore

class Projection {
    companion object {
        fun getAudioProjection():Array<String>{
            return arrayOf(
            MediaStore.Audio.Media._ID,  // row id
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.IS_MUSIC,
            MediaStore.Audio.Media.IS_PODCAST,
            MediaStore.Audio.Media.IS_RINGTONE,
            MediaStore.Audio.Media.IS_ALARM,
            MediaStore.Audio.Media.IS_NOTIFICATION,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.COMPOSER,
            MediaStore.Audio.Media.YEAR,
            MediaStore.Audio.Media.TRACK,
            MediaStore.Audio.Media.DURATION,  // duration of the audio file in ms
            MediaStore.Audio.Media.BOOKMARK,  // position, in ms, where playback was at in last stopped
            MediaStore.Audio.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Audio.Media.AUTHOR,

            MediaStore.Audio.Media.WRITER,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.GENRE


            )
        }
    }

        fun getVideoProjection():Array<String>{
            return  arrayOf(
                    MediaStore.Video.Media._ID,
                    MediaStore.Video.Media.DISPLAY_NAME,
                    MediaStore.Video.Media.DURATION,
                    MediaStore.Video.Media.SIZE,
                    MediaStore.Video.Media.TITLE,
                    MediaStore.Video.Media.DATE_ADDED,
                    MediaStore.Video.Media.VOLUME_NAME,
                    MediaStore.Video.Media.RESOLUTION,
                    MediaStore.Video.Media.ORIENTATION,
                    MediaStore.Video.Media.ARTIST,
                    MediaStore.Video.Media.AUTHOR,
                    MediaStore.Video.Media.ALBUM_ARTIST,
                    MediaStore.Video.Media.HEIGHT,
                    MediaStore.Video.Media.IS_DOWNLOAD
            )

        }
    fun getImagesProjection():Array<String>{
        return arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DURATION,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media.TITLE,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media.VOLUME_NAME,
                MediaStore.Images.Media.RESOLUTION,
                MediaStore.Images.Media.ORIENTATION,
                MediaStore.Images.Media.ARTIST,
                MediaStore.Images.Media.AUTHOR,
                MediaStore.Images.Media.ALBUM_ARTIST,
                MediaStore.Images.Media.HEIGHT,
                MediaStore.Images.Media.IS_DOWNLOAD,
                MediaStore.Images.Media.DATE_TAKEN

        )
    }

}