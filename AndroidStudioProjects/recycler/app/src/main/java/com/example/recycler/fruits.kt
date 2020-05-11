package com.example.recycler

import kotlin.random.Random

data class fruits(
    val  name : String,
val origin : String,
val quantity : Int
) {
    companion object{

        @JvmField
        val fruit_name = arrayOf(
            "a" , "b" ,"c" , "d" , "w" , "s"
        )
        @JvmField
        val origin_name = arrayOf("patna", "bihar" , "qwerty" , "fef" )

          @JvmStatic
        fun fruitarray(n : Int) : ArrayList<fruits>
        {
            val fruitarray = ArrayList<fruits>(n)
for (i in 1..n)
            fruitarray.add(

                fruits(

                    fruit_name[Random.nextInt(6)],
                origin_name[Random.nextInt(4)],
                Random.nextInt(10)*100
                )
            )
            return fruitarray

        }
    }
}