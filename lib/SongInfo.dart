import 'dart:convert';
import 'dart:typed_data';

class SongInfo {
  String? id;
  String? albumId;
  String? artistId;
  String? artist;
  String? album;
  String? isMusic;
  String? isPodCast;
  String? isRingtone;
  String? isAlarm;
  String? isNotification;
  String? title;
  String? displayName;
  String? composer;
  String? year;
  String? track;
  String? duration;
  String? bookmark;
  String? bucketName;
  String? author;
  String? writer;
  String? size;
  String? uri;
  // Uint8List? bitArray;

  SongInfo(
      this.id,
      this.albumId,
      this.artistId,
      this.artist,
      this.album,
      this.isMusic,
      this.isPodCast,
      this.isRingtone,
      this.isAlarm,
      this.isNotification,
      this.title,
      this.displayName,
      this.composer,
      this.year,
      this.track,
      this.duration,
      this.bookmark,
      this.bucketName,
      this.author,
      this.writer,
      this.size,
      this.uri
      // this.bitArray
      );

  SongInfo copyWith({
    String? id,
    String? albumId,
    String? artistId,
    String? artist,
    String? album,
    String? isMusic,
    String? isPodCast,
    String? isRingtone,
    String? isAlarm,
    String? isNotifica,
    String? title,
    String? displayNam,
    String? composer,
    String? year,
    String? track,
    String? duration,
    String? bookmark,
    String? bucketName,
    String? author,
    String? writer,
    String? size,
    String? contentUri,
    // Uint8List? bitArray
  }) {
    return SongInfo(
      id ?? this.id,
      albumId ?? this.albumId,
      artistId ?? this.artistId,
      artist ?? this.artist,
      album ?? this.album,
      isMusic ?? this.isMusic,
      isPodCast ?? this.isPodCast,
      isRingtone ?? this.isRingtone,
      isAlarm ?? this.isAlarm,
      isNotifica ?? this.isNotification,
      title ?? this.title,
      displayNam ?? this.displayName,
      composer ?? this.composer,
      year ?? this.year,
      track ?? this.track,
      duration ?? this.duration,
      bookmark ?? this.bookmark,
      bucketName ?? this.bucketName,
      author ?? this.author,
      writer ?? this.writer,
      size ?? this.size,
      contentUri ?? this.uri,
      // bitArray ?? this.bitArray
    );
  }

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'albumId': albumId,
      'artistId': artistId,
      'artist': artist,
      'album': album,
      'isMusic': isMusic,
      'isPodCast': isPodCast,
      'isRingtone': isRingtone,
      'isAlarm': isAlarm,
      'isNotification': isNotification,
      'title': title,
      'displayName': displayName,
      'composer': composer,
      'year': year,
      'track': track,
      'duration': duration,
      'bookmark': bookmark,
      'bucketName': bucketName,
      'author': author,
      'writer': writer,
      'size': size,
      'uri': uri,
      // 'bitArray': bitArray,
    };
  }

  factory SongInfo.fromMap(Map<String, dynamic> map) {
    return SongInfo(
      map['id'],
      map['albumId'],
      map['artistId'],
      map['artist'],
      map['album'],
      map['isMusic'],
      map['isPodCast'],
      map['isRingtone'],
      map['isAlarm'],
      map['isNotification'],
      map['title'],
      map['displayName'],
      map['composer'],
      map['year'],
      map['track'],
      map['duration'],
      map['bookmark'],
      map['bucketName'],
      map['author'],
      map['writer'],
      map['size'],
      map['uri'],
      // map['bitArray'] != null
      //     ? Uint8List.fromList(List<int>.from(map['bitArray']))
      //     : null,
    );
  }

  String toJson() => json.encode(toMap());

  factory SongInfo.fromJson(String source) =>
      SongInfo.fromMap(json.decode(source));

  @override
  String toString() {
    return 'SongInfo(id: $id, albumId: $albumId, artistId: $artistId, artist: $artist, album: $album, isMusic: $isMusic, isPodCast: $isPodCast, isRingtone: $isRingtone, isAlarm: $isAlarm, isNotification: $isNotification, title: $title, displayName: $displayName, composer: $composer, year: $year, track: $track, duration: $duration, bookmark: $bookmark, bucketName: $bucketName, author: $author, writer: $writer, size: $size, uri: $uri)';
  }

  @override
  bool operator ==(Object other) {
    if (identical(this, other)) return true;

    return other is SongInfo &&
        other.id == id &&
        other.albumId == albumId &&
        other.artistId == artistId &&
        other.artist == artist &&
        other.album == album &&
        other.isMusic == isMusic &&
        other.isPodCast == isPodCast &&
        other.isRingtone == isRingtone &&
        other.isAlarm == isAlarm &&
        other.isNotification == isNotification &&
        other.title == title &&
        other.displayName == displayName &&
        other.composer == composer &&
        other.year == year &&
        other.track == track &&
        other.duration == duration &&
        other.bookmark == bookmark &&
        other.bucketName == bucketName &&
        other.author == author &&
        other.writer == writer &&
        other.size == size &&
        other.uri == uri;
    // other.bitArray == bitArray;
  }

  @override
  int get hashCode {
    return id.hashCode ^
        albumId.hashCode ^
        artistId.hashCode ^
        artist.hashCode ^
        album.hashCode ^
        isMusic.hashCode ^
        isPodCast.hashCode ^
        isRingtone.hashCode ^
        isAlarm.hashCode ^
        isNotification.hashCode ^
        title.hashCode ^
        displayName.hashCode ^
        composer.hashCode ^
        year.hashCode ^
        track.hashCode ^
        duration.hashCode ^
        bookmark.hashCode ^
        bucketName.hashCode ^
        author.hashCode ^
        writer.hashCode ^
        size.hashCode ^
        uri.hashCode;
    // bitArray.hashCode;
  }
}
