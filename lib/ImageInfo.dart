import 'dart:convert';

class ImageInfo {
  String? id;
  String? displayName;
  String? duration;
  String? size;
  String? title;
  String? dateAdded;
  String? volumeName;
  String? resolution;
  String? orientation;
  String? artist;
  String? author;
  String? albumArtist;
  String? height;
  String? isDownload;
  String? uri;
  ImageInfo({
    this.id,
    this.displayName,
    this.duration,
    this.size,
    this.title,
    this.dateAdded,
    this.volumeName,
    this.resolution,
    this.orientation,
    this.artist,
    this.author,
    this.albumArtist,
    this.height,
    this.isDownload,
    this.uri,
  });

  ImageInfo copyWith({
    String? id,
    String? displayName,
    String? duration,
    String? size,
    String? title,
    String? dateAdded,
    String? volumeName,
    String? resolution,
    String? orientation,
    String? artist,
    String? author,
    String? albumArtist,
    String? height,
    String? isDownload,
    String? uri,
  }) {
    return ImageInfo(
      id: id ?? this.id,
      displayName: displayName ?? this.displayName,
      duration: duration ?? this.duration,
      size: size ?? this.size,
      title: title ?? this.title,
      dateAdded: dateAdded ?? this.dateAdded,
      volumeName: volumeName ?? this.volumeName,
      resolution: resolution ?? this.resolution,
      orientation: orientation ?? this.orientation,
      artist: artist ?? this.artist,
      author: author ?? this.author,
      albumArtist: albumArtist ?? this.albumArtist,
      height: height ?? this.height,
      isDownload: isDownload ?? this.isDownload,
      uri: uri ?? this.uri,
    );
  }

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'displayName': displayName,
      'duration': duration,
      'size': size,
      'title': title,
      'dateAdded': dateAdded,
      'volumeName': volumeName,
      'resolution': resolution,
      'orientation': orientation,
      'artist': artist,
      'author': author,
      'albumArtist': albumArtist,
      'height': height,
      'isDownload': isDownload,
      'uri': uri,
    };
  }

  factory ImageInfo.fromMap(Map<String, dynamic> map) {
    return ImageInfo(
      id: map['id'],
      displayName: map['displayName'],
      duration: map['duration'],
      size: map['size'],
      title: map['title'],
      dateAdded: map['dateAdded'],
      volumeName: map['volumeName'],
      resolution: map['resolution'],
      orientation: map['orientation'],
      artist: map['artist'],
      author: map['author'],
      albumArtist: map['albumArtist'],
      height: map['height'],
      isDownload: map['isDownload'],
      uri: map['uri'],
    );
  }

  String toJson() => json.encode(toMap());

  factory ImageInfo.fromJson(String source) =>
      ImageInfo.fromMap(json.decode(source));

  @override
  String toString() {
    return 'ImageInfo(id: $id, displayName: $displayName, duration: $duration, size: $size, title: $title, dateAdded: $dateAdded, volumeName: $volumeName, resolution: $resolution, orientation: $orientation, artist: $artist, author: $author, albumArtist: $albumArtist, height: $height, isDownload: $isDownload, uri: $uri)';
  }

  @override
  bool operator ==(Object other) {
    if (identical(this, other)) return true;

    return other is ImageInfo &&
        other.id == id &&
        other.displayName == displayName &&
        other.duration == duration &&
        other.size == size &&
        other.title == title &&
        other.dateAdded == dateAdded &&
        other.volumeName == volumeName &&
        other.resolution == resolution &&
        other.orientation == orientation &&
        other.artist == artist &&
        other.author == author &&
        other.albumArtist == albumArtist &&
        other.height == height &&
        other.isDownload == isDownload &&
        other.uri == uri;
  }

  @override
  int get hashCode {
    return id.hashCode ^
        displayName.hashCode ^
        duration.hashCode ^
        size.hashCode ^
        title.hashCode ^
        dateAdded.hashCode ^
        volumeName.hashCode ^
        resolution.hashCode ^
        orientation.hashCode ^
        artist.hashCode ^
        author.hashCode ^
        albumArtist.hashCode ^
        height.hashCode ^
        isDownload.hashCode ^
        uri.hashCode;
  }
}
