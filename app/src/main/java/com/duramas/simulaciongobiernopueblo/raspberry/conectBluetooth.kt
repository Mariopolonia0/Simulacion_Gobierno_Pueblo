package com.duramas.simulaciongobiernopueblo.raspberry

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID

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

    var state = ""

    init {

        if (conectarImpresora())
            if(abrirConexion())
                beginListenForData()

    }

    private fun conectarImpresora(): Boolean {

        if (!mBluetoothAdapter.isEnabled) {
            state = "Encienda el Bluttoth"
            return false
        }

        try {
            val listaDipositivos = mBluetoothAdapter.bondedDevices
            for (item in listaDipositivos) {
                state = "Conecte la Raspberry pi pico"
                if (item.name.equals("HC-05")) {
                    mmDevice = item
                    state = "Raspberry pi pico encontrada"
                    return true
                }
            }
        } catch (securityException: SecurityException) {
            state = "Encienda el Bluttoth"
            return false
        }
        return true
    }

    private fun abrirConexion() :Boolean{
        var _state = true
        try {
            val uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
            mmSocket = mmDevice!!.createRfcommSocketToServiceRecord(uuid)
            mmSocket!!.connect()
            mmOutputStream = mmSocket!!.getOutputStream()
            mmInputStream = mmSocket!!.getInputStream()
        } catch (securityException: SecurityException) {
            _state = false
            Log.e("abrirConexion securityException", securityException.toString())
        } catch (exception: Exception) {
            _state = false
            Log.e("abrirConexion exception", exception.toString())
        }

        return if (_state) {
            state = "Conectado"
            true
        } else {
            state += "pero no esta conectada"
            false
        }
    }

    private fun beginListenForData() {
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

    fun impimir(envio: String) {
        try {
            mmOutputStream!!.write(envio.toByteArray())

        } catch (exception: Exception) {
            Log.e("impimir", exception.toString())
        }
    }

    fun close() {
        mmOutputStream!!.close()
        mmInputStream!!.close()
        mmSocket!!.close()
    }
}
