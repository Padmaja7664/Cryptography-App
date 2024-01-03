package com.example.cryptographyapp

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.math.pow

class Decode {

//    companion object{
//        @RequiresApi(Build.VERSION_CODES.O)
//        fun decode(temp:String):String{
//            // decode into String from encoded format
//            // decode into String from encoded format
//            val actualByte: ByteArray = Base64.getDecoder()
//                .decode(temp)
//            val actualString = String(actualByte)
//            return actualString
//
//        }
//    }

    companion object {
        fun decode(s: String): String {
            // Define a constant for an invalid code
            val invalid = "Invalid Code"

            // create the same initial
            // string as in encode class
            val ini = "11111111"
            var flag = true   // A flag to check if the initial value matches

            // Check if the initial value is the same as the encoded string
            for (i in 0 until 8) {
                if (ini[i] != s[i]) {
                    flag = false
                    break
                }
            }
            var `val` = ""   // Variable to store the reversed encrypted code

            // Reverse the encrypted code and store it in the `val` variable
            for (i in 8 until s.length) {
                `val` += s[i]
            }

            // Create a 2-dimensional array with dimensions 11101 x 8
            val arr = Array(11101) { IntArray(8) }
            var ind1 = -1   // Indices for the first dimension of the array
            var ind2 = 0   // Indices for the second dimension of the array

             // Populate the 2D array with values from the reversed encrypted code
            for (i in `val`.indices) {

                // check if the position of the
                // string if divisible by 7
                if (i % 7 == 0) {
                    // start the value in the other
                    // column of the 2D array
                    ind1++
                    ind2 = 0
                    val ch = `val`[i]
                    arr[ind1][ind2] = ch - '0'
                    ind2++
                } else {
                    // otherwise store the value
                    // in the same column
                    val ch = `val`[i]
                    arr[ind1][ind2] = ch - '0'
                    ind2++
                }
            }
            // create an array to store decimal values
            val num = IntArray(11111)
            var nind = 0   // Index for the decimal values array
            var tem: Int
            var cu: Int
            // run a loop of size of the column
            for (i in 0..ind1) {
                cu = 0
                tem = 0
                // convert binary to decimal and add them
                // from each column and store in the array
                for (j in 6 downTo 0) {
                    val tem1 = 2.0.pow(cu.toDouble()).toInt()
                    tem += arr[i][j] * tem1
                    cu++
                }
                num[nind++] = tem
            }
            var ret = ""   // Variable to store the final decrypted string
            var ch: Char
            
            // convert the decimal ASCII number to its
            // char value and add them to form a decrypted
            // string using concatenation function
            for (i in 0 until nind) {
                ch = num[i].toChar()
                ret += ch
            }
            Log.e("dec", "text 11 - $ret")

            // check if the encrypted code was
            // generated for this algorithm
            if (`val`.length % 7 == 0 && flag) {
                // return the decrypted code
                return ret
            } else {
                // otherwise return an invalid message
                return invalid
            }
        }
    }
}
