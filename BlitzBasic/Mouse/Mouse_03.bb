;
; Mouse Example 03
; Reads the Mouse Pointer from a file
;

WBStartup

; Creates the BitMap
BitMap 0,320,256,5

; Loads the Sprite from a file
InitShape 0,7,7,2
LoadShape 0,"cursor.lbm"
GetaSprite 0,0

Free Shape 0

; Initializes the Palette
InitPalette 0,32

PalRGB 0,0,0,0,0
PalRGB 0,1,15,15,15

; Loads the Sprite Palette
LoadPalette 0,"cursor.lbm",16

; Initializes the Copper
InitCopList 0,$5

BLITZ

; Mouse Definitions
Mouse On
MouseArea 0,0,319,255
BitMapInput
BitMapOutput 0

; Creates the Display
CreateDisplay 0,0
DisplayPalette 0,0

; Draws the Sprite at Mouse Coordinations
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
