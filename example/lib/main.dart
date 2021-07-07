import 'dart:io';
import 'dart:isolate';
import 'dart:typed_data';

import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
// import 'package:just_audio/just_audio.dart';

import 'package:media_stores/SongInfo.dart';
import 'package:media_stores/media_stores.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: HomePage(),
    );
  }
}

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  List<SongInfo> songInfo = [];
  // AudioPlayer audioPlayer = AudioPlayer();
  @override
  void initState() {
    super.initState();

    getSongs();
  }

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
      appBar: AppBar(
        title: Text('Media'),
      ),
      body: GestureDetector(
        onDoubleTap: () async {
          await MediaStores.playback();
        },
        child: Container(
          height: size.height,
          width: size.width,
          child: ListView.builder(
            itemCount: songInfo.length,
            itemBuilder: (_, i) {
              return ListTile(
                subtitle: Text(songInfo[i].size!),
                onTap: () async {
                  // audioPlayer.setUrl(songInfo[i].uri!);
                  // audioPlayer.play();
                  // final c = File.fromUri(Uri.parse(songInfo[i].uri!));
                  // // final c = await MediaStore.bitMap(int.parse(songInfo[i].id!));
                  // print(c.toString());
                },
                leading: FutureBuilder(
                    builder: (context, snapshot) {
                      if (snapshot.hasData) {
                        if (snapshot.data != null) {
                          return CircleAvatar(
                            child: Image.memory(snapshot.data as Uint8List),
                          );
                        } else {
                          return CircleAvatar();
                        }
                      } else {
                        return CircleAvatar();
                      }
                    },
                    future: MediaStores.bitMap(
                      int.parse(songInfo[i].id!),
                    )),
                title: Text(songInfo[i].title!),
              );
            },
          ),
        ),
      ),
    );
  }

  void getSongs() async {
    final songInfo = await MediaStores.getSongs();

    setState(() {
      this.songInfo = songInfo;
    });
    // final g = await MediaStore.getImages();
  }
}