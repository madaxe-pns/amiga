;
; Mouse Example 02
; Uses a box as a mouse pointer
;

WBStartup

; Creates the BitMap
BitMap 0,320,256,4

; Draws a Box
Boxf 0,20,7,27,1

; Gets a Sprite from the Box
InitShape 0,8,8,2
GetaShape 0,0,20,8,8
GetaSprite 0,0

Boxf 0,20,7,27,0
Free Shape 0

; Initializes the Palette
InitPalette 0,16

PalRGB 0,0,0,0,0
PalRGB 0,1,15,15,15

; Initializes the Sprite Palette
PalRGB 0,17,0,0,15

; Initializes the Copper
InitCopList 0,$4

BLITZ

; Mouse Definitions
Mouse On
MouseArea 0,0,319,255
BitMapInput
BitMapOutput 0

; Creates the Display
CreateDisplay 0,0
DisplayPalette 0,0

; Displays the Sprite at Mouse Coordinations
DisplaySprite 0,0,MouseX,MouseY,0

; Displays the BitMap
DisplayBitMap 0,0
Colour 1

While Joyb(0)<>1

  Locate 0,0
  Print "x-",MouseX," , y-",MouseY,"     "
  DisplaySprite 0,0,MouseX,MouseY,0

  VWait

Wend

MouseWait

End
