import 'dart:convert';

import 'package:flutter/material.dart';

class Palette {
  Color darkMutedColor;
  Color darkVibrantColorcovariant;
  Color dominantColor;
  Color lightMutedColor;
  Color lightVibrantColor;
  Color mutedColor;
  Color vibrantColor;
  Palette({
    required this.darkMutedColor,
    required this.darkVibrantColorcovariant,
    required this.dominantColor,
    required this.lightMutedColor,
    required this.lightVibrantColor,
    required this.mutedColor,
    required this.vibrantColor,
  });

  Palette copyWith({
    Color? darkMutedColor,
    Color? darkVibrantColorcovariant,
    Color? dominantColor,
    Color? lightMutedColor,
    Color? lightVibrantColor,
    Color? mutedColor,
    Color? vibrantColor,
  }) {
    return Palette(
      darkMutedColor: darkMutedColor ?? this.darkMutedColor,
      darkVibrantColorcovariant:
          darkVibrantColorcovariant ?? this.darkVibrantColorcovariant,
      dominantColor: dominantColor ?? this.dominantColor,
      lightMutedColor: lightMutedColor ?? this.lightMutedColor,
      lightVibrantColor: lightVibrantColor ?? this.lightVibrantColor,
      mutedColor: mutedColor ?? this.mutedColor,
      vibrantColor: vibrantColor ?? this.vibrantColor,
    );
  }

  Map<String, dynamic> toMap() {
    return {
      'darkMutedColor': darkMutedColor.value,
      'darkVibrantColorcovariant': darkVibrantColorcovariant.value,
      'dominantColor': dominantColor.value,
      'lightMutedColor': lightMutedColor.value,
      'lightVibrantColor': lightVibrantColor.value,
      'mutedColor': mutedColor.value,
      'vibrantColor': vibrantColor.value,
    };
  }

  factory Palette.fromMap(Map<String, dynamic> map) {
    return Palette(
      darkMutedColor: Color(map['darkMutedColor'] ?? 0xff0000),
      darkVibrantColorcovariant:
          Color(map['darkVibrantColorcovariant'] ?? 0xff0000),
      dominantColor: Color(map['dominantColor'] ?? 0xff0000),
      lightMutedColor: Color(map['lightMutedColor'] ?? 0xff0000),
      lightVibrantColor: Color(map['lightVibrantColor'] ?? 0xff0000),
      mutedColor: Color(map['mutedColor'] ?? 0xff0000),
      vibrantColor: Color(map['vibrantColor'] ?? 0xff0000),
    );
  }

  String toJson() => json.encode(toMap());

  factory Palette.fromJson(String source) =>
      Palette.fromMap(json.decode(source));

  @override
  String toString() {
    return 'Palette(darkMutedColor: $darkMutedColor, darkVibrantColorcovariant: $darkVibrantColorcovariant, dominantColor: $dominantColor, lightMutedColor: $lightMutedColor, lightVibrantColor: $lightVibrantColor, mutedColor: $mutedColor, vibrantColor: $vibrantColor)';
  }

  @override
  bool operator ==(Object other) {
    if (identical(this, other)) return true;

    return other is Palette &&
        other.darkMutedColor == darkMutedColor &&
        other.darkVibrantColorcovariant == darkVibrantColorcovariant &&
        other.dominantColor == dominantColor &&
        other.lightMutedColor == lightMutedColor &&
        other.lightVibrantColor == lightVibrantColor &&
        other.mutedColor == mutedColor &&
        other.vibrantColor == vibrantColor;
  }

  @override
  int get hashCode {
    return darkMutedColor.hashCode ^
        darkVibrantColorcovariant.hashCode ^
        dominantColor.hashCode ^
        lightMutedColor.hashCode ^
        lightVibrantColor.hashCode ^
        mutedColor.hashCode ^
        vibrantColor.hashCode;
  }
}
