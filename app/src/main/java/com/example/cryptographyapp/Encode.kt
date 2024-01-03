package com.example.cryptographyapp

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

class Encode {
    //we can even use the built-in encoder but for some emulator it doesn't work
//    companion object {
//        @RequiresApi(Build.VERSION_CODES.O)
//        fun encode(temp:String):String{
//            // Encode into Base64 format
//            val basicBase64Format =
//                Base64.getEncoder()
//                    .encodeToString(temp.toByteArray(Charsets.UTF_8))
//
//            return basicBase64Format
//        }
//    }

    companion object {
        fun encode(s: String): String {
            // create a string to add in the initial
            // binary code for extra security
            val ini = "11111111"
            
            // Counter variable
            var cu = 0

            // Array to store ASCII values of characters
            val arr = IntArray(11111111)

            // iterate through the string
            for (i in s.indices) {
                // put the ASCII value of
                // each character in the array
                arr[i] = s[i].toInt()
                cu++
            }
            // Resultant string
            var res = ""

            // Array to store binary representation
            val bin = IntArray(111)
            var idx = 0

            // Loop through the array of ASCII values
            for (i1 in 0 until cu) {

                // get the ASCII value at position
                // i1 from the first array
                var temp = arr[i1]

                // Initialize the binary array with 0 values
                for (j in 0 until cu) bin[j] = 0
                idx = 0

                // Convert ASCII value to binary representation
                while (temp > 0) {
                    bin[idx++] = temp % 2
                    temp /= 2
                }
                // Convert binary representation to a string of size 7
                var dig = ""
                var temps: String

                // run a loop of size 7
                for (j in 0 until 7) {
                    temps = bin[j].toString()
                    dig += temps
                }
                var revs = ""

                // reverse the string
                for (j in dig.length - 1 downTo 0) {
                    val ca = dig[j]
                    revs = revs.plus(ca)
                }
                // Concatenate the reversed string to the result
                res += revs
            }
            // add the extra string to the binary code
            res = ini.plus(res)

            // return the encrypted code
            return res
        }
    }
}

// eg:
//1) ASCII values for "Hello":
// H: 72
// e: 101
// l: 108 (twice)
// o: 111

// 2)Binary representations (reversed) and concatenation:
// H: 0001001
// e: 1010011
// l (first occurrence): 0011011
// l (second occurrence): 0011011
// o: 1111011

//3) Concatenating the reversed binary representations:
// "0001001" + "1010011" + "0011011" + "0011011" + "1111011"

//4) Concatenating the initial binary code "11111111" to the result:
// "11111111" + "0001001" + "1010011" + "0011011" + "0011011" + "1111011"

// The correct output string is "11111111110000101100110011001101111"
