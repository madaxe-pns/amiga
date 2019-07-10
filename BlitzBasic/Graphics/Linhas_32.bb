;
; Creates a BitMap and draws Lines on OCS/ECS - 5 BitPlanes : 32 Colours
;

WBStartup

; Creates the BitMap
BitMap 0,320,256,5

; Initializes the Copper
InitCopList 0,44,256,$5,8,32,0

; Initializes the Palette
InitPalette 0,32

For i=31 To 0 Step -1:PalRGB 0,i,Rnd(15),Rnd(15),Rnd(15):Next

PalRGB 0,0,0,0,0


BLITZ


; Draws the Lines
For i= 0 To 255
  Line 0,i,320,i,i
Next

; Creates the Display
CreateDisplay 0

DisplayPalette 0,0

; Displays the BitMap
DisplayBitMap 0,0

MouseWait

End
