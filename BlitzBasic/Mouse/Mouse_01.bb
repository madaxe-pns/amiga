;
; Mouse Example 01
; Shows the coordinates of the mouse
;

WBStartup

; Creates the BitMap
BitMap 0,320,256,4

; Initializes the Palette
InitPalette 0,16

PalRGB 0,0,0,0,0
PalRGB 0,1,15,15,15

; Initializes the Copper
InitCopList 0,$4

BLITZ

; Mouse definitions
Mouse On
MouseArea 0,0,319,255
BitMapInput
BitMapOutput 0

; Creates the Display
CreateDisplay 0,0
DisplayPalette 0,0

; Displays the BitMap
DisplayBitMap 0,0
Colour 1

While Joyb(0)<>1

  Locate 0,0
  Print "x-",MouseX," , y-",MouseY,"     "

Wend

MouseWait

End
