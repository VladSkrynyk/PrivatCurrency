
package com.example.rolldice.model

data class DiceModel(val sides: List<Int> =
    List(5) { (1..6).random() })
