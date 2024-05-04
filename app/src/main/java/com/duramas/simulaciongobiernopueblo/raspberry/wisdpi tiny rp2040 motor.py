#Codigo WisdPi Tiny RP2040
from time import sleep
from machine import Pin,UART
import neopixel

pixel_pin = 29
pixel = neopixel.NeoPixel(machine.Pin(pixel_pin), 1)
pixel[0] = (0, 17, 255)
pixel.write()

leftBack = machine.Pin(17,Pin.OUT)
leftForward = machine.Pin(18,Pin.OUT)

rigthForward = machine.Pin(20,Pin.OUT)
righBack = machine.Pin(19,Pin.OUT)

leftForward.value(0)
leftBack.value(0)

rigthForward.value(0)
righBack.value(0)

pixel[0] = (0, 0, 0)
pixel.write()

uart = UART(0,9600)

#prueba
while True:
    if uart.any() > 0:
        dato = uart.readline()
        print(dato)

        if "0" in dato:
            leftForward.value(0)
            leftBack.value(0)
            rigthForward.value(0)
            righBack.value(0)
            pixel[0] = (0, 0, 0)
            pixel.write()

        if "1" in dato:
            leftForward.value(1)
            leftBack.value(0)
            rigthForward.value(1)
            righBack.value(0)
            pixel[0] = (255, 0, 0)
            pixel.write()

        if "2" in dato:
            leftForward.value(0)
            leftBack.value(1)
            rigthForward.value(0)
            righBack.value(1)
            pixel[0] = (0, 0, 255)
            pixel.write()

        if "3" in dato:
            leftForward.value(0)
            leftBack.value(1)
            rigthForward.value(1)
            righBack.value(0)

        if "4" in dato:
            leftForward.value(0)
            leftBack.value(1)
            rigthForward.value(0)
            righBack.value(1)