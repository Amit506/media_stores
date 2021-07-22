import 'dart:async';
import 'dart:typed_data';

import 'package:flutter/services.dart';
import 'package:media_stores/Palette.dart';
import 'package:media_stores/videoInfo.dart';

import 'ImageInfo.dart';
import 'SongInfo.dart';

class MediaStores {
  static const MethodChannel _channel = const MethodChannel('media_store');

  static Future<List<SongInfo>> getSongs() async {
    final List<dynamic> version = await _channel.invokeMethod('getSongs');
    final map = version
        .cast<Map<dynamic, dynamic>>()
        .map((trace) => trace.cast<String, dynamic>())
        .toList();
    List<SongInfo> songs = map.map((e) => SongInfo.fromMap(e)).toList();

    return songs;
  }

  static Future<Uint8List?> bitMap(int id,
      {int width = 640, int height = 480, int from = 0, quality = 100}) async {
    final bitmap = await _channel.invokeMethod("getThumbNail",
        {"id": id, "height": height, "width": width, "quality": quality});
    if (bitmap == null) {
      return null;
    } else {
      return Uint8List.fromList(List<int>.from(bitmap));
    }
  }

  static Future<Uint8List?> videoBitMap(int id,
      {int width = 640,
      int height = 480,
      int from = 0,
      int quality = 100}) async {
    final bitmap = await _channel.invokeMethod("getVideoThumbnail",
        {"id": id, "height": height, "width": width, "quality": quality});
    if (bitmap == null) {
      return null;
    } else {
      return Uint8List.fromList(List<int>.from(bitmap));
    }
  }

  static Future<List<VideoInfo>> getVideos() async {
    final List<dynamic> result = await _channel.invokeMethod("getVideos");
    final map = result
        .cast<Map<dynamic, dynamic>>()
        .map((trace) => trace.cast<String, dynamic>())
        .toList();

    List<VideoInfo> videos = map.map((e) => VideoInfo.fromMap(e)).toList();
    return videos;
  }

  static Future<List<ImageInfo>> getImages() async {
    final List<dynamic> result = await _channel.invokeMethod("getImages");

    final map = result
        .cast<Map<dynamic, dynamic>>()
        .map((trace) => trace.cast<String, dynamic>())
        .toList();

    List<ImageInfo> images = map.map((e) => ImageInfo.fromMap(e)).toList();

    return images;
  }

  static Future<String> getPath(String uri) async {
    final result = await _channel.invokeMethod("getUriPath", {"uri": uri});

    return result.toString();
  }

  static Future<Palette> getPalete(Uint8List i) async {
    final result = await _channel
        .invokeMethod<Map<dynamic, dynamic>>("getPalette", {"imageByte": i});

    final palette = Palette.fromMap(result!.cast<String, dynamic>());
    return palette;
  }
}
