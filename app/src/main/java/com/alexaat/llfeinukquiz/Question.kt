package com.alexaat.llfeinukquiz

enum class Correct{
    A,B,C,D
}

class Question(var question:String, var answers:List<String>, var correct:Correct)