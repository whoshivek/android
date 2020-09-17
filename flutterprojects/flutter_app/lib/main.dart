import 'package:flutter/material.dart';

void main(){
  runApp(MyApp());
}

class MyApp extends StatelessWidget{
  void onButton()
  {
    print("HI");
  }
@override
  Widget build(BuildContext context) {
    // TODO: implement build
  var questionsset = ["what is your name","what is your class?"];
  return MaterialApp(home: Scaffold(
    appBar: AppBar(title: Text("appbar"),),
    body: Column (
      children: [
        Text(questionsset[0]),
        RaisedButton(child: Text("answer1"),onPressed: onButton,),
        RaisedButton(child: Text("answer1"),onPressed: null,),
        RaisedButton(child: Text("answer1"),onPressed: null,),

      ],
    ),
  ),);
    throw UnimplementedError();
  }
}