package com.duramas.simulaciongobiernopueblo.raspberry

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID
import java.io.IOException
import java.text.DecimalFormat

class conectBluetooth {


    //android built in classes for bluetooth operations
    private val mBluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private var mmSocket: BluetoothSocket? = null
    private var mmDevice: BluetoothDevice? = null

    private var mmOutputStream: OutputStream? = null
    private var mmInputStream: InputStream? = null
    private var workerThread: Thread? = null

    private var readBuffer: ByteArray? = null
    private var readBufferPosition = 0


    //2C-P58-C
     fun conectarImpresora(): String {

        var impresoraEncotrada = false

        if (!mBluetoothAdapter.isEnabled) {
            return "Encienda  el Bluttoth"
        }

        try {
            val listaDipositivos = mBluetoothAdapter.bondedDevices
            for (item in listaDipositivos) {
                if (item.name.equals("HC-05")) {
                    mmDevice = item
                    impresoraEncotrada = true
                    break
                }
            }
        } catch (securityException: SecurityException) {
            return "Encienda el Bluttoth"
        }

        return if (impresoraEncotrada) {
            "Impresora encontrada"
        } else {
            "Conecte la impresora"
        }
    }

     fun abrirConexion() {
        try {
            val uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
            mmSocket = mmDevice!!.createRfcommSocketToServiceRecord(uuid)
            mmSocket!!.connect()
            mmOutputStream = mmSocket!!.getOutputStream()
            mmInputStream = mmSocket!!.getInputStream()

            beginListenForData()
        } catch (exception: Exception) {
            Log.e("abrirConexion", exception.toString())
        } catch (securityException: SecurityException) {
            Log.e("abrirConexion", securityException.toString())
        }
    }


     fun beginListenForData() {
        try {
            //This is the ASCII code for a newline character
            val delimiter: Byte = 10
            readBufferPosition = 0
            readBuffer = ByteArray(1024)
            workerThread = Thread {
                while (!Thread.currentThread().isInterrupted) {
                    try {
                        val bytesAvailable = mmInputStream!!.available()
                        if (bytesAvailable > 0) {
                            val packetBytes = ByteArray(bytesAvailable)
                            mmInputStream!!.read(packetBytes)
                            for (i in 0 until bytesAvailable) {
                                val b = packetBytes[i]
                                if (b == delimiter) {
                                    val encodedBytes = ByteArray(readBufferPosition)
                                    System.arraycopy(
                                        readBuffer, 0,
                                        encodedBytes, 0,
                                        encodedBytes.size
                                    )
                                    readBufferPosition = 0

                                } else {
                                    readBuffer!![readBufferPosition++] = b
                                }
                            }
                        }
                    } catch (ex: IOException) {
                        Log.e("beginListenForData", ex.toString())
                    }
                }
            }
            workerThread!!.start()

        } catch (e: NullPointerException) {
            Log.e("beginListenForData", e.toString())
        } catch (e: Exception) {
            Log.e("beginListenForData", e.toString())
        }
    }

     fun impimir(envio:String) {
        try {
            mmOutputStream!!.write(envio.toByteArray())
            close()
        } catch (exception: Exception) {
            Log.e("impimir", exception.toString())
        }
    }

    private fun close() {
        mmOutputStream!!.close()
        mmInputStream!!.close()
        mmSocket!!.close()
    }
}

fun FormatearMonto(precio: Double): String {
    val decimalFormat = DecimalFormat("#,###.##")
    return "$ ${decimalFormat.format(precio).replace('.',',')}"
}